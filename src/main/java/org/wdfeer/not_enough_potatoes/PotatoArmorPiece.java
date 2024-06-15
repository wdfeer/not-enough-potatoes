package org.wdfeer.not_enough_potatoes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

import java.util.Objects;

public class PotatoArmorPiece extends ArmorItem {
    public PotatoArmorPiece(ArmorItem.Type type) {
        super(Registries.ARMOR_MATERIAL.getEntry(PotatoMaterial.material), type, new Settings().component(PotatoCounterComponentType.INSTANCE, new PotatoCounterComponent(0)));
    }

    public static void OnPotatoEaten(PlayerEntity user){ // Accessed via an ItemMixin
        user.getArmorItems().forEach(s ->{
            if (s.getItem() instanceof PotatoArmorPiece potatoArmorPiece)
                potatoArmorPiece.OnPotatoEaten(s);
        });
    }

    private void OnPotatoEaten(ItemStack stack){
        PotatoCounterComponent counter = Objects.requireNonNull(stack.getComponents().get(PotatoCounterComponentType.INSTANCE));
        counter.count++;
    }
}
