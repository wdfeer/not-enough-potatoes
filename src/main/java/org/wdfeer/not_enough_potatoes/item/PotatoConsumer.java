package org.wdfeer.not_enough_potatoes.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;

public interface PotatoConsumer {
    void onPotatoEaten(ItemStack stack);

    Item[] POTATOES = new Item[] {Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO};

    static void onPotatoEaten(Entity user){
        user.getItemsEquipped().forEach(itemStack ->{
            if (itemStack.getItem() instanceof PotatoConsumer potatoEater)
                potatoEater.onPotatoEaten(itemStack);
            else if (itemStack.getOrCreateNbt().contains("potatoes_eaten")) {
                NbtCompound nbt = itemStack.getNbt();
                assert nbt != null;
                nbt.putInt("potatoes_eaten", nbt.getInt("potatoes_eaten") + 1);
            }
        });
    }
}
