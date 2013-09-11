package net.blockscape.registry;

import processing.core.PApplet;
import processing.core.PFont;

public class FontRegistry
{
    public static PFont buttonFont;
    public static PFont inGameFont;
    public static PFont titleFont;
    
    public static void init(PApplet host)
    {
        buttonFont = host.createFont("Arial", 24, true);
        inGameFont = host.createFont("Arial", 14, true);
        titleFont = host.loadFont("AtomicAge-48.vlw");
    }
}
