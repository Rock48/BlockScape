package net.blockscape.registry;

import java.util.ArrayList;

import net.blockscape.block.Block;

public class GameRegistry {

	private static ArrayList<Block> blocks;
	/**
	 * initializes the registries
	 */
	public static void initialize(){
		blocks = new ArrayList<Block>();
	}
	/**
	 * adds a new block to the block registry
	 * @param block to add
	 */
	public static void registerBlock(Block block){
		if(block!=null){
			blocks.add(block);
		}else{
			throw new NullPointerException();
		}
	}
	/**
	 * removes a block with the given blockID from the registry
	 * @param blockID to remove
	 */
	public static void unregisterBlock(int blockID){
		Block buffer = null;
		for(Block b: blocks){
			if(blockID == b.blockID){
				buffer = b;
			}
		}
		if(buffer!=null){
			blocks.remove(buffer);
			System.out.println("Successfully removed "+buffer.getName());
		}else{
			throw new RuntimeException("Block not found");
		}
	}
	/**
	 * gets a block from the registry with the given block id
	 * @param blockID of the block to retrieve
	 * @return the block with the given block id, if the id is invalid, returns null
	 */
	public static Block getBlock(int blockID){
		for(Block b: blocks){
			if(blockID == b.blockID){
				return b;
			}
		}
		throw new RuntimeException("Invalid Block ID");
	}
	/**
	 * returns the size of the block registry
	 * @return the size of the block registry
	 */
	public static int getBlockRegistrySize(){
		return blocks.size();
	}
}
