package dev.gabereal.entity;

import dev.gabereal.init.CharterliteObjects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.explosion.Explosion;

import java.util.UUID;

public class SoulExplosionEntity extends Entity {

    public SoulExplosionEntity(EntityType<SoulExplosionEntity> soulExplosionEntityEntityType, World world) {
        super(CharterliteObjects.SOUL_EXPLOSION, world);
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        if (getWorld().isClient()) {
            for (int i = 1; i < (8); i++) {
                this.getWorld().addImportantParticle(ParticleTypes.LARGE_SMOKE,
                        this.getX() + random.nextGaussian(),
                        this.getY(),
                        this.getZ() + random.nextGaussian(),
                        0, 0.1, 0);
                this.getWorld().addImportantParticle(ParticleTypes.LARGE_SMOKE,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        MathHelper.cos(i/4f * 3.141592f + (this.age + random.nextFloat()) / 10f) / 2f, 0.2, MathHelper.sin(i/4f * 3.141592f + (this.age + random.nextFloat()) / 10f) / 2f);
                this.getWorld().addImportantParticle(ParticleTypes.LARGE_SMOKE,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        MathHelper.cos(i/4f * 3.141592f + (this.age + random.nextFloat()) / 10f) / 2f, 0.2, MathHelper.sin(i/4f * 3.141592f + (this.age + random.nextFloat()) / 10f) / 2f);
                this.getWorld().addImportantParticle(ParticleTypes.SOUL_FIRE_FLAME,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        (random.nextFloat()-0.5f) / 4, 1f + random.nextFloat(), (random.nextFloat()-0.5f) / 4);
                this.getWorld().addImportantParticle(ParticleTypes.SOUL_FIRE_FLAME,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        (random.nextFloat()-0.5f) / 4, 1f + random.nextFloat(), (random.nextFloat()-0.5f) / 4);
                this.getWorld().addImportantParticle(ParticleTypes.SOUL,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        (random.nextFloat()-0.5f) / 4, 3f + random.nextFloat(), (random.nextFloat()-0.5f) / 4);
            }
        } else {
            this.getWorld().createExplosion(this, this.getX() + (random.nextFloat()-0.5f) * 15, this.getY() - (MathHelper.abs((float) random.nextFloat()) * 140) + 10, this.getZ() + (random.nextFloat()-0.5f) * 15, 12.0f, true, World.ExplosionSourceType.MOB);
            if(this.age > 600) {
                this.getWorld().syncGlobalEvent(WorldEvents.WITHER_SPAWNS, this.getBlockPos(), 0);
                LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(this.getWorld());
                lightningEntity.refreshPositionAfterTeleport(this.getPos());
                getWorld().spawnEntity(lightningEntity);
                this.remove(RemovalReason.DISCARDED);
            }
        }
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public net.minecraft.network.packet.Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
}