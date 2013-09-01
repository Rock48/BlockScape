package net.blockscape.block;

import net.blockscape.helper.IconHelper;

public class BlockStone extends Block{

	public BlockStone(int id) {
		super(id);
		texture = IconHelper.convertStringToBlockImage("stone.png");
	}
	
}
