package net.blockscape.helper;

import processing.core.PApplet;
import net.blockscape.block.Block;
import net.blockscape.world.World;
import net.blockscape.world.WorldBlock;

public class TerrainGenerationHelper
{

	/**
	 * generates the world
	 * @param host the game window
	 */
	public static void generateWorld(PApplet host)
	{
		float h = host.random(0.01F, 0.09F);
		int ymh;
		float n;
		float j = host.random(0, 5000);
		
		for (int x = 0; x < host.width / 16 + 1; x++)
		{
			j += h;
			n = host.noise(j);
			ymh = (int) (PApplet.map(n, 0, 1, 0, host.height / 16) + 0.5);
			float tree = host.random(0, 100);
			
			    
			for (int y = 0; y < host.height / 16; y++)
			{
				float oreGenChances = host.random(0, 100);
				
				if (y == ymh)
					World.setBlock(new WorldBlock(x, y, Block.blockGrass, host));
				else if (y < ymh && y > ymh - 4)
					World.setBlock(new WorldBlock(x, y, Block.blockDirt, host));
				else if (y <= ymh - 4)
				{
					if(oreGenChances <= 4)
					    World.setBlock(new WorldBlock(x, y, Block.oreCoal, host));
					else if(oreGenChances <= 6 && y <= 16)
					    World.setBlock(new WorldBlock(x, y, Block.oreIron, host));
					else if(oreGenChances <= 6.5 && y <= 8)
					    World.setBlock(new WorldBlock(x, y, Block.oreDiamond, host));
					else
					    World.setBlock(new WorldBlock(x, y, Block.blockStone, host));
				}
			}
			
			if(tree < 10)
				generateTree(x, (int) (ymh + 1), host);
		}
	}
	
	/**
	 * generates a tree
	 * @param x of base
	 * @param y of base
	 * @param host game window
	 */
	public static void generateTree(int x, int y,PApplet host)
	{
		World.setBlock(new WorldBlock(x, 0 + y, Block.blockLog, host));
		World.setBlock(new WorldBlock(x, 1 + y, Block.blockLog, host));
		World.setBlock(new WorldBlock(x, 2 + y, Block.blockLog, host));
		World.setBlock(new WorldBlock(x, 3 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x, 4 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x, 5 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x, 6 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x - 1, 3 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x - 2, 3 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x - 1, 4 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x - 2, 4 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x - 1, 5 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x + 2, 3 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x + 1, 3 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x + 2, 4 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x + 1, 4 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x + 1, 5 + y, Block.blockLeaves, host));
		World.setBlock(new WorldBlock(x, y - 1, Block.blockDirt, host));
	}
}
