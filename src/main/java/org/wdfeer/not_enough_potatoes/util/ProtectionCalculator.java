package org.wdfeer.not_enough_potatoes.util;

import net.minecraft.entity.EquipmentSlot;
import org.wdfeer.not_enough_potatoes.config.ProtectionConfig;

public class ProtectionCalculator {
    public static final double[] PROTECTION_MULTIPLIERS = new double[] {1/3d, 2/3d, 1, 1/3d};

    public static double getPotatoArmorProtection(int potatoes, EquipmentSlot slot){
        return getProtection(potatoes, ProtectionConfig.potatoArmorLogBase, slot);
    }

    public static double getPotatoShardProtection(int potatoes, EquipmentSlot slot){
        return getProtection(potatoes, ProtectionConfig.potatoShardLogBase, slot);
    }

    private static double getProtection(int potatoes, double logBase, EquipmentSlot slot){
        final int min = 0;
        return Math.max(Math.log(potatoes + 1)/Math.log(logBase) * PROTECTION_MULTIPLIERS[slot.getEntitySlotId()], min);
    }
}
