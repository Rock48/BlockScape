package net.blockscape.block;

import net.blockscape.helper.IconHelper;

public class BlockLeaves extends Block {

	public BlockLeaves(int id) {
		super(id);
		texture = IconHelper.convertStringToBlockImage("leaf.png");
	}

}
