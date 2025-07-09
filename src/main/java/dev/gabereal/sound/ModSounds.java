package dev.gabereal.sound;

import dev.gabereal.Charterlite;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

	public static final SoundEvent LESSER_DIVINITY_STRIKE = registerSoundEvent("lesser_divinity_strike");


	private static SoundEvent registerSoundEvent(String name) {
		Identifier identifier = new Identifier(Charterlite.MOD_ID, name);
		return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
	}

	public static void registerSounds() {
		Charterlite.LOGGER.info("Registering Mod Sounds for " + Charterlite.MOD_ID);
	}
}
