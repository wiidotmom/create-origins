package dev.igalaxy.createorigins;

import com.simibubi.create.AllTags;

import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.content.equipment.goggles.GogglesItem;

import dev.igalaxy.createorigins.condition.entity.NetheriteDivingGearCondition;
import io.github.apace100.apoli.mixin.EntityAccessor;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.origins.power.OriginsPowerTypes;
import net.fabricmc.api.ModInitializer;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

import static com.simibubi.create.content.equipment.armor.DivingHelmetItem.getWornItem;

public class CreateOrigins implements ModInitializer {
	public static final String ID = "createorigins";
	public static final String NAME = "Create: Origins";

	public static final PowerType<Power> GOGGLES = new PowerTypeReference<>(id("goggles"));

	@Override
	public void onInitialize() {
		GogglesItem.addIsWearingPredicate(GOGGLES::isActive);

		Registry.register(ApoliRegistries.ENTITY_CONDITION, NetheriteDivingGearCondition.getFactory().getSerializerId(), NetheriteDivingGearCondition.getFactory());
	}

	public static boolean merlingNeedsBacktank(LivingEntity merling) {
		return OriginsPowerTypes.WATER_BREATHING.isActive(merling) &&
				!(
						merling.isEyeInFluid(AllTags.AllFluidTags.DIVING_FLUIDS.tag) ||
						merling.hasEffect(MobEffects.CONDUIT_POWER) ||
						((EntityAccessor)merling).callIsBeingRainedOn()
				);
	}

	public static boolean hasFilledBacktank(LivingEntity entity) {
		ItemStack helmet = getWornItem(entity);
		if (helmet.isEmpty())
			return false;

		List<ItemStack> backtanks = BacktankUtil.getAllWithAir(entity);
		return !backtanks.isEmpty();
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(ID, path);
	}
}
