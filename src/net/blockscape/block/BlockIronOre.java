package net.blockscape.block;

import net.blockscape.helper.IconHelper;

public class BlockIronOre extends Block {

	public BlockIronOre(int id) {
		super(id);
		texture = IconHelper.convertStringToBlockImage("ironOre.png");
	}

}
