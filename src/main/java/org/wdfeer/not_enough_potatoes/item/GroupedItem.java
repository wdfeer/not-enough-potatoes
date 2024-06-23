package org.wdfeer.not_enough_potatoes.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;

public interface GroupedItem {
    RegistryKey<ItemGroup> getItemGroup();
}
