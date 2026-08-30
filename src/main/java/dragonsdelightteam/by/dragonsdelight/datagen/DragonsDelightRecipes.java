package dragonsdelightteam.by.dragonsdelight.datagen;

import dragonsdelightteam.by.dragonsdelight.datagen.recipe.CookingRecipes;
import dragonsdelightteam.by.dragonsdelight.items.ModItemEnum;
import dragonsdelightteam.by.dragonsdelight.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class DragonsDelightRecipes extends RecipeProvider {

    public DragonsDelightRecipes(final PackOutput output, final CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        CookingRecipes.register(output);
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(ModItems.get(ModItemEnum.HUMAN_FLESH)),
                        RecipeCategory.MISC,
                        Items.IRON_INGOT,
                        0.7f,  // 经验
                        200    // 烧制时间(ticks)
                )
                .unlockedBy("has_raw_iron", has(Items.RAW_IRON))
                .save(output);
    }
}
