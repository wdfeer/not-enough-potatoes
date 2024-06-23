package org.wdfeer.not_enough_potatoes.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;

import java.util.UUID;

public final class AttributeHelper {
    static NbtList getAttributeModifiers(NbtCompound head){
        return head.getList("AttributeModifiers", 10);
    }

    static int getIndexWithUuid(NbtList attributes, UUID uuid){
        int length = attributes.size();
        for (int i = 0; i < length; i++) {
            NbtElement uuidNbt = attributes.getCompound(i).get("UUID");
            if (uuidNbt != null && uuid.equals(NbtHelper.toUuid(uuidNbt))) {
                return i;
            }
        }
        return -1;
    }

    public static void removeAttributeWithUuid(NbtList attributes, UUID uuid){
        int index = AttributeHelper.getIndexWithUuid(attributes, uuid);
        if (index != -1)
            attributes.remove(index);
    }

    public static void removeAttributeWithUuid(ItemStack stack, UUID uuid){
        removeAttributeWithUuid(getAttributeModifiers(stack.getOrCreateNbt()), uuid);
    }

    public static void addDefaultArmorAttributes(ItemStack stack, ArmorItem armor) {
        EquipmentSlot slot = armor.getSlotType();
        armor.getAttributeModifiers(slot).forEach((entityAttribute, entityAttributeModifier) -> {
            if (!stack.getAttributeModifiers(slot).containsEntry(entityAttribute, entityAttributeModifier))
                stack.addAttributeModifier(entityAttribute, entityAttributeModifier, slot);
        });
    }
}
