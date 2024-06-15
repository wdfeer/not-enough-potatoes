package org.wdfeer.not_enough_potatoes;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Saddleable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.List;

public class DispenserBehavior {
    public static void Initialize(){
        for (int i = 0; i < PotatoArmorPiece.POTATOES.length; i++) {
            DispenserBlock.registerBehavior(PotatoArmorPiece.POTATOES[i], new FallibleItemDispenserBehavior() {
                @Override
                public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                    this.setSuccess(DispensePotato(pointer, stack));
                    return stack;
                }
            });
        }
    }

    static boolean DispensePotato(BlockPointer pointer, ItemStack potato){
        BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
        List<Entity> list = pointer.getWorld().getEntitiesByClass(Entity.class,
                new Box(blockPos),
                EntityPredicates.EXCEPT_SPECTATOR.and(entity -> {
                    var armors = entity.getArmorItems();
                    for (ItemStack stack : armors){
                        if (stack.getItem() instanceof  PotatoArmorPiece) return true;
                    }
                    return false;
                }));

        if (list.isEmpty()) {
            return false;
        }

        LivingEntity livingEntity = (LivingEntity)list.get(0);
        PotatoArmorPiece.OnPotatoEaten(livingEntity);
        return true;
    }
}
