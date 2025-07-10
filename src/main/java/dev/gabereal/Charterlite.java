package dev.gabereal;

import dev.gabereal.init.CharterliteObjects;
import dev.gabereal.item.ModItems;
import dev.gabereal.particle.ModParticles;
import dev.gabereal.sound.ModSounds;
import dev.gabereal.util.LesserDivinityHandler;
import dev.gabereal.util.LesserDivinityScheduler;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class Charterlite implements ModInitializer {
	public static final String MOD_ID = "charterlite";

	// Custom packets
	public static final Identifier GOLDEN_BEAM_PACKET_ID = new Identifier("charterlite", "golden_beam");
	public static final Identifier CHAIN_EFFECT_PACKET_ID = new Identifier("charterlite", "chain_effect");


	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		CharterliteObjects.init();
		GeckoLib.initialize();

		ModItems.registerModItems();
		ModSounds.registerSounds();
		ModParticles.registerParticles();

		LesserDivinityScheduler.init();
		LesserDivinityHandler.register();
	}
}