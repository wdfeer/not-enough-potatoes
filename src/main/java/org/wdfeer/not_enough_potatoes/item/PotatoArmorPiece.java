package org.wdfeer.not_enough_potatoes.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.wdfeer.not_enough_potatoes.material.PotatoMaterial;

import java.util.UUID;

import static org.wdfeer.not_enough_potatoes.util.AttributeHelper.addDefaultArmorAttributes;
import static org.wdfeer.not_enough_potatoes.util.AttributeHelper.removeAttributeWithUuid;

public class PotatoArmorPiece extends ArmorItem implements PotatoConsumer {
    public PotatoArmorPiece(EquipmentSlot slot) {
        super(new PotatoMaterial(), slot, new Settings().group(ItemGroup.COMBAT));
        armorAttributeUuid = UUID.nameUUIDFromBytes(("potato_armor" + slot.getName()).getBytes());
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        stack.getOrCreateNbt().putInt("potatoes_eaten", 0);
    }

    public static final double[] PROTECTION_MULTIPLIERS = new double[] {1/3d, 2/3d, 1, 1/3d};

    private final UUID armorAttributeUuid;
    public void onPotatoEaten(ItemStack stack){
        NbtCompound nbt = stack.getOrCreateNbt();
        int potatoes = nbt.getInt("potatoes_eaten") + 1;
        nbt.putInt("potatoes_eaten", potatoes);

        setGenericArmor(stack, armorAttributeUuid, getProtection(potatoes));

        stack.setDamage(0);
    }

    private double getProtection(int potatoes){
        return getProtection(potatoes, Math.E, slot);
    }

    public static double getProtection(int potatoes, double logBase, EquipmentSlot slot){
        final int min = 0;
        return Math.max(Math.log(potatoes)/Math.log(logBase) * PROTECTION_MULTIPLIERS[slot.getEntitySlotId()], min);
    }

    public static void setGenericArmor(ItemStack stack, UUID modifierUuid, double protection){
        ArmorItem armor = (ArmorItem) stack.getItem();
        EquipmentSlot slot = (armor).getSlotType();

        removeAttributeWithUuid(stack, modifierUuid);
        if (protection > 0) {
            EntityAttributeModifier potatoArmorAttribute = new EntityAttributeModifier(modifierUuid,
                    "generic.armor",
                    protection,
                    EntityAttributeModifier.Operation.ADDITION);

            stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, potatoArmorAttribute, slot);
        }

        addDefaultArmorAttributes(stack, armor);
    }


    public static Text getTooltip(int potatoes){
        return Text.translatable("not_enough_potatoes.potatoes_eaten").append(Integer.toString(potatoes)).formatted(Formatting.YELLOW);
    }

    public static Text getTooltip(ItemStack stack){
        return getTooltip(stack.getOrCreateNbt().getInt("potatoes_eaten"));
    }
}
