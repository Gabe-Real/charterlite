package dev.gabereal.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class CharteredParticle extends SpriteBillboardParticle {
	private final double originX, originY, originZ;

	protected CharteredParticle(ClientWorld world, double x, double y, double z, double vx, double vy, double vz, SpriteProvider spriteProvider) {
		super(world, x, y, z, vx, vy, vz);
		// this.maxAge = 65;
		this.setSprite(spriteProvider);

		this.originX = x;
		this.originY = y;
		this.originZ = z;


		this.maxAge = 67;
		this.scale = 0.0f;
		this.alpha = 0.0f;

		this.gravityStrength = 0;
		this.velocityX = 0;
		this.velocityY = 0;
		this.velocityZ = 0;
	}

	@Override
	public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
		Vec3d camPos = camera.getPos();
		float px = (float)(MathHelper.lerp(tickDelta, this.prevPosX, this.x) - camPos.x);
		float py = (float)(MathHelper.lerp(tickDelta, this.prevPosY, this.y) - camPos.y - 0.01f);
		float pz = (float)(MathHelper.lerp(tickDelta, this.prevPosZ, this.z) - camPos.z);

		float halfSize = this.scale / 2.0f;

		float minU = this.getMinU();
		float maxU = this.getMaxU();
		float minV = this.getMinV();
		float maxV = this.getMaxV();

		int light = this.getBrightness(tickDelta);
		float r = this.red;
		float g = this.green;
		float b = this.blue;
		float a = this.alpha;

		vertexConsumer.vertex(px - halfSize, py, pz - halfSize)
				.texture(minU, maxV)
				.color((int)(r * 255), (int)(g * 255), (int)(b * 255), (int)(a * 255))
				.light(light)
				.normal(0, 1, 0)
				.next();

		vertexConsumer.vertex(px - halfSize, py, pz + halfSize)
				.texture(minU, minV)
				.color((int)(r * 255), (int)(g * 255), (int)(b * 255), (int)(a * 255))
				.light(light)
				.normal(0, 1, 0)
				.next();

		vertexConsumer.vertex(px + halfSize, py, pz + halfSize)
				.texture(maxU, minV)
				.color((int)(r * 255), (int)(g * 255), (int)(b * 255), (int)(a * 255))
				.light(light)
				.normal(0, 1, 0)
				.next();

		vertexConsumer.vertex(px + halfSize, py, pz - halfSize)
				.texture(maxU, maxV)
				.color((int)(r * 255), (int)(g * 255), (int)(b * 255), (int)(a * 255))
				.light(light)
				.normal(0, 1, 0)
				.next();
	}




	@Override
	public void tick() {
		super.tick();

		if (age <= 5) {
			// Stage 0: Fade in + scale from 0 → 1.0
			float progress = age / 5.0f;
			this.scale = progress;
			this.alpha = progress;
		} else if (age <= 23) {
			// Stage 1: Hold at scale 1.0
			this.scale = 1.0f;
			this.alpha = 1.0f;
		} else if (age <= 35) {
			// Stage 2: Rapid scale up to 5.0
			float progress = (age - 23) / 6.0f;
			this.scale = 1.0f + progress * 4.0f;
			this.alpha = 1.0f;
		} else {
			// Stage 3: Shrink and fade out
			float progress = (age - 35) / 40.0f;
			this.scale = 5.0f * (1.0f - progress);
			this.alpha = 1.0f - progress;
		}
	}





	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}

	public static class Factory implements ParticleFactory<DefaultParticleType> {
		private final SpriteProvider sprites;

		public Factory(SpriteProvider spriteProvider) {
			this.sprites = spriteProvider;
		}

		public Particle createParticle(DefaultParticleType particleType, ClientWorld clientWorld,
									   double x, double y, double z, double xd, double yd, double zd) {
			return new CharteredParticle(clientWorld, x, y, z, xd, yd, zd, this.sprites);
		}
	}
}
