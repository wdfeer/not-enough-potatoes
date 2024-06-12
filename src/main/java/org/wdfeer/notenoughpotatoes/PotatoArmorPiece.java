package org.wdfeer.notenoughpotatoes;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

public abstract class PotatoArmorPiece extends ArmorItem {
    public PotatoArmorPiece(ArmorItem.Type type, Settings settings) {
        super(Registries.ARMOR_MATERIAL.getEntry(PotatoMaterial.material), type, settings);
    }
}
