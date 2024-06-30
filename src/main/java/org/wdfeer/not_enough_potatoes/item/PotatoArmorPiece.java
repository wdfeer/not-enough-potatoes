package org.wdfeer.not_enough_potatoes.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.wdfeer.not_enough_potatoes.material.PotatoMaterial;

import java.util.UUID;

import static org.wdfeer.not_enough_potatoes.util.AttributeHelper.addDefaultArmorAttributes;
import static org.wdfeer.not_enough_potatoes.util.AttributeHelper.removeAttributeWithUuid;
import static org.wdfeer.not_enough_potatoes.util.ProtectionCalculator.getPotatoArmorProtection;

public class PotatoArmorPiece extends ArmorItem implements PotatoConsumer, GroupedItem {
    public PotatoArmorPiece(Type slot) {
        super(new PotatoMaterial(), slot, new FabricItemSettings());
        armorAttributeUuid = UUID.nameUUIDFromBytes(("potato_armor" + slot.getName()).getBytes());
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        stack.getOrCreateNbt().putInt("potatoes_eaten", 0);
    }


    private final UUID armorAttributeUuid;
    public void onPotatoEaten(ItemStack stack){
        NbtCompound nbt = stack.getOrCreateNbt();
        int potatoes = nbt.getInt("potatoes_eaten") + 1;
        nbt.putInt("potatoes_eaten", potatoes);

        setGenericArmor(stack, armorAttributeUuid, getProtection(potatoes));

        stack.setDamage(0);
    }

    private double getProtection(int potatoes){
        return getPotatoArmorProtection(potatoes, type.getEquipmentSlot());
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

    @Override
    public RegistryKey<ItemGroup> getItemGroup() {
        return ItemGroups.COMBAT;
    }
}
