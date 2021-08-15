package net.ludocrypt.backrooms.block;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.group.ItemGroup;

public class BrittleConcreteBlock extends Block {

	public BrittleConcreteBlock() {
		super(Material.STONE);
		this.setStrength(3.5F);
		this.setSound(Block.STONE);
		this.setTranslationKey("brittleConcrete");
		this.setItemGroup(ItemGroup.BUILDING_BLOCKS);
		this.method_8634("backrooms:brittle_concrete");
	}

}
