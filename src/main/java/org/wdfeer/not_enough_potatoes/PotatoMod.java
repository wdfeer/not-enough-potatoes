package org.wdfeer.not_enough_potatoes;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import org.wdfeer.not_enough_potatoes.config.ProtectionConfig;
import org.wdfeer.not_enough_potatoes.item.PotatoItems;

public class PotatoMod implements ModInitializer {
    public static final String MOD_ID = "not_enough_potatoes";

    @Override
    public void onInitialize() {
        PotatoItems.initialize();
        DispenserBehavior.initialize();

        MidnightConfig.init(MOD_ID, ProtectionConfig.class);
    }
}
