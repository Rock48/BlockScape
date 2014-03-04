package net.blockscape.world;

import net.blockscape.BlockScape;
import net.blockscape.block.Block;
import net.blockscape.gui.OptionsScreenEnum;

import org.msgpack.annotation.Message;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
@Message
public class WorldBlock
{
	public PVector loc;
	Block block;
	public float width;
	public float height;
	PApplet host;
	PVector worldCoords;
	
	/**
	 * Creates a new block for putting in the world
	 * @param x world x
	 * @param y world y
	 * @param block the block
	 * @param host the game window
	 */
	public WorldBlock(float x, float y, Block block, PApplet host)
	{
		loc = new PVector(x * 16, (host.height / 16 - y - 1) * 16);
		worldCoords = new PVector(x, y);
		this.host = host;
		this.block = block;
		width = 16;
		height = 16;
	}
	
	/**
	 * updates and draws
	 */
	public void updateAndDraw()
	{
		PImage texture = block.getTexture();
		host.imageMode(PApplet.CORNER);
		host.image(texture, loc.x, loc.y);
		
		if (host.mouseX >= loc.x && host.mouseX < loc.x+16 && host.mouseY >= loc.y && host.mouseY < loc.y+16 && BlockScape.screenSelected == OptionsScreenEnum.noScreen)
		{
			host.fill(0);
			int y = (int)(((host.height-loc.y)/16)-1);
			String toprint = "("+(int)(loc.x/16)+", "+y+") "+ block.getName();
			host.text(toprint, 1, 12);
			host.noFill();
			host.stroke(0);
			host.rect(loc.x, loc.y, 15, 15);
		}
	}
	
	/**
	 * returns if the given point is intersecting with the block
	 * @param point that is or is not intersecting
	 * @return if the point is intersecting
	 */
	public boolean intersectsWith(PVector point)
	{
		return point.x>loc.x && point.y>loc.y && point.x<loc.x+width && point.y<loc.y+height;
	}
	
	/**
	 * gets the block type
	 * @return the block type
	 */
	public Block getBlock()
	{
		return block;
	}
	
	/**
	 * get the world coordanites of the block
	 * @return the world coords
	 */
	public PVector getCoords()
	{
		return worldCoords;
	}
}
