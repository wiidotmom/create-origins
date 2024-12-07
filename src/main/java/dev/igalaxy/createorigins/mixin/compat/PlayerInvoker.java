package dev.igalaxy.createorigins.mixin.compat;

import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Player.class)
public interface PlayerInvoker {
	@Invoker("addEffect")
	public void invokeAddEffect(int x, int y, int z);
}
