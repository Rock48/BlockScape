package net.blockscape.save;

import java.nio.file.*;

import net.blockscape.helper.FileHelper;

public class SaveData
{

    public static void initDirectory()
    {
        try
        {
            Files.createDirectories(FileHelper.getPathFromString(FileHelper.getFileDirectoryString() + "saves"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
