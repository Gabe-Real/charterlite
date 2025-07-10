package dev.gabereal.entity.interfaces;

import dev.gabereal.entity.ChainsEntity;
import net.minecraft.util.Identifier;

public interface ChainsEntityInterface {
    Identifier getModelResource(ChainsEntity object);

    Identifier getTextureResource(ChainsEntity object);

    Identifier getAnimationResource(ChainsEntity animatable);
}
