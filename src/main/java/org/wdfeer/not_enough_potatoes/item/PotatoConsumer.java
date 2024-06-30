package org.wdfeer.not_enough_potatoes.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;

import java.util.UUID;

import static org.wdfeer.not_enough_potatoes.util.ProtectionCalculator.getPotatoShardProtection;

public interface PotatoConsumer {
    void onPotatoEaten(ItemStack stack);

    Item[] POTATOES = new Item[] {Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO};

    static void onPotatoEaten(Entity user){
        user.getItemsEquipped().forEach(stack ->{
            if (stack.getItem() instanceof PotatoConsumer potatoEater)
                potatoEater.onPotatoEaten(stack);
            else if (isPotatoConsumer(stack)) {
                NbtCompound nbt = stack.getNbt();
                assert nbt != null;
                int potatoes = nbt.getInt("potatoes_eaten") + 1;
                nbt.putInt("potatoes_eaten", potatoes);
                
                UUID modifierUuid = UUID.nameUUIDFromBytes(("potato_adapted" + stack.getItem().getName()).getBytes());
                PotatoArmorPiece.setGenericArmor(stack, modifierUuid, getPotatoShardProtection(potatoes, ((ArmorItem)stack.getItem()).getSlotType()));
            }
        });
    }

    static boolean isPotatoConsumer(ItemStack stack){
        return stack.getItem() instanceof PotatoConsumer || (stack.getItem() instanceof ArmorItem && stack.hasNbt() && stack.getOrCreateNbt().contains("potatoes_eaten"));
    }
}
