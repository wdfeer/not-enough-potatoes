package org.wdfeer.notenoughpotatoes;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

public class PotatoItems {
    public static Item[] items = new Item[] {
            new PotatoArmorPiece(ArmorItem.Type.HELMET)
    };
    public static void Initialize(){
        for (Item item : items) {
            Registries.ITEM.createEntry(item);
        }
    }
}
