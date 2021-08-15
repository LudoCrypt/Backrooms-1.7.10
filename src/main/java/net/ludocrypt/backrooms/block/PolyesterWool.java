package net.ludocrypt.backrooms.block;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.group.ItemGroup;

public class PolyesterWool extends Block {

	public PolyesterWool() {
		super(Material.WOOL);
		this.setStrength(1.0F);
		this.setSound(Block.CLOTH);
		this.setTranslationKey("polyesterWool");
		this.setItemGroup(ItemGroup.BUILDING_BLOCKS);
		this.method_8634("backrooms:polyester_wool");
	}

}
