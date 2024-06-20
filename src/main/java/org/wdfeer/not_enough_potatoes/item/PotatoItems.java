package org.wdfeer.not_enough_potatoes.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.wdfeer.not_enough_potatoes.PotatoMod;

public class PotatoItems {
    public static Item[] items;

    private static Identifier getIdentifier(String name){
        return new Identifier(PotatoMod.MOD_ID, name);
    }

    public static void initialize(){
        items = new Item[] {
                Registry.register(Registry.ITEM, getIdentifier("potato_helmet"), new PotatoArmorPiece(EquipmentSlot.HEAD)),
                Registry.register(Registry.ITEM, getIdentifier("potato_chestplate"), new PotatoArmorPiece(EquipmentSlot.CHEST)),
                Registry.register(Registry.ITEM, getIdentifier("potato_leggings"), new PotatoArmorPiece(EquipmentSlot.LEGS)),
                Registry.register(Registry.ITEM, getIdentifier("potato_boots"), new PotatoArmorPiece(EquipmentSlot.FEET)),
                Registry.register(Registry.ITEM, getIdentifier("potato_adapter"), new Item(new Item.Settings().group(ItemGroup.MATERIALS)))
        };
    }
}
