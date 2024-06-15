package org.wdfeer.not_enough_potatoes;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.DataComponentType;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.util.Util;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PotatoCounterComponentType implements DataComponentType<PotatoCounterComponent> {
    public static final PotatoCounterComponentType INSTANCE = new PotatoCounterComponentType();

    @Nullable
    @Override
    public Codec<PotatoCounterComponent> getCodec() {
        return PotatoCounterComponent.CODEC;
    }

    @Override
    public PacketCodec<? super RegistryByteBuf, PotatoCounterComponent> getPacketCodec() {
        return PacketCodecs.codec(PotatoCounterComponent.CODEC);
    }
}
