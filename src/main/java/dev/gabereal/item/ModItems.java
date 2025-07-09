package dev.gabereal.item;

import dev.gabereal.Charterlite;
import dev.gabereal.item.custom.ParticleSpawnItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
	public static final Item LESSER_DIVINITY = registerItem("lesser_divinity",
		new Item(new FabricItemSettings().maxDamage(3)));

	public static final Item DUSKS_EPITAPH = registerItem("dusks_epitaph_small",
			new Item(new FabricItemSettings().maxCount(1)));

	public static final Item PARTICLE_ITEM = registerItem("particle_spawn_item",
		new ParticleSpawnItem(new FabricItemSettings().maxCount(1)));


	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(Charterlite.MOD_ID, name), item);
	}

	private static void itemGroupIngredients(FabricItemGroupEntries entries) {
		entries.add(LESSER_DIVINITY);
		entries.add(PARTICLE_ITEM);
		entries.add(DUSKS_EPITAPH);
	}

	public static void registerModItems() {
		Charterlite.LOGGER.info("Registering Mod Items for " + Charterlite.MOD_ID);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::itemGroupIngredients);

	}
}
