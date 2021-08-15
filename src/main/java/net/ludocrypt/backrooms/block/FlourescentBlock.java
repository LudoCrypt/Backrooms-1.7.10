package net.ludocrypt.backrooms.block;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.group.ItemGroup;

public class FlourescentBlock extends Block {

	public FlourescentBlock() {
		super(Material.GLASS);
		this.setLightLevel(1.0F);
		this.setSound(Block.GLASS);
		this.setStrength(2.0F);
		this.setItemGroup(ItemGroup.REDSTONE);
		this.setTranslationKey("flourescentLight");
		this.method_8634("backrooms:flourescent_light");
	}

}
