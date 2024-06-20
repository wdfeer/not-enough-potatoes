package org.wdfeer.not_enough_potatoes.recipe;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.wdfeer.not_enough_potatoes.PotatoMod;
import org.wdfeer.not_enough_potatoes.item.PotatoArmorPiece;
import net.fabricmc.fabric.api.event.lifecycle.*;

public class PotatoAdapting {
    public static void initialize(){
        ServerLifecycleEvents.SERVER_STARTING.register(PotatoAdapting::registerRecipes);
    }

    private static void registerRecipes(MinecraftServer server) {
        for (Item item : Registry.ITEM) {
            if (item instanceof ArmorItem && !(item instanceof PotatoArmorPiece)) {
                registerRecipe(item);
            }
        }
    }

    private static void registerRecipe(Item baseItem) {
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("potatoes_eaten", 0);

        ItemStack result = new ItemStack(baseItem, 1);
        result.setNbt(nbt);

        SmithingRecipe recipe = new SmithingRecipe(
                new Identifier(PotatoMod.MOD_ID, baseItem.getTranslationKey() + "_potato_adapting"),
                Ingredient.ofItems(baseItem),
                Ingredient.ofItems(Items.NETHER_STAR),
                result
        );

        // TODO: REGISTER
    }
}
