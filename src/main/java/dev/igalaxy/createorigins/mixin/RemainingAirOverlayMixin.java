package dev.igalaxy.createorigins.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.equipment.armor.RemainingAirOverlay;

import dev.igalaxy.createorigins.CreateOrigins;
import net.minecraft.client.player.LocalPlayer;

import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Debug(export = true)
@Mixin(RemainingAirOverlay.class)
public class RemainingAirOverlayMixin {
	@ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z", ordinal = 0))
	private static boolean modifyRenderRemainingAirOverlay(boolean original, @Local(name = "player") LocalPlayer player) {
		return original || CreateOrigins.merlingNeedsBacktank(player);
	}
}
