package net.blockscape.save;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import net.blockscape.BlockScape;
import net.blockscape.Player;
import net.blockscape.helper.FileHelper;
import net.blockscape.helper.LogHelper;
import net.blockscape.lib.MainReference;
import net.blockscape.lib.Saves;
import net.blockscape.registry.GameRegistry;
import net.blockscape.world.World;
import net.blockscape.world.WorldBlock;

import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.unpacker.Unpacker;

import processing.core.PApplet;

public class SaveData
{
    public static void initDirectory(PApplet host_){
        try
        {
            if (!Files.exists(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + Saves.WORLD_SAVES_FOLDER)))
                Files.createDirectories(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + Saves.WORLD_SAVES_FOLDER));
            
        }
        catch (Exception e)
        {
            LogHelper.severe(MainReference.FILE_ERROR_MSG);
            e.printStackTrace();
        }
    }
    public static Save createSaveOfCurrentWorld(){
        Save save = new Save();
        save.x = BlockScape.player.x;
        save.y = BlockScape.player.y;
        save.velx = BlockScape.player.xvelocity;
        save.vely = BlockScape.player.yvelocity;
        save.blocks = new int[BlockScape.instance.width/16+1][BlockScape.instance.height/16];
        
        for (int x = 0; x < BlockScape.instance.width/16+1; x++)
        {
            for (int y = 0; y < BlockScape.instance.height/16; y++)
            {
                save.blocks[x][y] = World.getBlock(x, y).getBlock().blockID;
            }
        }
        return save;
    }
    static class SaveWorker implements Runnable {
        String filename;
        
        
        public SaveWorker(String file){
            filename = file;
        }
        @Override
        public void run() {
            MessagePack msgpack = new MessagePack();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Packer packer = msgpack.createPacker(out);
            
            try
            {
                packer.write(createSaveOfCurrentWorld());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            
            File savefile = new File(FileHelper.getAbsoluteFileDirectoryString() + File.separator + "saves" + File.separator + filename + ".jif");
            
            try
            {
                savefile.createNewFile();
                FileOutputStream stream = new FileOutputStream(savefile);
                out.writeTo(stream);
                stream.close();
                out.close();
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            
        }
        
    }
    public static void saveGame(String filename)
    {
    	Thread worker = new Thread(new SaveWorker(filename));
        worker.start();
    }
    
    public static void loadGame(String filename, Player player) throws Exception
    {
    	MessagePack msgpack = new MessagePack();
    	
        FileInputStream file = new FileInputStream(FileHelper.getAbsoluteFileDirectoryString() + File.separator + "saves" + File.separator + filename + ".jif");
        
    	Unpacker unpacker = msgpack.createUnpacker(file);
    	
    	Save save = unpacker.read(Save.class);
    	
    	ArrayList<WorldBlock> blocks = new ArrayList<WorldBlock>();
    	
    	for(int x = 0; x < save.blocks.length; x++){
    	    for(int y = 0; y < save.blocks[x].length; y++){
                if(save.blocks[x][y]!=0)
    	        blocks.add(new WorldBlock(x,y,GameRegistry.getBlock(save.blocks[x][y]),BlockScape.instance));
    	        
            }
    	}
    	
    	World.setWorld(blocks);
    	
    	player.x = save.x;
    	player.y = save.y;
    	player.xvelocity = save.velx;
    	player.yvelocity = save.vely;
    }
    public List<String> getSaves(){
    	File savedir = new File(FileHelper.getAbsoluteFileDirectoryString() + File.separator + "saves" + File.separator);
    	File[] listOfFiles = savedir.listFiles();
    	List<String> filenames = new ArrayList<String>();
    	for(int i = 0; i < listOfFiles.length; i++){
    		String filename = listOfFiles[i].getName();
    		if(filename.endsWith(".jif")){
    			filenames.add(filename);
    		}
    	}
    	return filenames;
    }
}