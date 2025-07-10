package dev.gabereal.misc;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;

public class EntityAttributes {
    public static DefaultAttributeContainer.Builder createChainAttributes() {
        return MobEntity.createMobAttributes().add(net.minecraft.entity.attribute.EntityAttributes.GENERIC_MAX_HEALTH, 10).add(net.minecraft.entity.attribute.EntityAttributes.GENERIC_ATTACK_DAMAGE, 2).add(net.minecraft.entity.attribute.EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3).add(net.minecraft.entity.attribute.EntityAttributes.GENERIC_FLYING_SPEED, 0.7);
    }
}
