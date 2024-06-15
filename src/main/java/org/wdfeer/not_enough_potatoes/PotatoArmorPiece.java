package org.wdfeer.not_enough_potatoes;

import net.minecraft.client.item.BundleTooltipData;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PotatoArmorPiece extends ArmorItem {
    public PotatoArmorPiece(EquipmentSlot slot) {
        super(new PotatoMaterial(), slot, new Settings().group(ItemGroup.COMBAT));
    }

    public static void OnPotatoEaten(World world, PlayerEntity user){
        user.getArmorItems().forEach(armor ->{
            if (armor.getItem() instanceof PotatoArmorPiece potatoArmorPiece)
                potatoArmorPiece.OnPotatoEaten(world, armor);
        });
    }

    final UUID ARMOR_ATTRIBUTE_UUID = UUID.nameUUIDFromBytes("potato_armor".getBytes());
    private void OnPotatoEaten(World world, ItemStack stack){
        NbtCompound nbt = stack.getOrCreateNbt();
        int potatoes = nbt.getInt("potatoes_eaten") + 1;
        nbt.putInt("potatoes_eaten", potatoes);

        EntityAttributeModifier modifier = new EntityAttributeModifier(ARMOR_ATTRIBUTE_UUID,
                "generic.armor",
                potatoes,
                EntityAttributeModifier.Operation.ADDITION);

        nbt.remove("AttributeModifiers");
        stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, modifier, slot);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("Potatoes eaten: " + stack.getOrCreateNbt().get("potatoes_eaten")));
    }
}
