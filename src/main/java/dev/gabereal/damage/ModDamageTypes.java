package dev.gabereal.damage;

import dev.gabereal.Charterlite;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.server.world.ServerWorld;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> REPURPOSE_DAMAGE_TYPE =
            RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Charterlite.MOD_ID, "repurpose"));

    public static DamageSource repurpose(ServerWorld world) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(REPURPOSE_DAMAGE_TYPE));
    }
}
