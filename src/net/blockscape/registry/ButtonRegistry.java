package net.blockscape.registry;

import java.util.ArrayList;

import net.blockscape.BlockScape;
import net.blockscape.gui.Button;
import net.blockscape.helper.GeneralHelper;
import net.blockscape.save.SaveData;
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
    public static Button backFromCreate;
    
    //Button Array
    public static ArrayList<Button> worldButtons;
    
    public static void init(PApplet host)
    {
        returnToGame = new Button(540, 360, 200, 70, "Return To Game", true, FontRegistry.buttonFont, host);
        flyMode = new Button(540, 220, 200, 70, "Fly Mode: Off", true, FontRegistry.buttonFont, host);
        returnToMenu = new Button(540, 500, 200, 70, "Return To Menu", true, FontRegistry.buttonFont, host);
        
        exitGame = new Button(340, 400, 600, 70, "Exit Game", true, FontRegistry.buttonFont, host);
        loadWorld = new Button(375, 220, 250, 70, "Load Saved World", true, FontRegistry.buttonFont, host);
        newWorld = new Button(655, 220, 250, 70, "Create New World", true, FontRegistry.buttonFont, host);
        
        createWorld = new Button(700, 340, 200, 70, "Create World!", true, FontRegistry.buttonFont, host);
        backFromCreate = new Button(400, 340, 200, 70, "Cancel", true, FontRegistry.buttonFont, host);
        
        
        generateWorldButtons();
    }
    
    public static void generateWorldButtons()
    {
        worldButtons = new ArrayList<Button>();
        
        int wbY = 1;
        int wbX = 0;
        
        for(String s : SaveData.getSaves())
        {
            worldButtons.add(new Button(50 + wbX * 350, wbY * 50, 300, 35, GeneralHelper.getTokenFromEnd(s, "\\.", 2), true, FontRegistry.buttonFont, BlockScape.instance));
            
            wbY++;
            
            if(wbY>9)
            {
                wbY=1;
                wbX++;
            }
        }
    }
}
