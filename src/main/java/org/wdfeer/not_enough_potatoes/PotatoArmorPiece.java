package org.wdfeer.not_enough_potatoes;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class PotatoArmorPiece extends ArmorItem {
    public PotatoArmorPiece(EquipmentSlot slot) {
        super(new PotatoMaterial(), slot, new Settings());
    }

    public static void OnPotatoEaten(PlayerEntity user){
        user.getArmorItems().forEach(s ->{
            if (s.getItem() instanceof PotatoArmorPiece potatoArmorPiece)
                potatoArmorPiece.OnPotatoEaten(s);
        });
    }

    private void OnPotatoEaten(ItemStack stack){
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt("potatoes_eaten", nbt.getInt("potatoes_eaten") + 1);
    }
}
