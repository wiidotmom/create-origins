package dev.igalaxy.createorigins;

import com.simibubi.create.Create;

import com.simibubi.create.content.equipment.goggles.GogglesItem;

import dev.igalaxy.createorigins.condition.entity.NetheriteDivingGearCondition;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.apace100.apoli.registry.ApoliRegistries;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateOrigins implements ModInitializer {
	public static final String ID = "createorigins";
	public static final String NAME = "Create: Origins";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	public static final PowerType<Power> GOGGLES = new PowerTypeReference<>(id("goggles"));
	public static final PowerType<Power> GIRL_POWER = new PowerTypeReference<>(id("girl_power"));

	@Override
	public void onInitialize() {
		LOGGER.info("Create addon mod [{}] is loading alongside Create [{}]!", NAME, Create.VERSION);

		GogglesItem.addIsWearingPredicate(GOGGLES::isActive);
		if (FabricLoader.getInstance().isModLoaded("estrogen")) {

		}

		Registry.register(ApoliRegistries.ENTITY_CONDITION, NetheriteDivingGearCondition.getFactory().getSerializerId(), NetheriteDivingGearCondition.getFactory());
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(ID, path);
	}
}
