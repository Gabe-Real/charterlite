package dev.gabereal.particle.lodestone;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;

import java.awt.*;

public class GoldenBeamParticle {

    public static void spawnGoldenBeam(World level, Vec3d pos) {
        // Beam core (WISP)
        WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE.get())
                .setScaleData(GenericParticleData.create(0.5f, 0f).setEasing(Easing.QUAD_IN).build())
                .setColorData(ColorParticleData.create(new Color(255,223,0), new Color(255,180,0)).build())
                .setTransparencyData(GenericParticleData.create(0.8f, 0f).build())
                .setLifetime(40)
                .addMotion(0, -0.5f, 0)
                .enableNoClip()
                .spawn(level, pos.x, pos.y + 20, pos.z);

        // Impact flash (STAR)
        WorldParticleBuilder.create(LodestoneParticleRegistry.STAR_PARTICLE.get())
                .setScaleData(GenericParticleData.create(1.5f, 0f).setEasing(Easing.CIRC_OUT).build())
                .setColorData(ColorParticleData.create(Color.WHITE, new Color(255,223,0)).build())
                .setTransparencyData(GenericParticleData.create(1f, 0f).build())
                .setLifetime(15)
                .spawn(level, pos.x, pos.y + 1, pos.z);

        // Energy Sparks
        for (int i = 0; i < 20; i++) {
            Vec3d offset = Vec3d.unpackRgb((int) (Math.random() * 0xFFFFFF)).normalize().multiply(1.5);
            WorldParticleBuilder.create(LodestoneParticleRegistry.SPARK_PARTICLE.get())
                    .setScaleData(GenericParticleData.create(0.3f, 0f).build())
                    .setColorData(ColorParticleData.create(new Color(255,200,0), new Color(255,100,0)).build())
                    .setTransparencyData(GenericParticleData.create(0.9f, 0f).build())
                    .setLifetime(30)
                    .addMotion(offset.x * 0.2, 0.1, offset.z * 0.2)
                    .spawn(level, pos.x, pos.y + 1, pos.z);
        }

        // Aftershock Twinkles
        WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE.get())
                .setScaleData(GenericParticleData.create(0.2f, 0f).setEasing(Easing.SINE_IN_OUT).build())
                .setColorData(ColorParticleData.create(new Color(255,223,0), new Color(255,223,0)).build())
                .setTransparencyData(GenericParticleData.create(0.5f, 0f).build())
                .setLifetime(50)
                .spawn(level, pos.x, pos.y + 1, pos.z);
    }
}
