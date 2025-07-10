package dev.gabereal.entity;

import dev.gabereal.entity.interfaces.ChainsEntityRendererInterface;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ChainsEntityRenderer extends GeoEntityRenderer<dev.gabereal.entity.ChainsEntity> implements ChainsEntityRendererInterface {
    public ChainsEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ChainsEntityModel());
    }

    @Override
    public void render(dev.gabereal.entity.ChainsEntity chainsEntity, float entityYaw, float partialTicks, MatrixStack stack, VertexConsumerProvider provider, int packedLightIn) {
        super.render(chainsEntity, entityYaw, partialTicks, stack, provider, 15728880);
    }

    @Override
    public RenderLayer getRenderType(dev.gabereal.entity.ChainsEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(this.getTexture(animatable));
    }

    @Override
    public Identifier getTexture(dev.gabereal.entity.ChainsEntity chainsEntity) {
        return this.getTexture(chainsEntity);
    }
}
