package org.wdfeer.not_enough_potatoes;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class PotatoArmorPiece extends ArmorItem {
    public PotatoArmorPiece(EquipmentSlot slot) {
        super(new PotatoMaterial(), slot, new Settings().group(ItemGroup.COMBAT));
        armorAttributeUuid = UUID.nameUUIDFromBytes(("potato_armor" + slot.getName()).getBytes());
    }

    public static void OnPotatoEaten(PlayerEntity user){
        user.getArmorItems().forEach(armor ->{
            if (armor.getItem() instanceof PotatoArmorPiece potatoArmorPiece)
                potatoArmorPiece.OnPotatoEaten(armor);
        });
    }

    private final UUID armorAttributeUuid;
    private void OnPotatoEaten(ItemStack stack){
        NbtCompound nbt = stack.getOrCreateNbt();
        int potatoes = nbt.getInt("potatoes_eaten") + 1;
        nbt.putInt("potatoes_eaten", potatoes);

        EntityAttributeModifier modifier = new EntityAttributeModifier(armorAttributeUuid,
                "generic.armor",
                Math.max(Math.log(potatoes), 1),
                EntityAttributeModifier.Operation.ADDITION);

        nbt.remove("AttributeModifiers");
        stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, modifier, slot);

        stack.setDamage(0);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("Potatoes eaten: " + stack.getOrCreateNbt().get("potatoes_eaten")));
    }
}
