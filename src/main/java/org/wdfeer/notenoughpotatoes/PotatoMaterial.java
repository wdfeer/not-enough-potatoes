package org.wdfeer.notenoughpotatoes;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;

import java.util.HashMap;

public class PotatoMaterial {
    public static ArmorMaterial armorMaterial;
    public static RegistryEntry<ArmorMaterial> armorMaterialRegistryEntry;

    public static void Initialize(){
        HashMap<ArmorItem.Type, Integer> defenseMap = new HashMap<>();
        defenseMap.put(ArmorItem.Type.HELMET, 1);
        defenseMap.put(ArmorItem.Type.CHESTPLATE, 2);
        defenseMap.put(ArmorItem.Type.LEGGINGS, 2);
        defenseMap.put(ArmorItem.Type.BOOTS, 1);

        armorMaterial = new ArmorMaterial(defenseMap,
                8,
                SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
                () -> Ingredient.ofItems(Items.POTATO),
                null,
                0F,
                0F);

        armorMaterialRegistryEntry = Registries.ARMOR_MATERIAL.createEntry(armorMaterial);
    }
}
