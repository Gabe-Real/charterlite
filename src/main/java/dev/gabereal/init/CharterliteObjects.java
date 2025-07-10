package dev.gabereal.init;

import dev.gabereal.entity.*;
import dev.gabereal.Charterlite;
import dev.gabereal.misc.EntityAttributes;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class CharterliteObjects {
    private static final Map<EntityType<?>, Identifier> ENTITY_TYPES = new LinkedHashMap<>();

    public static final EntityType<ChainsEntity> CHAINS = createEntity("chains", EntityAttributes.createChainAttributes(), FabricEntityTypeBuilder.create(SpawnGroup.MISC, ChainsEntity::new).dimensions(EntityDimensions.fixed(0.4F, 0.8F)).build());
    public static final EntityType<SoulExplosionEntity> SOUL_EXPLOSION = createEntity("soul_explosion", FabricEntityTypeBuilder.create(SpawnGroup.MISC, SoulExplosionEntity::new).trackRangeBlocks(10).dimensions(EntityDimensions.fixed(0.9f, 1.8F)).build());


    private static <T extends Entity> EntityType<T> createEntity(String name, EntityType<T> type) {
        ENTITY_TYPES.put(type, new Identifier(Charterlite.MOD_ID, name));
        return type;
    }

    private static <T extends LivingEntity> EntityType<T> createEntity(String name, DefaultAttributeContainer.Builder attributes, EntityType<T> type) {
        FabricDefaultAttributeRegistry.register(type, attributes);
        ENTITY_TYPES.put(type, new Identifier(Charterlite.MOD_ID, name));
        return type;
    }
    private static <T extends LivingEntity> EntityType<T> createRaven(String name, DefaultAttributeContainer.Builder attributes, EntityType<T> type) {
        FabricDefaultAttributeRegistry.register(type, attributes);
        ENTITY_TYPES.put(type, new Identifier("tot", name));
        return type;
    }
    public static void init() {
        ENTITY_TYPES.keySet().forEach(entityType -> Registry.register(Registries.ENTITY_TYPE, ENTITY_TYPES.get(entityType), entityType));
    }
}

