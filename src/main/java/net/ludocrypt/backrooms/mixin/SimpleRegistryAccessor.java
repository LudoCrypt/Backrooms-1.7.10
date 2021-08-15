package net.ludocrypt.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.util.IdList;
import net.minecraft.util.registry.SimpleRegistry;

@Mixin(SimpleRegistry.class)
public interface SimpleRegistryAccessor {
	@Accessor("ids")
	IdList getIds();
}
