package net.blockscape.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.blockscape.lib.MainReference;

public class LogHelper
{
    private static Logger mainLog;
    
    public static void init()
    {
        mainLog = Logger.getLogger(MainReference.GAME_NAME);
    }
    
    public static void log(Level level, String msg)
    {
        mainLog.log(level, msg);
    }
    
    public static void debug(String msg)
    {
        mainLog.log(Level.INFO, "[DEBUG] " + msg);
    }
    
    public static void info(String msg)
    {
        mainLog.log(Level.INFO, msg);
    }
    
    public static void severe(String msg)
    {
        mainLog.log(Level.SEVERE, msg);
    }
    
    public static void warning(String msg)
    {
        mainLog.log(Level.WARNING, msg);
    }
    
}
