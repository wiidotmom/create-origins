package dev.igalaxy.createorigins.condition.entity;

import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import static com.simibubi.create.content.equipment.armor.NetheriteDivingHandler.NETHERITE_DIVING_BITS_KEY;

public class NetheriteDivingGearCondition {
	public static boolean condition(SerializableData.Instance data, Entity entity) {
		CompoundTag nbt = entity.getCustomData();
		byte bits = nbt.getByte(NETHERITE_DIVING_BITS_KEY);

		return (bits & 0b1111) == 0b1111;
	}

	public static ConditionFactory<Entity> getFactory() {
		return new ConditionFactory<>(
				new ResourceLocation("createorigins:netherite_diving_gear"),
				new SerializableData(),
				NetheriteDivingGearCondition::condition
		);
	}
}
