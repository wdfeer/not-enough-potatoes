package org.wdfeer.notenoughpotatoes;

import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;

public class PotatoArmorPiece extends ArmorItem {
    public PotatoArmorPiece(ArmorItem.Type type) {
        super(PotatoMaterial.armorMaterialRegistryEntry, type, new Settings());
    }
}
