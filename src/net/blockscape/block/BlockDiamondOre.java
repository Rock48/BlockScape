package net.blockscape.block;

import net.blockscape.helper.IconHelper;

public class BlockDiamondOre extends Block {

	public BlockDiamondOre(int id) {
		super(id);
		texture = IconHelper.convertStringToBlockImage("diamondOre.png");
	}

}
