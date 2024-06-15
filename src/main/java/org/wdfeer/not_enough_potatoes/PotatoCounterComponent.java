package org.wdfeer.not_enough_potatoes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class PotatoCounterComponent{
    public int count = 0;
    public PotatoCounterComponent(int count){
        this.count = count;
    }

    public static final Codec<PotatoCounterComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("counter").forGetter(component -> component.count)
    ).apply(instance, PotatoCounterComponent::new));
}
