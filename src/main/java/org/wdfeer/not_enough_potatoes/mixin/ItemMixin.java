package org.wdfeer.not_enough_potatoes.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.wdfeer.not_enough_potatoes.PotatoArmorPiece;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "finishUsing", at = @At("RETURN"))
    private void injectFinishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir){
        if (stack.isOf(Items.POTATO) && user instanceof PlayerEntity player) {
            PotatoArmorPiece.OnPotatoEaten(player);
        }
    }
}
