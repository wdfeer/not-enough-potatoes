package org.wdfeer.not_enough_potatoes.material;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import static net.minecraft.entity.EquipmentSlot.FEET;
import static net.minecraft.entity.EquipmentSlot.LEGS;
import static net.minecraft.item.Items.CHEST;

public class PotatoMaterial implements ArmorMaterial {
    @Override
    public int getDurability(ArmorItem.Type type) {
        return 100;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        switch (type){
            case HELMET, BOOTS -> {
                return 1;
            }
            case CHESTPLATE, LEGGINGS ->{
                return  2;
            }
        }
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 8;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.POTATO);
    }

    @Override
    public String getName() {
        return "potato";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
