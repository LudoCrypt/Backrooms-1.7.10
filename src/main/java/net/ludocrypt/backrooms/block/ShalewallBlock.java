package net.ludocrypt.backrooms.block;

import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.Material;
import net.minecraft.item.group.ItemGroup;

public class ShalewallBlock extends FallingBlock {

	public ShalewallBlock() {
		super(Material.STONE);
		this.setUnbreakable();
		this.setResistance(6000000.0F);
		this.setSound(Block.STONE);
		this.setTranslationKey("shalewall");
		this.disableStats();
		this.setItemGroup(ItemGroup.BUILDING_BLOCKS);
		this.method_8634("backrooms:shalewall");
	}

}
