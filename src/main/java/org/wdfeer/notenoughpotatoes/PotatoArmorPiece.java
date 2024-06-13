package org.wdfeer.notenoughpotatoes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

public class PotatoArmorPiece extends ArmorItem {
    public PotatoArmorPiece(ArmorItem.Type type) {
        super(Registries.ARMOR_MATERIAL.getEntry(PotatoMaterial.material), type, new Settings());
    }

    public static void OnPotatoEaten(PlayerEntity user){
        user.getArmorItems().forEach(s ->{
            if (s.getItem() instanceof PotatoArmorPiece potatoArmorPiece)
                user.animateDamage(2);
        });
    }
}
