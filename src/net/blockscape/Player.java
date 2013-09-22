package net.blockscape;

import net.blockscape.helper.IconHelper;
import net.blockscape.world.World;
import net.blockscape.world.WorldBlock;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Player
{
	
    static PImage texture;
	private static float x, y, width, height, xvelocity, yvelocity;
	public static boolean right, left, up, down;
	static PApplet host;
	
	/**
	 * Initialized the player
	 * @param x_ 
	 * @param y_
	 * @param host_ game window for drawing
	 */
	public static void initPlayer(int x_, int y_, PApplet host_)
	{
		setX(x_);
		setY(y_);
		setWidth(16);
		setHeight(30);
		host = host_;
		texture = IconHelper.convertStringToEntityImage("Player.png");
	}
	
	/**
	 * draws the player
	 */
	public static void draw()
	{
		host.imageMode(PApplet.CORNER);
		host.noStroke();
		host.image(texture, Player.getX(), Player.getY());
	}
	
	/**
	 * updates collisions and player position
	 */
	public static void update()
	{
		
		setY(getY() + getYvelocity() * ((BlockScape) host).deltaTime);
		setX(getX() + xvelocity * ((BlockScape)host).deltaTime);
		
		
		
		if (xvelocity > 0)
		{
			xvelocity -= 30*((BlockScape)host).deltaTime;
			
			if (((BlockScape) host).ground)
				xvelocity -= 150*((BlockScape)host).deltaTime;
		}
		
		if (xvelocity<0)
		{
			xvelocity += 30*((BlockScape)host).deltaTime;
			
			if (((BlockScape) host).ground)
				xvelocity += 150*((BlockScape)host).deltaTime;
		}
		
		if (xvelocity >= -120)
		{
			if (left)
			    xvelocity-=300*((BlockScape)host).deltaTime;
		}
		if (xvelocity <= 120)
		{
			if (right)
			    xvelocity += 300*((BlockScape)host).deltaTime;
		}
		
		if(yvelocity>1000)yvelocity=1000F;
		if(yvelocity<-1000)yvelocity=-1000F;
		
		if(xvelocity>1000)xvelocity=1000F;
		if(xvelocity<-1000)xvelocity=-1000F;
		if (xvelocity > -3 && xvelocity < 3)
			xvelocity = 0;
		boolean onGround = false;
		PVector leftSideHigh,rightSideHigh,leftSideLow,rightSideLow,topSide,bottomSide,groundCheck;
		leftSideHigh = new PVector();
		rightSideHigh = new PVector();
		leftSideLow = new PVector();
		rightSideLow = new PVector();
		topSide = new PVector();
		bottomSide = new PVector();
		groundCheck = new PVector();
		
		leftSideHigh.x = leftSideLow.x = getX();
		rightSideHigh.x = rightSideLow.x = getX()+getWidth();
		leftSideLow.y = rightSideLow.y = getY()+(getHeight()/3)*2;
		leftSideHigh.y = rightSideHigh.y = getY()+(getHeight()/3);
		
		topSide.x = groundCheck.x = bottomSide.x = getX()+getWidth()/2;
		topSide.y = getY();
		bottomSide.y = getY()+getHeight();
		groundCheck.y = bottomSide.y + 5;
		
		for(WorldBlock b: World.getWorld())
		{
			if(b.getBlock().getDoesPlayerCollide())
			{
				if(b.intersectsWith(leftSideHigh) || b.intersectsWith(leftSideLow))
				{
					setX(b.loc.x+b.width);
					xvelocity=0;
				}
				
				if(b.intersectsWith(rightSideHigh) || b.intersectsWith(rightSideLow))
				{
					setX(b.loc.x-getWidth());
					xvelocity=0;
				}
				
				if(b.intersectsWith(topSide))
				{
					setY(b.loc.y+b.height);
					setYvelocity(0);
				}
				
				if(b.intersectsWith(bottomSide))
				{
					setY(b.loc.y-getHeight());
					setYvelocity(0);
					
				}
				
				if(b.intersectsWith(groundCheck))
					onGround = true;
			}
		}
		
		if(onGround || BlockScape.isFlyMode)
			((BlockScape) host).ground = true;
		else
			((BlockScape) host).ground = false;
	}
	
	/**
	 * @return the x
	 */
	public static float getX()
	{
		return x;
	}
	
	/**
	 * @param x the x to set
	 */
	public static void setX(float x)
	{
		Player.x = x;
	}
	
	/**
	 * @return the y
	 */
	public static float getY()
	{
		return y;
	}
	
	/**
	 * @param y the y to set
	 */
	public static void setY(float y)
	{
		Player.y = y;
	}
	
	/**
	 * @return the yvelocity
	 */
	public static float getYvelocity()
	{
		return yvelocity;
	}
	
	/**
	 * @param yvelocity the yvelocity to set
	 */
	public static void setYvelocity(float yvelocity)
	{
		Player.yvelocity = yvelocity;
	}
	
	/**
	 * @return the width
	 */
	public static float getWidth()
	{
		return width;
	}
	
	/**
	 * @param width the width to set
	 */
	public static void setWidth(float width)
	{
		Player.width = width;
	}
	
	/**
	 * @return the height
	 */
	public static float getHeight()
	{
		return height;
	}
	
	/**
	 * @param height the height to set
	 */
	public static void setHeight(float height)
	{
		Player.height = height;
	}
}


