package net.ludocrypt.backrooms.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.util.IdList;

@Mixin(IdList.class)
public interface IdListAccessor {
	@Accessor("list")
	List<?> getList();
}
