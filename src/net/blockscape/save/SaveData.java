package net.blockscape.save;

import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;

import net.blockscape.helper.FileHelper;
import net.blockscape.helper.LogHelper;

public class SaveData
{
    private static ArrayList<WorldSave> saves;

    public static void initDirectory()
    {
        try
        {
            Files.createDirectories(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves"));
        }
        catch (Exception e)
        {
            LogHelper.severe("There has been a problem creating and/or changing the file data. Below is the stack trace.");
            e.printStackTrace();
        }
    }
    
    public static void addWorld(WorldSave world)
    {
        saves.add(world);
    }
    
    /*public static void createWorldFile())
    {
        try
        {
            Files.createFile(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves" + File.separator + world.getName() + ".world"));
        }
        catch (Exception e)
        {
            LogHelper.severe("There has been a problem creating and/or changing the file data. Below is the stack trace.");
            e.printStackTrace();
            return;
        }
        
    }*/
    
    public static void saveGame()
    {
        
    }
    
}
