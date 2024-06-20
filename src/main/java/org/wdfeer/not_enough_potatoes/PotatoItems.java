package org.wdfeer.not_enough_potatoes;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PotatoItems {
    public static Item[] items;

    private static Identifier GetIdentifier(String name){
        return new Identifier(PotatoMod.MOD_ID, name);
    }

    public static void Initialize(){
        items = new Item[] {
                Registry.register(Registry.ITEM, GetIdentifier("potato_helmet"), new PotatoArmorPiece(EquipmentSlot.HEAD)),
                Registry.register(Registry.ITEM, GetIdentifier("potato_chestplate"), new PotatoArmorPiece(EquipmentSlot.CHEST)),
                Registry.register(Registry.ITEM, GetIdentifier("potato_leggings"), new PotatoArmorPiece(EquipmentSlot.LEGS)),
                Registry.register(Registry.ITEM, GetIdentifier("potato_boots"), new PotatoArmorPiece(EquipmentSlot.FEET)),
                Registry.register(Registry.ITEM, GetIdentifier("potato_adapter"), new Item(new Item.Settings().group(ItemGroup.MATERIALS)))
        };
    }
}
