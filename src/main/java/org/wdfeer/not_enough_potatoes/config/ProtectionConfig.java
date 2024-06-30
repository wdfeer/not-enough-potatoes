package org.wdfeer.not_enough_potatoes.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class ProtectionConfig extends MidnightConfig {
    @Comment
    public static Comment logarithmNote = null;

    @Entry(min = 1.1, max = 1e6)
    public static double potatoArmorLogBase = 1;

    @Entry(min = 1.1, max = 1e6)
    public static double potatoShardLogBase = 1;
}
