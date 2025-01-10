package dev.igalaxy.createorigins.mixin;

import com.simibubi.create.content.equipment.armor.DivingHelmetItem;

import io.github.apace100.origins.power.OriginsPowerTypes;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.level.material.Fluid;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DivingHelmetItem.class)
public class DivingHelmetItemMixin {
	@Redirect(method = "breatheUnderwater", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z", ordinal = 0))
	private static boolean modifyBreatheUnderwater(LivingEntity entity, TagKey<Fluid> tagKey) {
		if (OriginsPowerTypes.WATER_BREATHING.isActive(entity)) {
			return !(entity.isEyeInFluid(tagKey) || entity.hasEffect(MobEffects.CONDUIT_POWER) || entity.level().isRainingAt(entity.blockPosition()));
		}
		return entity.isEyeInFluid(tagKey);
	}
}
