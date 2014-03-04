package net.blockscape.save;

import java.util.ArrayList;

import net.blockscape.BlockScape;
import net.blockscape.Player;
import net.blockscape.world.World;
import net.blockscape.world.WorldBlock;

public class WorldSave
{
    private String dispName;
    public ArrayList<WorldBlock> blocks;
    public Player player;
    public Save save;
    
    public WorldSave(String dispName_, World w, Player player_)
    {
        this.dispName = dispName_;
        this.blocks = World.getWorld();
        this.player = player_;
        this.save = new Save();
        save.x = player.x;
        save.y = player.y;
        save.velx = player.xvelocity;
        save.vely = player.yvelocity;
        save.block = new int[BlockScape.instance.width/16+1][BlockScape.instance.height/16];
        
        for (int x = 0; x < BlockScape.instance.width/16+1; x++)
		{
			for (int y = 0; y < BlockScape.instance.height/16; y++)
			{
				save.block[x][y] = World.getBlock(x, y).getBlock().blockID;
			}
		}
    }
    public WorldSave(Save s)
    {
    	
    }
    
    public WorldSave(String dispName_)
    {
        this.dispName = dispName_;
        blocks = new ArrayList<WorldBlock>();
    }
    
    public String getName()
    {
        return dispName;
    }
    
    public void setName(String newName)
    {
        this.dispName = newName;
    }
    
}
