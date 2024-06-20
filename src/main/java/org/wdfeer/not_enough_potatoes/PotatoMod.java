package org.wdfeer.not_enough_potatoes;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wdfeer.not_enough_potatoes.item.PotatoItems;

public class PotatoMod implements ModInitializer {
    public static final String MOD_ID = "not_enough_potatoes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        PotatoItems.initialize();
        DispenserBehavior.initialize();
    }
}
