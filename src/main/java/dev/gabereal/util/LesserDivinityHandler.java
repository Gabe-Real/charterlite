package dev.gabereal.util;

import dev.gabereal.Charterlite;
import dev.gabereal.damage.ModDamageTypes;
import dev.gabereal.item.ModItems;
import dev.gabereal.particle.ModParticles;
import dev.gabereal.sound.ModSounds;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.impl.lib.tinyremapper.api.TrLogger;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.spongepowered.asm.logging.Level;
import team.lodestar.lodestone.handlers.ScreenshakeHandler;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.ParticleEffectSpawner;
import team.lodestar.lodestone.systems.particle.SimpleParticleOptions;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.screenshake.PositionedScreenshakeInstance;
import team.lodestar.lodestone.registry.common.particle.*;
import net.minecraft.network.PacketByteBuf;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;


import java.awt.*;

import static dev.gabereal.Charterlite.GOLDEN_BEAM_PACKET_ID;


public class LesserDivinityHandler {

	public static void register() {

		final int MAX_USES = 3;
		final int COOLDOWN_TICKS = 100;

		AttackEntityCallback.EVENT.register((player, world, hand, target, hitResult) -> {

			if (!(player instanceof ServerPlayerEntity)) return ActionResult.PASS;
			ServerPlayerEntity attacker = (ServerPlayerEntity) player;

			if (!(target instanceof LivingEntity)) return ActionResult.PASS;
			LivingEntity victim = (LivingEntity) target;

			if (!attacker.getOffHandStack().isOf(ModItems.LESSER_DIVINITY)) return ActionResult.PASS;

			// Check cooldown
			if (attacker.getItemCooldownManager().isCoolingDown(ModItems.LESSER_DIVINITY)) return ActionResult.PASS;


			ItemStack offhand = attacker.getOffHandStack();

			// Check remaining uses (durability)
			if (offhand.getDamage() >= offhand.getMaxDamage() - 1) {
				attacker.sendMessage(Text.literal("The Lesser Divinity has lost its power."), true);
				return ActionResult.PASS;
			}

			float damage = (float) attacker.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
			if (victim.getHealth() - damage <= 0) {
				handleLesserDivinityStrike(victim, world);


				// Apply durability
				offhand.damage(1, attacker, p -> p.sendToolBreakStatus(player.getActiveHand()));

				// Apply cooldown
				attacker.getItemCooldownManager().set(ModItems.LESSER_DIVINITY, COOLDOWN_TICKS);


				return ActionResult.FAIL; // cancel the kill
			}

			return ActionResult.PASS;
		});
	}

	private static void handleLesserDivinityStrike(LivingEntity target, World world) {
		// Spawn initial freeze effects and particles
		target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 255, false, false, false));
		target.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 60, 250, false, false, false));
		// target.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 250, false, false, false));

		if (world instanceof ServerWorld serverWorld) {
			Vec3d pos = target.getPos();
			// serverWorld.spawnParticles(ParticleTypes.END_ROD, pos.x, pos.y + 1, pos.z, 50, 0.5, 0.5, 0.5, 0.1);
			serverWorld.spawnParticles(
					dev.gabereal.particle.ModParticles.CHARTERED_PARTICLE,
					target.getX(),
					target.getBoundingBox().minY + 0.1,
					target.getZ(),
					1, // count
					0, 0, 0,
					0 // speed
			);
			serverWorld.playSound(null, target.getBlockPos(), ModSounds.LESSER_DIVINITY_STRIKE, SoundCategory.PLAYERS, 10.0f, 1.0f);


			ScreenshakeHandler.addScreenshake(
					new PositionedScreenshakeInstance(
							75,                         // Duration in ticks (3 seconds)
							pos,                        // Position to shake around
							25f, 30f,                   // Radius min/max
							Easing.SINE_IN_OUT          // Easing function
					).setIntensity(0f, 1.5f, 0f)    // X, Y, Z shake intensity
			);
			// Schedule delayed task after 3 seconds (60 ticks)
			LesserDivinityScheduler.schedule(30, () -> {
				// Spawn giant golden beam & explosion

				// however the fuck you do this

				// Kill with custom damage source
				target.damage(ModDamageTypes.repurpose(serverWorld), Float.MAX_VALUE); // or a large value like 9999f

				// serverWorld.playSound(null, target.getBlockPos(), ModSounds.LESSER_DIVINITY_STRIKE, SoundCategory.PLAYERS, 10.0f, 1.0f);

				// # Explosion
				// serverWorld.createExplosion(null, pos.x, pos.y, pos.z, 6.0f, true, World.ExplosionSourceType.TNT);
				// serverWorld.createExplosion(null, pos.x, pos.y, pos.z, 6.0f, true, World.ExplosionSourceType.TNT);
				// serverWorld.createExplosion(null, pos.x, pos.y, pos.z, 6.0f, true, World.ExplosionSourceType.TNT);

				serverWorld.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, pos.x, pos.y + 1, pos.z, 100, 1, 1, 1, 0.5);
			});
		}

		Charterlite.LOGGER.info("Lesser Divinity strike activated on " + target.getType().getName().getString());
	}
}
