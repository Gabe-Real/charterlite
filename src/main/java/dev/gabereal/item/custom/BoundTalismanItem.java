package dev.gabereal.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BoundTalismanItem extends Item {
    public BoundTalismanItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (context.isAdvanced()) {
            tooltip.add(Text.literal("Forged in the depths of the Nether,").formatted(Formatting.DARK_RED));
            tooltip.add(Text.literal("this talisman binds itself to the bearer’s soul.").formatted(Formatting.DARK_RED));
            tooltip.add(Text.empty());
            tooltip.add(Text.literal("Death is not the end—but the beginning").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("of a darker bond.").formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.literal("Hold SHIFT to show lore").formatted(Formatting.GOLD, Formatting.ITALIC));
        }
    }
}
