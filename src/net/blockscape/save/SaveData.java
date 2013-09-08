package net.blockscape.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.ArrayList;

import net.blockscape.helper.FileHelper;
import net.blockscape.helper.LogHelper;
import net.blockscape.lib.MainReference;
import net.blockscape.world.WorldBlock;

public class SaveData
{
    private static ArrayList<WorldSave> saves;

    public static void initDirectory()
    {
        saves = new ArrayList<WorldSave>();
        
        try
        {
            Files.createDirectories(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves"));
        }
        catch (Exception e)
        {
            LogHelper.severe(MainReference.FILE_ERROR_MSG);
            e.printStackTrace();
        }
    }
    
    public static void addWorld(WorldSave world)
    {
        saves.add(world);
        createWorldFile(saves.indexOf(world));
    }
    
    public static void createWorldFile(int index)
    {
        try
        {
            Files.createDirectory(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves" + File.separator + saves.get(index).getName()));
            Files.createFile(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves" + File.separator + saves.get(index).getName() + File.separator + "World.data"));
        }
        catch (Exception e)
        {
            LogHelper.severe(MainReference.FILE_ERROR_MSG);
            e.printStackTrace();
            return;
        }
        
    }
    
    public static void saveGame(WorldSave newWorld)
    {
        for (WorldSave save: saves)
        {
            if (save.getName() == newWorld.getName())
            {
                saves.set(saves.indexOf(save), newWorld);
                prepareForWorldWrite(saves.indexOf(newWorld));
                return;
            }
        }
        
        LogHelper.severe("Could not find matching world to save!!");
    }
    
    private static void prepareForWorldWrite(int index)
    {
        try
        {
            LogHelper.info("EARLY!!!");
            File worldFile = new File(FileHelper.getFileDirectoryString() + "saves" + File.separator + saves.get(index).getName() + File.separator + "World.data");
            if (worldFile.exists())
            {
                LogHelper.info("WAY!!");
                if (worldFile.delete())
                {
                    LogHelper.info("BEFORE!!!");
                    File newWorldFile = new File(Files.createFile(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves" + File.separator + saves.get(index).getName() + File.separator + "World.data")).toString());
                    writeToWorldFile(newWorldFile, saves.get(index).getBlocks());
                    LogHelper.info("AFTER!!!");
                }
            }
        }
        catch (Exception e)
        {
            LogHelper.severe(MainReference.FILE_ERROR_MSG);
            e.printStackTrace();
            return;
        }
    }
    
    public static void writeToWorldFile(File worldFile, ArrayList<WorldBlock> blocks) throws FileNotFoundException
    {
        PrintWriter output = new PrintWriter(worldFile);
        
        try
        {
            for (WorldBlock b: blocks)
            {
                output.print(b.getBlock().blockID + " ");
                output.print(b.getCoords().x + " ");
                output.println(b.getCoords().y);
            }
            
        }
        catch (Exception e)
        {
            LogHelper.severe(MainReference.FILE_ERROR_MSG);
            e.printStackTrace();
        }
        finally
        {
            output.close();
        }
    }
    
}
