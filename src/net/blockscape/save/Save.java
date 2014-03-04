package net.blockscape.save;

import java.util.ArrayList;

import net.blockscape.Player;
import net.blockscape.world.World;
import net.blockscape.world.WorldBlock;

import org.msgpack.annotation.Message;

@Message
public class Save {
	public float x, y, velx, vely;
	public int[][] block;
}
