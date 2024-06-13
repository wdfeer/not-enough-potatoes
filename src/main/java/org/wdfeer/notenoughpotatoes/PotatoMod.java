package org.wdfeer.notenoughpotatoes;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PotatoMod implements ModInitializer {
    public static final String MOD_ID = "notenoughpotatoes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        PotatoMaterial.Initialize();
        PotatoItems.Initialize();
    }
}
