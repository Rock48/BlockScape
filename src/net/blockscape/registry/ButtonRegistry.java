package net.blockscape.registry;

import net.blockscape.gui.Button;

import processing.core.PApplet;

public class ButtonRegistry
{
    public static Button returnToGame;
    public static Button exitGame;
    public static Button flyMode;
    
    public static void init(PApplet host)
    {
        returnToGame = new Button(540, 360, 200, 70, "Return To Game", true, FontRegistry.buttonFont, host);
        exitGame = new Button(540, 500, 200, 70, "Exit Game", true, FontRegistry.buttonFont, host);
        flyMode = new Button(540, 220, 200, 70, "Fly Mode: Off", true, FontRegistry.buttonFont, host);
    }
}
