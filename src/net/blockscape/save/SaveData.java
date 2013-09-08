package net.blockscape.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.ArrayList;

import net.blockscape.Player;
import net.blockscape.helper.FileHelper;
import net.blockscape.helper.LogHelper;
import net.blockscape.lib.MainReference;
import net.blockscape.lib.Saves;
import net.blockscape.world.WorldBlock;

public class SaveData
{
    private static ArrayList<WorldSave> saves;

    public static void initDirectory()
    {
        saves = new ArrayList<WorldSave>();
        
        try
        {
            if (!Files.exists(FileHelper.getPathFromString(FileHelper.getFileDirectoryString())))
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
    
    private static void createWorldFile(int index)
    {
        try
        {
            if (!Files.exists(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves" + File.separator + saves.get(index).getName())))
                Files.createDirectory(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves" + File.separator + saves.get(index).getName()));
            if (!Files.exists(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves" + File.separator + saves.get(index).getName() + File.separator + Saves.WORLD_FILE_NAME)))
                Files.createFile(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves" + File.separator + saves.get(index).getName() + File.separator + Saves.WORLD_FILE_NAME));
            if (!Files.exists(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves" + File.separator + saves.get(index).getName() + File.separator + Saves.PLAYER_FILE_NAME)))
                Files.createFile(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves" + File.separator + saves.get(index).getName() + File.separator + Saves.PLAYER_FILE_NAME));
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
                prepareForPlayerWrite(saves.indexOf(newWorld));
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
            File worldFile = new File(FileHelper.getAbsoluteFileDirectoryString() + "saves" + File.separator + saves.get(index).getName() + File.separator + Saves.WORLD_FILE_NAME);
            
            if (worldFile.exists())
            {
                if (worldFile.delete())
                {
                    File newWorldFile = new File(Files.createFile(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves" + File.separator + saves.get(index).getName() + File.separator + Saves.WORLD_FILE_NAME)).toString());
                    writeToWorldFile(newWorldFile, saves.get(index).getBlocks());
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
                output.print((int) b.getCoords().x + " ");
                output.println((int) b.getCoords().y);
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
    
    private static void prepareForPlayerWrite(int index)
    {
        try
        {
            File playerFile = new File(FileHelper.getAbsoluteFileDirectoryString() + "saves" + File.separator + saves.get(index).getName() + File.separator + Saves.PLAYER_FILE_NAME);
            
            if (playerFile.exists())
            {
                if (playerFile.delete())
                {
                    File newPlayerFile = new File(Files.createFile(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves" + File.separator + saves.get(index).getName() + File.separator + Saves.PLAYER_FILE_NAME)).toString());
                    writeToPlayerFile(newPlayerFile);
                }
            }
        }
        catch (Exception e)
        {
            LogHelper.severe(MainReference.FILE_ERROR_MSG);
            e.printStackTrace();
        }
    }
    
    public static void writeToPlayerFile(File playerFile) throws FileNotFoundException
    {
        PrintWriter output = new PrintWriter(playerFile);
        
        try
        {
            output.print((int) Player.getX());
            output.print(" ");
            output.print((int) Player.getY());
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
