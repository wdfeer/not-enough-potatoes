package org.wdfeer.notenoughpotatoes;

import net.fabricmc.api.ModInitializer;

public class PotatoMod implements ModInitializer {
    public static final String MOD_ID = "notenoughpotatoes";
    @Override
    public void onInitialize() {
        PotatoMaterial.Initialize();
        PotatoItems.Initialize();
    }
}
