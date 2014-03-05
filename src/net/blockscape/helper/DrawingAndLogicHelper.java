package net.blockscape.helper;

import net.blockscape.BlockScape;
import net.blockscape.block.Block;
import net.blockscape.gui.Button;
import net.blockscape.lib.MainReference;
import net.blockscape.registry.ButtonRegistry;
import net.blockscape.registry.FontRegistry;
import net.blockscape.registry.TextBoxRegistry;
import net.blockscape.save.SaveData;
import net.blockscape.world.World;
import net.blockscape.world.WorldBlock;
import processing.core.PApplet;
import processing.core.PConstants;

public class DrawingAndLogicHelper
{
	static float rotSinTim;
	static long oldTime = 0;
	/**
	 * Called every time the game loops in game-play
	 * @param host the game window
	 */
	public static void drawGame(PApplet host)
	{
		host.textFont(FontRegistry.inGameFont);
		host.textAlign(PApplet.LEFT);
		
		for(WorldBlock b: World.getWorld())
			b.updateAndDraw();

		rotSinTim += 0.05;
		BlockScape.player.setYvelocity(BlockScape.player.getYvelocity() + 270F * ((BlockScape) host).deltaTime);
		
		BlockScape.distBetweenPlayerAndMouse = PApplet.dist(BlockScape.player.getX() + BlockScape.player.getWidth() / 2, BlockScape.player.getY() + BlockScape.player.getHeight() / 2, host.mouseX, host.mouseY)/16;
		
		if(BlockScape.distBetweenPlayerAndMouse < MainReference.MAX_REACH && BlockScape.distBetweenPlayerAndMouse > 1)
			BlockScape.canPlaceOrRemoveBlock = true;
		else
			BlockScape.canPlaceOrRemoveBlock = false;
		
		BlockScape.player.update();
		
		BlockScape.player.draw();
		
		long curTime = System.currentTimeMillis();
		
		if(curTime >= oldTime + 30*1000){
		    SaveData.saveGame(BlockScape.worldName);
		    oldTime = System.currentTimeMillis();
		}
		
		if(BlockScape.selectedBlock != null)
		{
        	  float r = PApplet.map(PApplet.sin(rotSinTim), -1, 1, -16, 16);
        	  host.pushMatrix();
        
        	  host.translate(175, 24);
        	  host.imageMode(PApplet.CENTER);
        	  host.rectMode(PApplet.CENTER);
        	  host.rotate(PApplet.radians(r));
        	  host.image(BlockScape.selectedBlock.getTexture(),0, 0, 16, 16);
        	  
        	  if(!BlockScape.canPlaceOrRemoveBlock)
        	  {
        		  host.fill(0,100);
        		  host.rect(0, 0, 16, 16);
        	  }
        	  
        	  host.popMatrix();
        	  host.rectMode(PApplet.CORNER);
        	  host.fill(0);
        	  host.text("Currently Selected Block:", 1, 30);
        	  host.fill(BlockScape.canPlaceOrRemoveBlock ? 200: 0);
        	  host.text(BlockScape.selectedBlock.getName(), 190, 30);
        	  host.rectMode(PApplet.CORNER);
		}
		else
			BlockScape.selectedBlock = Block.blockStone;
		
		//Check for block placement and removal
		if (host.mousePressed && host.mouseButton == PApplet.RIGHT && BlockScape.canPlaceOrRemoveBlock)
			World.addBlockWithoutReplacing(new WorldBlock(host.mouseX / 16, (host.height - host.mouseY) / 16, BlockScape.selectedBlock, host));

		if (host.mousePressed && host.mouseButton == PApplet.LEFT && BlockScape.canPlaceOrRemoveBlock)
			World.removeBlockFromWorld(host.mouseX / 16, (host.height - host.mouseY) / 16);
		
	}
	
	 /**
     * Called every time the game loops when paused
     * @param host the game window
     */
    public static void drawPauseMenu(PApplet host)
    {
        for(WorldBlock b: World.getWorld())
            b.updateAndDraw();
        
        BlockScape.player.draw();
        
        
        ButtonRegistry.returnToGame.update();
        ButtonRegistry.returnToMenu.update();
        ButtonRegistry.flyMode.update();
        
        if (BlockScape.isFlyMode)
            ButtonRegistry.flyMode.setText("Fly Mode: On");
        else
            ButtonRegistry.flyMode.setText("Fly Mode: Off");
        
        if (BlockScape.isSaving && ((int) BlockScape.saveDisplayCounter / 20) % 2 == 0)
        {
            host.textAlign(PConstants.CENTER);
            host.text("Saving. . .", host.width / 2, 40);
        }
    }
    
    /**
     * Called every time the game loops when main menu
     * @param host the game window
     */
    public static void drawMainMenu(PApplet host)
    {
        host.textFont(FontRegistry.titleFont);
        host.text(MainReference.GAME_NAME, host.width / 2, 100);
        host.textFont(FontRegistry.buttonFont);
        host.text(MainReference.CREATOR_CREDIT, host.width / 2, 150);
        
        ButtonRegistry.loadWorld.update();
        ButtonRegistry.newWorld.update();
        ButtonRegistry.exitGame.update();
    }
    
    /**
     * Called every time the game loops when world creator menu
     * @param host the game window
     */
    public static void drawWorldCreateMenu(PApplet host)
    {
        TextBoxRegistry.worldNamer.update();
        ButtonRegistry.createWorld.update();
        ButtonRegistry.backFromCreate.update();
    }
    
    /**
     * Called every time the game loops when world selector menu
     * @param host the game window
     */
    public static void drawWorldSelectionMenu(PApplet host)
    {
        for(Button b : ButtonRegistry.worldButtons)
        {
        	b.update();
        }
    }
}
