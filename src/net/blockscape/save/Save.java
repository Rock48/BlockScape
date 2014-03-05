package net.blockscape.save;

import org.msgpack.annotation.Message;

@Message
public class Save
{
	public float x, y, velx, vely;
	public int[][] blocks;
}
