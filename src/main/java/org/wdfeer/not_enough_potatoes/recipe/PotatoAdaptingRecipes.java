package org.wdfeer.not_enough_potatoes.recipe;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.wdfeer.not_enough_potatoes.PotatoMod;
import org.wdfeer.not_enough_potatoes.item.PotatoItems;
import org.wdfeer.not_enough_potatoes.mixin.RecipeManagerAccessor;

import java.util.Map;

public class PotatoAdaptingRecipes {
    public static void initialize(){
        ServerLifecycleEvents.SERVER_STARTING.register(PotatoAdaptingRecipes::registerRecipes);
    }

    private static void registerRecipes(MinecraftServer server) {
        RecipeManager recipeManager = server.getRecipeManager();

        Registry.ITEM.stream()
                .filter(item -> item instanceof ArmorItem)
                .forEach(baseItem -> {
                    // Create your recipe here
                    Identifier recipeId = new Identifier(PotatoMod.MOD_ID, baseItem.getTranslationKey() + "_potato_adapting");
                    JsonObject recipeJson = createRecipeJson(baseItem, PotatoItems.potatoAdapter, baseItem, "{potatoes_eaten:0}");

                    // Convert JSON to SmithingRecipe
                    SmithingRecipe recipe = RecipeSerializer.SMITHING.read(recipeId, recipeJson);

                    // Inject recipe into recipe manager
                    Map<Identifier, Recipe<?>> recipes = ((RecipeManagerAccessor) recipeManager).getRecipes();
                    if (recipes != null)
                        recipes.put(recipeId, recipe);
                });
    }

    private static JsonObject createRecipeJson(Item base, Item addition, Item result, String nbt) {
        JsonObject json = new JsonObject();
        json.addProperty("type", "minecraft:smithing");

        JsonObject baseJson = new JsonObject();
        baseJson.addProperty("item", Registry.ITEM.getId(base).toString());
        json.add("base", baseJson);

        JsonObject additionJson = new JsonObject();
        additionJson.addProperty("item", Registry.ITEM.getId(addition).toString());
        json.add("addition", additionJson);

        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("item", Registry.ITEM.getId(result).toString());
        resultJson.addProperty("nbt", nbt);
        json.add("result", resultJson);

        return json;
    }
}
