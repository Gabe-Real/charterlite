package dev.gabereal;

import dev.gabereal.entity.ChainsEntityRenderer;
import dev.gabereal.entity.SoulExplosionRenderer;
import dev.gabereal.init.CharterliteObjects;
import dev.gabereal.particle.ModClientParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.util.ModelIdentifier;

public class CharterliteClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModClientParticles.register();

		EntityRendererRegistry.register(CharterliteObjects.SOUL_EXPLOSION, SoulExplosionRenderer::new);
		EntityRendererRegistry.register(CharterliteObjects.CHAINS, ChainsEntityRenderer::new);

		ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> new ModelIdentifier(Charterlite.MOD_ID, "dusks_epitaph", "inventory"));
	}
}
