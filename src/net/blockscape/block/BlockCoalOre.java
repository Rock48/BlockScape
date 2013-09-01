package net.blockscape.block;

import net.blockscape.helper.IconHelper;

public class BlockCoalOre extends Block {

	public BlockCoalOre(int id) {
		super(id);
		texture = IconHelper.convertStringToBlockImage("coalOre.png");
	}

}
