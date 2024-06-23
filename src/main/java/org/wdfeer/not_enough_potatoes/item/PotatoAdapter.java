package org.wdfeer.not_enough_potatoes.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PotatoAdapter extends Item {
    public PotatoAdapter() {
        super(new FabricItemSettings());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.not_enough_potatoes.potato_adapter.tooltip").formatted(Formatting.YELLOW));
    }

    @Nullable
    ItemStack getAdaptable(LivingEntity user){
        for (ItemStack armor : user.getArmorItems()) {
            if (!(armor.getItem() instanceof ArmorItem) || (armor.hasNbt() && armor.getOrCreateNbt().contains("potatoes_eaten")))
                continue;
            return armor;
        }
        return null;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack adaptable = getAdaptable(user);
        ItemStack stack = user.getStackInHand(hand);

        if (adaptable == null) return TypedActionResult.fail(stack);
        else {
            adapt(stack, adaptable);

            return TypedActionResult.success(stack);
        }
    }

    private static void adapt(ItemStack stack, ItemStack armor){
        armor.getOrCreateNbt().putInt("potatoes_eaten", 0);

        stack.decrement(1);
    }
}
