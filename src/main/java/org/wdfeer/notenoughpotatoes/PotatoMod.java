package org.wdfeer.notenoughpotatoes;

import net.fabricmc.api.ModInitializer;

public class PotatoMod implements ModInitializer {
    @Override
    public void onInitialize() {
        PotatoMaterial.Initialize();
        PotatoItems.Initialize();
    }
}
