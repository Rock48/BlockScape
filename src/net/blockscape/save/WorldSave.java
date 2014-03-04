package net.blockscape.save;

import java.util.ArrayList;

import net.blockscape.Player;
import net.blockscape.world.WorldBlock;

public class WorldSave
{
    private String dispName;
    public ArrayList<WorldBlock> blocks;
    public Player player;
    
    public WorldSave(String dispName_, ArrayList<WorldBlock> blocks_, Player player_)
    {
        this.dispName = dispName_;
        this.blocks = blocks_;
        this.player = player_;
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
