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
	
	//Font
	public static PFont mainFont;
	
	//Buttons
	public static Button returnTogame;
	
	
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
	    mainFont = new PFont(); //Declare Game's Font
	    ground = false;
	    isFlyMode = false;
	    
	    LogHelper.init();
		World.init();
		IconHelper.init(this);
		Player.initPlayer(width/2, 0, this);
		SaveData.initDirectory();
		size(1280,720);
		
		if(frame!=null)
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
	
	public void pauseGame()
	{
	    isPaused = true;
	}
	
	public void unpauseGame()
	{
	    isPaused = false;
	}

}
