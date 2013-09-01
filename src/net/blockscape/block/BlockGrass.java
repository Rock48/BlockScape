package net.blockscape.block;

import net.blockscape.helper.IconHelper;

public class BlockGrass extends Block {

	
	public BlockGrass(int id) {
		super(id);
		texture = IconHelper.convertStringToBlockImage("grass.png");
	}
	
	
}
