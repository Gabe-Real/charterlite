package dev.gabereal;

import dev.gabereal.particle.ModClientParticles;
import dev.gabereal.particle.lodestone.GoldenBeamParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import static dev.gabereal.Charterlite.GOLDEN_BEAM_PACKET_ID;

public class CharterliteClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModClientParticles.register();

		ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> new ModelIdentifier(Charterlite.MOD_ID, "dusks_epitaph", "inventory"));
	}
}