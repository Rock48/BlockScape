package net.blockscape.save;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;

import net.blockscape.Player;
import net.blockscape.helper.FileHelper;
import net.blockscape.helper.LogHelper;
import net.blockscape.lib.MainReference;
import net.blockscape.lib.Saves;
import net.blockscape.world.WorldBlock;
import processing.core.PApplet;

public class SaveData
{
    private static ArrayList<WorldSave> saves;
    private static PApplet host;

    public static void initDirectory(PApplet host_)
    {
        host = host_;
        saves = new ArrayList<WorldSave>();
        
        try
        {
            if (!Files.exists(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + Saves.WORLD_SAVES_FOLDER)))
                Files.createDirectories(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + Saves.WORLD_SAVES_FOLDER));
            else
            {
                File folder = new File(FileHelper.getAbsoluteFileDirectoryString() + Saves.WORLD_SAVES_FOLDER);
                File[] saveFolders = folder.listFiles();
                
                if (saveFolders == null)
                    return;
                
                for (File f: saveFolders)
                {
                    if (f.isDirectory())
                        saves.add(new WorldSave(f.getName()));
                }
            }
        }
        catch (Exception e)
        {
            LogHelper.severe(MainReference.FILE_ERROR_MSG);
            e.printStackTrace();
        }
    }
    public static void saveGame(WorldSave save){
    	MessagePack msgpack = new MessagePack();
    	String name = save.getName();
    	ArrayList<WorldBlock> blocks = save.blocks;
    	Player player = save.player;
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	Packer packer = msgpack.createPacker(out);
        try {
			packer.write(save);
		} catch (IOException e) {
			e.printStackTrace();
		}
       
    }
    public static void loadGame(String filename){
    	
    }
}