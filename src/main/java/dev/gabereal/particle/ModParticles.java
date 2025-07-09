package dev.gabereal.particle;

import dev.gabereal.Charterlite;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
	public static final DefaultParticleType CHARTERED_PARTICLE =
		registerParticle("chartered", FabricParticleTypes.simple());

	public static final DefaultParticleType DIVINITY_BEAM =
		registerParticle("divinity_beam", FabricParticleTypes.simple());


	private static DefaultParticleType registerParticle(String name, DefaultParticleType particleType) {
		return Registry.register(Registries.PARTICLE_TYPE, new Identifier(Charterlite.MOD_ID, name), particleType);
	}

	public static void registerParticles() {
		Charterlite.LOGGER.info("Registering Particles for " + Charterlite.MOD_ID);
	}
}
