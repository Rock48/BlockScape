package net.blockscape.block;

import net.blockscape.helper.IconHelper;

public class BlockCobble extends Block {

	public BlockCobble(int id) {
		super(id);
		texture = IconHelper.convertStringToBlockImage("cobblestone.png");
	}



}
