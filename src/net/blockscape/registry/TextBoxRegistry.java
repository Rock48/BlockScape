package net.blockscape.registry;

import net.blockscape.gui.TextBox;
import processing.core.PApplet;

public class TextBoxRegistry
{
    public static TextBox worldNamer;
    
    public static void init(PApplet host)
    {
        worldNamer = new TextBox(400, 200, 500, 100, "Please name your world:", true, FontRegistry.buttonFont, host);
        
        worldNamer.input = "New World";
    }
}
