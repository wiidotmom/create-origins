package dev.igalaxy.createorigins.mixin;

import com.bawnorton.mixinsquared.TargetHandler;

import com.simibubi.create.AllItems;

import com.simibubi.create.content.equipment.armor.DivingBootsItem;

import io.github.apace100.apoli.power.PowerType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = LivingEntity.class, priority = 1500)
public abstract class LikeWaterMixinSquared {
	@TargetHandler(
			mixin = "io.github.apace100.origins.mixin.LikeWaterMixin",
			name = "method_26317Proxy"
	)
	@Redirect(
		method = "@MixinSquared:Handler",
		at = @At(
				value = "INVOKE",
				target = "io/github/apace100/apoli/power/PowerType.isActive(Lnet/minecraft/world/entity/Entity;)Z"
		)
	)
	private boolean isActive(PowerType instance, Entity entity) {
		if (entity instanceof LivingEntity && instance.isActive(entity)) {
			ItemStack boots = ((LivingEntity) entity).getItemBySlot(EquipmentSlot.FEET);
			if (boots.getItem() instanceof DivingBootsItem) {
				return false;
			}
		}
		return instance.isActive(entity);
	}
}
