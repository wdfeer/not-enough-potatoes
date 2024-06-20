package org.wdfeer.not_enough_potatoes.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.util.Identifier;

public class PotatoAdapterSmithingRecipe extends SmithingRecipe {
    public PotatoAdapterSmithingRecipe(Identifier id, Ingredient base, Ingredient addition, ItemStack result) {
        super(id, base, addition, result);
    }

    public static void register(){
        
    }
}
