package dev.igalaxy.createorigins.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.simibubi.create.content.equipment.armor.DivingHelmetItem;

import dev.igalaxy.createorigins.CreateOrigins;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Debug(export = true)
@Mixin(value = Player.class, priority = 1500)
public class WaterBreathingUpdateAirMixinSquared {
	@SuppressWarnings({"MixinAnnotationTarget", "InvalidInjectorMethodSignature"}) // bug with mixin^2
	@TargetHandler(
			mixin = "io.github.apace100.origins.mixin.WaterBreathingMixin$UpdateAir",
			name = "tick"
	)
	@ModifyExpressionValue(
			method = "@MixinSquared:Handler",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/entity/player/Player;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"
			)
	)
	private boolean headUnderWater(boolean original) {
		LivingEntity entity = (LivingEntity) (Object) this;

		boolean needsBacktank = CreateOrigins.merlingNeedsBacktank(entity);
		boolean canNotBreathe = !DivingHelmetItem.canBreathe(entity);

		if(!(needsBacktank && canNotBreathe))
			return original;

		return CreateOrigins.hasFilledBacktank(entity);
	}
}
