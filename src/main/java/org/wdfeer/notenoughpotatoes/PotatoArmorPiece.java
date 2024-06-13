package org.wdfeer.notenoughpotatoes;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

public class PotatoArmorPiece extends ArmorItem {
    public PotatoArmorPiece(ArmorItem.Type type) {
        super(Registries.ARMOR_MATERIAL.getEntry(PotatoMaterial.material), type, new Settings());
    }

    public void OnPotatoEaten(ItemStack armor){

    }
}
