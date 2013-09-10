package net.blockscape.block;

import net.blockscape.helper.IconHelper;
import net.blockscape.registry.GameRegistry;
import processing.core.PImage;

public class Block
{

	protected PImage texture;
	public int blockID;
	private String name;
	private boolean doesPlayerCollide;
	private boolean isBreakable;
	
	//Blocks
	public static Block blockStone;
	public static Block blockGrass;
	public static Block blockDirt;
	public static Block blockCobble;
	public static Block blockLog;
	public static Block blockLeaves;
	public static Block oreIron;
	public static Block oreCoal;
	public static Block oreDiamond;
	public static Block blockBlueThing;
	
	
	/**
	 * Ininitalized all blocks
	 */
	public static void blockInit()
	{
		blockStone = new BlockStone(1).setName("Stone");
		blockGrass = new BlockGrass(2).setName("Grass");
		blockDirt = new BlockDirt(3).setName("Dirt");
		blockCobble = new BlockCobble(4).setName("Cobblestone");
		blockLog = new BlockLog(5).setName("Wooden Log").setDoesPlayerCollide(false);
		blockLeaves = new BlockLeaves(6).setName("Leaves");
		oreIron = new BlockIronOre(7).setName("Iron Ore");
		oreCoal = new BlockCoalOre(8).setName("Coal Ore");
		oreDiamond = new BlockDiamondOre(9).setName("Diamond Ore");
		blockBlueThing = new BlockBlue(10).setName("Blue Thing");
		
		regInit();
	}
	
	/**
	 * adds blocks to block registry
	 */
	private static void regInit()
	{
		GameRegistry.registerBlock(blockStone);
		GameRegistry.registerBlock(blockGrass);
		GameRegistry.registerBlock(blockDirt);
		GameRegistry.registerBlock(blockCobble);
		GameRegistry.registerBlock(blockLog);
		GameRegistry.registerBlock(blockLeaves);
		GameRegistry.registerBlock(oreIron);
		GameRegistry.registerBlock(oreCoal);
		GameRegistry.registerBlock(oreDiamond);
		GameRegistry.registerBlock(blockBlueThing);
	}
	
	/**
	 * Creates a new block with defualts. id req
	 * @param id
	 */
	public Block(int id)
	{
		blockID = id;
		name = "undefined";
		texture = IconHelper.convertStringToBlockImage("default.png");
		doesPlayerCollide = true;
		isBreakable = true;
	}
	
	/**
	 * Returns the texture for the block
	 * @return texture
	 */
	public PImage getTexture()
	{
		return texture;
	}
	
	/**
	 * Returns the name of the block
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets the name and returns the block
	 * @param name
	 * @return self
	 */
	public Block setName(String name)
	{
		this.name = name;
		return this;
	}

	/**
	 * Returns if the player collides with the block
	 * @return the doesPlayerCollide
	 */
	public boolean getDoesPlayerCollide()
	{
		return doesPlayerCollide;
	}


	/**
	 * sets if the player will collide with this block. Defults to true.
	 * @param doesPlayerCollide
	 */
	public Block setDoesPlayerCollide(boolean doesPlayerCollide)
	{
		this.doesPlayerCollide = doesPlayerCollide;
		return this;
	}
	
	/**
	 * 
	 * @return Whether or not the block can be broken by the player
	 */
	public boolean isBlockBreakable()
	{
	    return this.isBreakable;
	}
	
	/**     
	 * Sets the block to be unbreakable 
	 */
	public void setBlockUnbreakable()
	{
	    this.isBreakable = false;
	}
	
	/**     
     * Sets the block to be breakable (only needed if you want to make an unbreakable block breakable for some reason)
     */
    public void setBlockBreakable()
    {
        this.isBreakable = true;
    }
	
}
