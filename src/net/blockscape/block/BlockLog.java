package net.blockscape.block;

import net.blockscape.helper.IconHelper;

public class BlockLog extends Block {

	public BlockLog(int id) {
		super(id);
		texture = IconHelper.convertStringToBlockImage("log.png");
	}

}
