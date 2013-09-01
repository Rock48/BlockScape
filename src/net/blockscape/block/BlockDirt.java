package net.blockscape.block;

import net.blockscape.helper.IconHelper;

public class BlockDirt extends Block{

	public BlockDirt(int id) {
		super(id);
		texture = IconHelper.convertStringToBlockImage("dirt.png");
	}

}
