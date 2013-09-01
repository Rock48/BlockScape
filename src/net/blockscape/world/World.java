package net.blockscape.world;

import java.util.ArrayList;

import processing.core.PVector;

public class World {

	private static ArrayList<WorldBlock> blocks;
	
	public static void init(){
		blocks = new ArrayList<WorldBlock>();
	}
	/**
	 * returns the world arraylist
	 * @return the world
	 */
	public static ArrayList<WorldBlock> getWorld(){
		
		return blocks;
	}
	/**
	 * Adds a block to the world if a block doesnt exist at the given coords
	 * @param block WorldBlock to add
	 * @return if it succeeded with adding a block
	 */
	public static boolean addBlockWithoutReplacing(WorldBlock block){
		
		for(WorldBlock b: blocks){
			if(b.getCoords().equals(block.getCoords())){
				return false;
				
			}
			
		}
		blocks.add(block);
		return true;
	}
	/**
	 * removes a block from the world
	 * @param blockToRemove
	 */
	public static void removeBlockFromWorld(WorldBlock blockToRemove){
		blocks.remove(blockToRemove);
	}
	/**
	 * removes a block from the world with the index from the arraylist
	 * @param index
	 */
	public static void removeBlockFromWorld(int index){
		blocks.remove(index);
	}
	/**
	 * removes a block from the world with the given coords
	 * @param x
	 * @param y
	 */
	public static void removeBlockFromWorld(int x, int y){
		WorldBlock temp = null;
		for(WorldBlock b: blocks){
			if(b.getCoords().equals(new PVector(x,y))){
				temp = b;
				break;
			}
		}
		if(temp != null){
			blocks.remove(temp);
		}
	}
	/**
	 * replaces a block in the world
	 * @param block to set
	 */
	public static void setBlock(WorldBlock block){
		WorldBlock temp = null;
		for(WorldBlock b: blocks){
			if(b.getCoords().equals(block.getCoords())){
				temp = b;
				break;
			}
		}
		if(temp != null){
			blocks.remove(temp);
		}
		blocks.add(block);
	}
}
