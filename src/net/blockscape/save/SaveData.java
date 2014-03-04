package net.blockscape.save;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import net.blockscape.helper.FileHelper;
import net.blockscape.helper.LogHelper;
import net.blockscape.lib.MainReference;
import net.blockscape.lib.Saves;

import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;

import processing.core.PApplet;

public class SaveData
{
    private static ArrayList<WorldSave> saves;
    public static void initDirectory(PApplet host_)
    
    {
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
    
    public static void saveGame(WorldSave save)
    {
    	MessagePack msgpack = new MessagePack();
    	String name = save.getName();
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	Packer packer = msgpack.createPacker(out);
    	
        try
        {
			packer.write(save.save);
		}
        catch (IOException e)
        {
			e.printStackTrace();
		}
        
        File savefile = new File(FileHelper.getAbsoluteFileDirectoryString() + File.separator + "saves" + File.separator + name + ".jif");
        
        try
        {
        	savefile.createNewFile();
        	FileOutputStream stream = new FileOutputStream(savefile);
        	out.writeTo(stream);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public static void loadGame(String filename)
    {
    	
    }
}