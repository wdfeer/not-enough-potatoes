package org.wdfeer.not_enough_potatoes.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.wdfeer.not_enough_potatoes.material.PotatoMaterial;

import java.util.List;
import java.util.UUID;

public class PotatoArmorPiece extends ArmorItem implements PotatoConsumer {
    public PotatoArmorPiece(EquipmentSlot slot) {
        super(new PotatoMaterial(), slot, new Settings().group(ItemGroup.COMBAT));
        armorAttributeUuid = UUID.nameUUIDFromBytes(("potato_armor" + slot.getName()).getBytes());
    }

    public static final double[] PROTECTION_MULTIPLIERS = new double[] {1, 2, 3, 1};

    private final UUID armorAttributeUuid;
    public void onPotatoEaten(ItemStack stack){
        NbtCompound nbt = stack.getOrCreateNbt();
        int potatoes = nbt.getInt("potatoes_eaten") + 1;
        nbt.putInt("potatoes_eaten", potatoes);

        EntityAttributeModifier modifier = new EntityAttributeModifier(armorAttributeUuid,
                "generic.armor",
                Math.max(Math.log(potatoes) * PROTECTION_MULTIPLIERS[slot.getEntitySlotId()] / 3, 1),
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
