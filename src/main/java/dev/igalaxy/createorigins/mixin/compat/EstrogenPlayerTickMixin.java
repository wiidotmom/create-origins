package dev.igalaxy.createorigins.mixin.compat;

import dev.mayaqq.estrogen.registry.EstrogenEffects;
import dev.mayaqq.estrogen.registry.effects.EstrogenEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class EstrogenPlayerTickMixin {
	@Inject(method = "tick", at = @At("TAIL"))
	private void createorigins$tick(CallbackInfo ci) {
		IPowerCont
		((LivingEntity)(Object) this).addEffect(new MobEffectInstance(EstrogenEffects.ESTROGEN_EFFECT.get(), 200, 0, true, false, true));
	}
}
