package org.wdfeer.not_enough_potatoes;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.wdfeer.not_enough_potatoes.item.PotatoConsumer;

import java.util.List;

public class DispenserBehavior {
    public static void initialize(){
        var behavior = new FallibleItemDispenserBehavior() {
            @Override
            public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                this.setSuccess(DispensePotato(pointer, stack));
                return stack;
            }
        };

        for (int i = 0; i < PotatoConsumer.POTATOES.length; i++) {
            DispenserBlock.registerBehavior(PotatoConsumer.POTATOES[i], behavior);
        }
    }

    static boolean DispensePotato(BlockPointer pointer, ItemStack potato){
        BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
        List<Entity> list = pointer.getWorld().getEntitiesByClass(Entity.class,
                new Box(blockPos),
                EntityPredicates.EXCEPT_SPECTATOR.and(entity -> {
                    var armors = entity.getArmorItems();
                    for (ItemStack stack : armors){
                        if (PotatoConsumer.isPotatoConsumer(stack)) return true;
                    }
                    return false;
                }));

        if (list.isEmpty()) {
            return false;
        }

        LivingEntity livingEntity = (LivingEntity)list.get(0);
        PotatoConsumer.onPotatoEaten(livingEntity);
        potato.decrement(1);

        return true;
    }
}
