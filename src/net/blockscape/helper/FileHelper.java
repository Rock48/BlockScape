package net.blockscape.helper;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileHelper
{

    public static String getFileDirectoryString()
    {
        return "BlockScape" + File.separator;
    }
    
    public static String getAbsoluteFileDirectoryString()
    {
        return System.getenv("APPDATA") + File.separator + "BlockScape" + File.separator;
    }
    
    public static Path getPathFromString(String path)
    {
        return FileSystems.getDefault().getPath(System.getenv("APPDATA") + File.separator, path);
    }
    
}
