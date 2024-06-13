package org.wdfeer.notenoughpotatoes;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PotatoItems {
    public static Item[] items;

    private static Identifier GetIdentifier(String name){
        return new Identifier(PotatoMod.MOD_ID, name);
    }

    public static void Initialize(){
        items = new Item[] {
                Registry.register(Registries.ITEM, GetIdentifier("potato_helmet"), new PotatoArmorPiece(ArmorItem.Type.HELMET)),
                Registry.register(Registries.ITEM, GetIdentifier("potato_chestplate"), new PotatoArmorPiece(ArmorItem.Type.CHESTPLATE)),
                Registry.register(Registries.ITEM, GetIdentifier("potato_leggings"), new PotatoArmorPiece(ArmorItem.Type.LEGGINGS)),
                Registry.register(Registries.ITEM, GetIdentifier("potato_boots"), new PotatoArmorPiece(ArmorItem.Type.BOOTS))
        };
    }
}
