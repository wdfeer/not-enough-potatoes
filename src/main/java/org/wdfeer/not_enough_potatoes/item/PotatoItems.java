package org.wdfeer.not_enough_potatoes.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.wdfeer.not_enough_potatoes.PotatoMod;

public class PotatoItems {
    public static Item[] items;
    public static Item potatoAdapter;

    private static Identifier getIdentifier(String name){
        return new Identifier(PotatoMod.MOD_ID, name);
    }

    public static void initialize(){
        potatoAdapter = Registry.register(Registries.ITEM, getIdentifier("potato_adapter"), new PotatoAdapter());
        items = new Item[] {
                Registry.register(Registries.ITEM, getIdentifier("potato_helmet"), new PotatoArmorPiece(ArmorItem.Type.HELMET)),
                Registry.register(Registries.ITEM, getIdentifier("potato_chestplate"), new PotatoArmorPiece(ArmorItem.Type.CHESTPLATE)),
                Registry.register(Registries.ITEM, getIdentifier("potato_leggings"), new PotatoArmorPiece(ArmorItem.Type.LEGGINGS)),
                Registry.register(Registries.ITEM, getIdentifier("potato_boots"), new PotatoArmorPiece(ArmorItem.Type.BOOTS)),
                potatoAdapter
        };

        for (Item item : items){
            if (item instanceof GroupedItem groupedItem){
                ItemGroupEvents.modifyEntriesEvent(groupedItem.getItemGroup()).register(content -> content.add(item));
            }
        }
    }
}
