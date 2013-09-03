package net.blockscape.helper;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileHelper
{

    public static String getFileDirectoryString()
    {
        return "Users" + File.separator + System.getProperty("user.name") + File.separator + "AppData" + File.separator + "Roaming" + File.separator + "BlockScape" + File.separator;
    }
    
    public static Path getPathFromString(String path)
    {
        return FileSystems.getDefault().getPath("C:" + File.separator, path);
    }
    
}
