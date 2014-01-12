package net.blockscape.registry;

import net.blockscape.gui.Button;

import processing.core.PApplet;

public class ButtonRegistry
{
    //Pause Menu
    public static Button returnToGame;
    public static Button flyMode;
    public static Button returnToMenu;
    
    //Main Menu
    public static Button loadWorld;
    public static Button newWorld;
    public static Button exitGame;
    
    //World Creator
    public static Button createWorld;
    
    public static void init(PApplet host)
    {
        returnToGame = new Button(540, 360, 200, 70, "Return To Game", true, FontRegistry.buttonFont, host);
        exitGame = new Button(340, 400, 600, 70, "Exit Game", true, FontRegistry.buttonFont, host);
        flyMode = new Button(540, 220, 200, 70, "Fly Mode: Off", true, FontRegistry.buttonFont, host);
        returnToMenu = new Button(540, 500, 200, 70, "Return To Menu", true, FontRegistry.buttonFont, host);
        loadWorld = new Button(375, 220, 250, 70, "Load Saved World", true, FontRegistry.buttonFont, host);
        newWorld = new Button(655, 220, 250, 70, "Create New World", true, FontRegistry.buttonFont, host);
        
        createWorld = new Button(540, 340, 200, 70, "Create World!", true, FontRegistry.buttonFont, host);
    }
}
