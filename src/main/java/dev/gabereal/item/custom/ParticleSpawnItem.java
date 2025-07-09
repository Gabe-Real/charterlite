package dev.gabereal.item.custom;

import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ParticleSpawnItem extends Item {

	private static final ParticleEmitterInfo HERALD = new ParticleEmitterInfo(
			new Identifier("charterlite", "herald")
	);

	public ParticleSpawnItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos blockPos = context.getBlockPos();

		if (!world.isClient()) {
			AAALevel.addParticle(
					world,
					false,
					HERALD.clone().position(
							blockPos.getX() + 0.5d,
							blockPos.getY() + 1d,
							blockPos.getZ() + 0.5d
					)
			);
		}

		return ActionResult.SUCCESS;
	}
}
