package net.blockscape;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.net.URL;

import net.blockscape.block.Block;
import net.blockscape.gui.Button;
import net.blockscape.helper.*;
import net.blockscape.lib.MainReference;
import net.blockscape.registry.GameRegistry;
import net.blockscape.save.SaveData;
import net.blockscape.world.World;

import processing.core.PApplet;
import processing.core.PFont;

public class BlockScape extends PApplet
{
	public static final int maxReachDistance = 5; //Distance you can build away from you
	private static final long serialVersionUID = -1390024970025652247L; //Dunno
	
	public static float distBetweenPlayerAndMouse; //The distance between the player and the user's mouse
	
	//Booleans
	public static boolean canPlaceOrRemoveBlock;
	public boolean ground;
	public static boolean isPaused;
	public static boolean isFlyMode;
	
	//Fonts
	public static PFont buttonFont;
	public static PFont inGameFont;
	
	//Buttons
	public static Button returnToGame;
	public static Button exitGame;
	public static Button flyModeOn;
	public static Button flyModeOff;
	
	
	/**
     * @param args
     */
    public static void main(String[] args)
    {
        PApplet.main(new String[]{"net.blockscape.BlockScape"});
    }
    
	/**
	 * Called on game start
	 */
	public void setup()
	{
	    buttonFont = createFont("Arial", 24, true);
	    inGameFont = createFont("Arial", 14, true);
	    
	    ground = false;
	    isFlyMode = false;
	    
	    returnToGame = new Button(540, 360, 200, 70, "Return To Game", true, buttonFont, this);
	    exitGame = new Button(540, 500, 200, 70, "Exit Game", true, buttonFont, this);
	    flyModeOn = new Button(540, 220, 200, 70, "Fly Mode: ON", true, buttonFont, this);
        flyModeOff = new Button(540, 220, 200, 70, "Fly Mode: OFF", true, buttonFont, this);
        
	    LogHelper.init();
		World.init();
		IconHelper.init(this);
		Player.initPlayer(width/2, 0, this);
		SaveData.initDirectory();
		size(1280,720);
		
		if(frame != null)
		{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL icon = classLoader.getResource("data/block/grass.png");
			frame.setTitle(MainReference.GAME_NAME);
			frame.setIconImage(getToolkit().getImage(icon));
		}
		
		GameRegistry.initialize();
		Block.blockInit();
		frameRate(60);
		TerrainGenerationHelper.generateWorld(this);
		addMouseWheelListener(new MouseWheelListener() { public void mouseWheelMoved(MouseWheelEvent mwe) { mouseWheel(mwe.getWheelRotation()); }});
	}
	
	/**
	 * main game loop
	 */
	public void draw()
	{
	    if (!isPaused)
	    {
	        background(100,100,255);
	        DrawingAndLogicHelper.drawGame(this);
	    }
	    else
	    {
	        background(200);
	        DrawingAndLogicHelper.drawPauseMenu(this);
	    }
	}
	
	/**
	 * called when a key is pressed
	 */
	public void keyPressed()
	{
	    if (isPaused)
	    {
	        key = 0;
	        return;
	    }
	    
    	if (key == ' ' && ground)
    	    Player.setYvelocity(-3);
    	if (key == 'a')
    	    Player.left = true;
    	if (key == 'd')
    	    Player.right = true;
    	if (key == ENTER)
    	{
    		Player.setX(mouseX);
    		Player.setY(mouseY);
    	}
    	
    	if (key == ESC)
    	{
    	    key = 0;
    	    pauseGame();
    	}
	}
	
	/**
	 * called when a key is released
	 */
	public void keyReleased()
	{
    	if (key=='a')
    	    Player.left = false;
    	if (key=='d')
    	    Player.right = false;
	}
	
	//Mouse Wheel Constants
	public final int mwUP = -1;
	public final int mwDWN = 1;
	public int selectedBlockID = 1;
	public Block selectedBlock = Block.blockStone; //Default Block
	
	/**
	 * called when the mouse wheel is used
	 * @param delta up (-1) down (1)
	 */
	public void mouseWheel(int delta)
	{
		  
	    if (delta == mwUP)
	    {
	        selectedBlockID++;
	        
	        if (selectedBlockID > GameRegistry.getBlockRegistrySize())
	            selectedBlockID = 1;
	    }
	    
	    if (delta == mwDWN)
	    {
	        selectedBlockID--;
	        
	        if (selectedBlockID < 1)
	            selectedBlockID = GameRegistry.getBlockRegistrySize();
	    }
	    
	    selectedBlock = GameRegistry.getBlock(selectedBlockID);
	}
	
	public static void pauseGame()
	{
	    isPaused = true;
	}
	
	public static void unpauseGame()
	{
	    isPaused = false;
	}
	
	public static void endgame()
	{
	    System.exit(0);
	}
	
	public void mouseReleased()
	{
	    if (BlockScape.flyModeOn.held || BlockScape.flyModeOff.held)
	        isFlyMode = !isFlyMode;
	}
}
