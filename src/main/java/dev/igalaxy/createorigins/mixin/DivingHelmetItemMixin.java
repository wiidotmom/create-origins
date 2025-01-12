package dev.igalaxy.createorigins.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.simibubi.create.content.equipment.armor.DivingHelmetItem;

import dev.igalaxy.createorigins.CreateOrigins;
import io.github.apace100.origins.power.OriginsPowerTypes;
import net.minecraft.world.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DivingHelmetItem.class)
public class DivingHelmetItemMixin {
	@ModifyExpressionValue(method = "breatheUnderwater", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;isFireResistant()Z"))
	private static boolean shouldNotProvideAirInLava(boolean original, LivingEntity entity) {
		return original || CreateOrigins.merlingNeedsBacktank(entity);
	}

	@ModifyExpressionValue(method = "breatheUnderwater", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z", ordinal = 0))
	private static boolean shouldProvideAir(boolean original, LivingEntity entity) {
		if(OriginsPowerTypes.WATER_BREATHING.isActive(entity)) {
			return CreateOrigins.merlingNeedsBacktank(entity);
		}
		return original;
	}

	@ModifyVariable(method = "breatheUnderwater", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/equipment/armor/BacktankUtil;consumeAir(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;F)V"), ordinal = 2)
	private static boolean modifyLavaDiving(boolean lavaDiving, LivingEntity entity) {
		return lavaDiving && !CreateOrigins.merlingNeedsBacktank(entity);
	}
}
