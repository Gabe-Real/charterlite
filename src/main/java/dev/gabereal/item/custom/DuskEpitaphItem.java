package dev.gabereal.item.custom;

import dev.gabereal.Charterlite;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class DuskEpitaphItem extends SwordItem {

    public DuskEpitaphItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient) {
            // Check if this hit would kill the target
            float damage = attacker instanceof PlayerEntity player
                    ? (float) player.getAttributeValue(net.minecraft.entity.attribute.EntityAttributes.GENERIC_ATTACK_DAMAGE)
                    : 2.0f;

            if (target.getHealth() - damage <= 0) {
                // Prevent death and freeze target (we'll implement this later)
                freezeTargetWithChains(target);
                return false; // cancel damage
            }
        }

        return super.postHit(stack, target, attacker);
    }

    private void freezeTargetWithChains(LivingEntity target) {
        // Freeze the target
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 255, false, false, false));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 100, 250, false, false, false));
        target.setVelocity(0, 0, 0); // Stop movement

        // Send packet to client to spawn the chain effect
        if (target instanceof ServerPlayerEntity serverPlayer) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeDouble(target.getX());
            buf.writeDouble(target.getY());
            buf.writeDouble(target.getZ());

            ServerPlayNetworking.send(serverPlayer, Charterlite.CHAIN_EFFECT_PACKET_ID, buf);
        } else if (target.getWorld() instanceof ServerWorld serverWorld) {
            for (ServerPlayerEntity player : serverWorld.getPlayers()) {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeDouble(target.getX());
                buf.writeDouble(target.getY());
                buf.writeDouble(target.getZ());

                ServerPlayNetworking.send(player, Charterlite.CHAIN_EFFECT_PACKET_ID, buf);
            }
        }
    }
}
