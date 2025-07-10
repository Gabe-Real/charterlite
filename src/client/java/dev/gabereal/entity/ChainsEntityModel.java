package dev.gabereal.entity;

import dev.gabereal.Charterlite;
import dev.gabereal.entity.interfaces.ChainsEntityInterface;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.model.GeoModel;

public class ChainsEntityModel extends GeoModel<dev.gabereal.entity.ChainsEntity> implements ChainsEntityInterface, GeoAnimatable {
    @Override
    public Identifier getModelResource(ChainsEntity object) {
        return new Identifier(Charterlite.MOD_ID, "geo/entity/chains.geo.json");
    }

    @Override
    public Identifier getTextureResource(ChainsEntity object) {
        return new Identifier(Charterlite.MOD_ID, "textures/entity/chains.png");
    }

    @Override
    public Identifier getAnimationResource(ChainsEntity animatable) {
        return new Identifier(Charterlite.MOD_ID, "animations/entity/chains.animation.json");
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return null;
    }

    @Override
    public double getTick(Object o) {
        return 0;
    }
}
