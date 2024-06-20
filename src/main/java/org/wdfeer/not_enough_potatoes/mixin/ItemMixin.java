package org.wdfeer.not_enough_potatoes.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.wdfeer.not_enough_potatoes.item.PotatoArmorPiece;
import org.wdfeer.not_enough_potatoes.item.PotatoConsumer;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "finishUsing", at = @At("HEAD"))
    private void injectFinishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir){
        if (Arrays.stream(PotatoConsumer.POTATOES).anyMatch(stack::isOf) && user instanceof PlayerEntity) {
            PotatoConsumer.onPotatoEaten(user);
        }
    }

    @Inject(method = "appendTooltip", at = @At("HEAD"))
    private void injectAppendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci){
        if (stack.hasNbt() && Objects.requireNonNull(stack.getNbt()).contains("potatoes_eaten")){
            tooltip.add(PotatoArmorPiece.getTooltip(stack));
        }
    }
}
