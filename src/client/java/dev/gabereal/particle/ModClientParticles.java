package dev.gabereal.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;


public class ModClientParticles {
	public static void register() {
		ParticleFactoryRegistry.getInstance().register(
			ModParticles.CHARTERED_PARTICLE,
			CharteredParticle.Factory::new
		);
	}
}
