package dev.igalaxy.createorigins.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.simibubi.create.content.equipment.armor.DivingHelmetItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = Player.class, priority = 1500)
public class WaterBreathingUpdateAirMixinSquared {
	@TargetHandler(
			mixin = "io.github.apace100.origins.mixin.WaterBreathingMixin$UpdateAir",
			name = "tick"
	)
	@ModifyExpressionValue(
			method = "@MixinSquared:Handler",
			at = @At(
					value = "INVOKE",
					target = "Lio/github/apace100/origins/mixin/WaterBreathingMixin$UpdateAir;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"
			)
	)
	private boolean headUnderWater(boolean original) {
		if (DivingHelmetItem.breatheUnderwater((LivingEntity) (Object) this))
			return false;

		return original;
	}
}
