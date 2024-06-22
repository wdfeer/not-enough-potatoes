package org.wdfeer.not_enough_potatoes.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PotatoAdapter extends Item {
    public PotatoAdapter() {
        super(new Item.Settings().group(ItemGroup.MATERIALS));
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

//        return getAdaptable(user) == null ? TypedActionResult.fail(user.getActiveItem()) : TypedActionResult.success(user.getActiveItem());
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack adaptable = getAdaptable(user);

        if (adaptable == null) return super.finishUsing(stack, world, user);
        else {
//            adapt(stack, adaptable);

            return stack;
        }
    }

    private static void adapt(ItemStack stack, ItemStack armor){
        armor.getOrCreateNbt().putInt("potatoes_eaten", 0);

        stack.decrement(1);
    }
}
