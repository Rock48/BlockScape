package net.blockscape.registry;

import net.blockscape.gui.TextBox;
import processing.core.PApplet;

public class TextBoxRegistry
{

    //Test
    public static TextBox test;
    
    public static void init(PApplet host)
    {
        test = new TextBox(20, 20, 200, 100, "This is a test:", true, FontRegistry.buttonFont, host);
    }
}
