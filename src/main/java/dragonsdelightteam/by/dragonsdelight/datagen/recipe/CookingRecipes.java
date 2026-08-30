package dragonsdelightteam.by.dragonsdelight.datagen.recipe;

import by.dragonsurvivalteam.dragonsurvival.registry.DSBlocks;
import by.dragonsurvivalteam.dragonsurvival.registry.DSItems;
import dragonsdelightteam.by.dragonsdelight.items.ModItemEnum;
import dragonsdelightteam.by.dragonsdelight.items.ModItems;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

import static vectorwing.farmersdelight.data.recipe.CookingRecipes.MEDIUM_EXP;
import static vectorwing.farmersdelight.data.recipe.CookingRecipes.NORMAL_COOKING;

public class CookingRecipes {
    public static void register(RecipeOutput output) {
        cookDragonDelightRecipe(output);
    }
    private static void cookDragonDelightRecipe(RecipeOutput output) {
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.ROAST_FISH_WITH_GOLD_FOIL), 1, NORMAL_COOKING, MEDIUM_EXP, Items.GOLD_INGOT)
                .addIngredient(CompoundIngredient.of(Ingredient.of(Tags.Items.FOODS_COOKED_FISH), Ingredient.of(Tags.Items.FOODS_RAW_FISH)))
                .addIngredient(ModItems.get(ModItemEnum.LUMINOUS_SAUCE))
                .addIngredient(Ingredient.of(DSItems.CHARRED_MUSHROOM.value(), DSItems.CHARRED_VEGETABLE.value()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(ModItems.get(ModItemEnum.LUMINOUS_SAUCE))
                .save(output);
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.FEAST_OF_VICTORY), 1, NORMAL_COOKING, MEDIUM_EXP, DSBlocks.BLACK_KNIGHT_HELMET.value().asItem())
                .addIngredient(ModItems.get(ModItemEnum.CURING_HUMAN_MEAT))
                .addIngredient(CompoundIngredient.of(Ingredient.of(Tags.Items.FOODS_RAW_MEAT), Ingredient.of(Items.ROTTEN_FLESH)))
                .addIngredient(Items.GOLD_NUGGET)
                .addIngredient(Ingredient.of(Tags.Items.FOODS_VEGETABLE))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByItems("has_black_knight_helmet", DSBlocks.BLACK_KNIGHT_HELMET.value().asItem())
                .save(output, "cooking/feast_of_victory_black_knight_helmet");
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.FEAST_OF_VICTORY), 1, NORMAL_COOKING, MEDIUM_EXP, DSBlocks.GRAY_KNIGHT_HELMET.value().asItem())
                .addIngredient(ModItems.get(ModItemEnum.CURING_HUMAN_MEAT))
                .addIngredient(CompoundIngredient.of(Ingredient.of(Tags.Items.FOODS_RAW_MEAT), Ingredient.of(Items.ROTTEN_FLESH)))
                .addIngredient(Items.GOLD_NUGGET)
                .addIngredient(Ingredient.of(Tags.Items.FOODS_VEGETABLE))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByItems("has_gray_knight_helmet", DSBlocks.GRAY_KNIGHT_HELMET.value().asItem())
                .save(output, "cooking/feast_of_victory_gray_knight_helmet");
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.FEAST_OF_VICTORY), 1, NORMAL_COOKING, MEDIUM_EXP, DSBlocks.GOLDEN_KNIGHT_HELMET.value().asItem())
                .addIngredient(ModItems.get(ModItemEnum.CURING_HUMAN_MEAT))
                .addIngredient(CompoundIngredient.of(Ingredient.of(Tags.Items.FOODS_RAW_MEAT), Ingredient.of(Items.ROTTEN_FLESH)))
                .addIngredient(Items.GOLD_NUGGET)
                .addIngredient(Ingredient.of(Tags.Items.FOODS_VEGETABLE))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByItems("has_golden_knight_helmet", DSBlocks.GOLDEN_KNIGHT_HELMET.value().asItem())
                .save(output, "cooking/feast_of_victory_golden_knight_helmet");
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.ORE_SAUCE), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(Items.GOLD_NUGGET)
                .addIngredient(Items.REDSTONE)
                .addIngredient(Items.LAPIS_LAZULI)
                .addIngredient(DSItems.ELDER_DRAGON_DUST.value())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByItems("has_elder_dragon_dust", DSItems.ELDER_DRAGON_DUST.value())
                .save(output);
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.LUMINOUS_SAUCE), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(Items.GLOW_INK_SAC)
                .addIngredient(Items.SUGAR)
                .addIngredient(Items.NETHER_WART)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByItems("has_nether_wart", Items.NETHER_WART)
                .save(output);
    }
}
