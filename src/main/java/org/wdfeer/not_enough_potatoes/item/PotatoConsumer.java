package org.wdfeer.not_enough_potatoes.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public interface PotatoConsumer {
    void OnPotatoEaten(ItemStack stack);

    Item[] POTATOES = new Item[] {Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO};

    static void OnPotatoEaten(Entity user){
        user.getItemsEquipped().forEach(itemStack ->{
            if (itemStack.getItem() instanceof PotatoConsumer potatoEater)
                potatoEater.OnPotatoEaten(itemStack);
        });
    }
}
