package org.wdfeer.not_enough_potatoes.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.wdfeer.not_enough_potatoes.PotatoMod;

public class PotatoItems {
    public static Item[] items;
    public static Item potatoAdapter;

    private static Identifier getIdentifier(String name){
        return new Identifier(PotatoMod.MOD_ID, name);
    }

    public static void initialize(){
        potatoAdapter = Registry.register(Registry.ITEM, getIdentifier("potato_adapter"), new PotatoAdapter());
        items = new Item[] {
                Registry.register(Registry.ITEM, getIdentifier("potato_helmet"), new PotatoArmorPiece(EquipmentSlot.HEAD)),
                Registry.register(Registry.ITEM, getIdentifier("potato_chestplate"), new PotatoArmorPiece(EquipmentSlot.CHEST)),
                Registry.register(Registry.ITEM, getIdentifier("potato_leggings"), new PotatoArmorPiece(EquipmentSlot.LEGS)),
                Registry.register(Registry.ITEM, getIdentifier("potato_boots"), new PotatoArmorPiece(EquipmentSlot.FEET)),
                potatoAdapter
        };
    }
}
