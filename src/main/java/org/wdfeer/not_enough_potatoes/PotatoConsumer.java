package org.wdfeer.not_enough_potatoes;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public interface PotatoConsumer {
    void OnPotatoEaten(ItemStack stack);

    static void OnPotatoEaten(Entity user){
        user.getItemsEquipped().forEach(itemStack ->{
            if (itemStack.getItem() instanceof PotatoConsumer potatoEater)
                potatoEater.OnPotatoEaten(itemStack);
        });
    }
}
