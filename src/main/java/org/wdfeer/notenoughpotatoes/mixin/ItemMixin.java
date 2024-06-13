package org.wdfeer.notenoughpotatoes.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.wdfeer.notenoughpotatoes.PotatoArmorPiece;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "use", at = @At("HEAD"))
    private void injectItemUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){
        ItemStack itemStack = user.getStackInHand(hand);
        FoodComponent foodComponent = (FoodComponent)itemStack.get(DataComponentTypes.FOOD);
        if (itemStack.getItem() == Items.POTATO && foodComponent != null && user.canConsume(foodComponent.canAlwaysEat())) {
            user.getArmorItems().forEach((ItemStack s) -> {
                if (s.getItem() instanceof PotatoArmorPiece potatoArmor) {
                    potatoArmor.OnPotatoEaten(s);
                }
            });
        }
    }
}
