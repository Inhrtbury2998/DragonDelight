package dragonsdelightteam.by.dragonsdelight.datagen.recipe;

import by.dragonsurvivalteam.dragonsurvival.registry.DSBlocks;
import by.dragonsurvivalteam.dragonsurvival.registry.DSItems;
import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import dragonsdelightteam.by.dragonsdelight.items.ModItemEnum;
import dragonsdelightteam.by.dragonsdelight.items.ModItems;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

import static vectorwing.farmersdelight.data.recipe.CookingRecipes.MEDIUM_EXP;
import static vectorwing.farmersdelight.data.recipe.CookingRecipes.NORMAL_COOKING;

public class CookingRecipes {
    public static void register(RecipeOutput output) {
        cookNoodles(output);
        cookMeals(output);
        cookMiscellaneous(output);
        cookSauce(output);
    }

    private static void cookNoodles(RecipeOutput output) {
        // 矿洞汤
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.CAVE_SOUP), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModItems.get(ModItemEnum.ORE_SAUCE))
                .addIngredient(Items.LAVA_BUCKET)
                .addIngredient(DSItems.DOUBLE_QUARTZ.value())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(ModItems.get(ModItemEnum.ORE_SAUCE), DSItems.DOUBLE_QUARTZ.value())
                .save(output);

        // 余烬薯线
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.EMBER_NOODLES), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModItems.get(ModItemEnum.POISONOUS_POTATO_NOODLES))
                .addIngredient(Items.GUNPOWDER)
                .addIngredient(Ingredient.of(
                        DSItems.CHARRED_MEAT.value(),
                        DSItems.CHARRED_VEGETABLE.value(),
                        DSItems.CHARRED_MUSHROOM.value(),
                        DSItems.CHARRED_SEAFOOD.value()
                ))
                .addIngredient(Items.CHARCOAL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(ModItems.get(ModItemEnum.POISONOUS_POTATO_NOODLES))
                .save(output);

        // 矿石薯线
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.ORE_NOODLES), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModItems.get(ModItemEnum.POISONOUS_POTATO_NOODLES))
                .addIngredient(ModItems.get(ModItemEnum.ORE_SAUCE))
                .addIngredient(DSItems.DOUBLE_QUARTZ.value())
                .addIngredient(DSItems.CHARGED_COAL.value())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(ModItems.get(ModItemEnum.ORE_SAUCE))
                .save(output);

        // 浓汤薯线
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.BISQUE_NOODLES), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModItems.get(ModItemEnum.POISONOUS_POTATO_NOODLES))
                .addIngredient(Items.ROTTEN_FLESH)
                .addIngredient(Items.FERMENTED_SPIDER_EYE)
                .addIngredient(Items.BONE_MEAL)
                .addIngredient(ModItems.get(ModItemEnum.LUMINOUS_SAUCE))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(ModItems.get(ModItemEnum.LUMINOUS_SAUCE))
                .save(output);

        // 遗迹薯线
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.RELIC_NOODLES), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModItems.get(ModItemEnum.POISONOUS_POTATO_NOODLES))
                .addIngredient(Items.ROTTEN_FLESH)
                .addIngredient(Items.DRIED_KELP)
                .addIngredient(CompoundIngredient.of(Ingredient.of(Items.PRISMARINE_SHARD), Ingredient.of(Items.PRISMARINE_CRYSTALS)))
                .addIngredient(Items.BONE_MEAL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(ModItems.get(ModItemEnum.POISONOUS_POTATO_NOODLES))
                .save(output);

        // 繁茂拌面
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.LUSH_NOODLES), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModItems.get(ModItemEnum.POISONOUS_POTATO_NOODLES))
                .addIngredient(Items.GLOW_LICHEN)
                .addIngredient(Items.SPIDER_EYE)
                .addIngredient(Items.GLOW_BERRIES, 2)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(Items.GLOW_BERRIES)
                .save(output);

        // 湿地拌面
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.WETLAND_NOODLES), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModItems.get(ModItemEnum.POISONOUS_POTATO_NOODLES))
                .addIngredient(Items.GLOW_INK_SAC)
                .addIngredient(Items.SEAGRASS, 3)
                .addIngredient(Ingredient.of(Tags.Items.FOODS_BERRY), 2)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(Items.SEAGRASS)
                .save(output);

        // 夏日拌面
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.SUMMER_NOODLES), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModItems.get(ModItemEnum.POISONOUS_POTATO_NOODLES))
                .addIngredient(Items.TROPICAL_FISH)
                .addIngredient(ModItems.get(ModItemEnum.SEAFOOD_SAUCE))
                .addIngredient(Items.SNOWBALL, 2)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(ModItems.get(ModItemEnum.SEAFOOD_SAUCE))
                .save(output);

        // 烤冷面
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.GRILLED_COLD_NOODLES), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(ModItems.get(ModItemEnum.MASHED_POISONOUS_POTATO))
                .addIngredient(DSItems.CHARRED_VEGETABLE.value())
                .addIngredient(Items.FERMENTED_SPIDER_EYE)
                .addIngredient(CommonTags.Items.CROPS_ONION)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(ModItems.get(ModItemEnum.MASHED_POISONOUS_POTATO))
                .save(output);
    }
    private static void cookSauce(RecipeOutput output) {
        // 矿石酱
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.ORE_SAUCE), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(Items.GOLD_NUGGET)
                .addIngredient(Items.REDSTONE)
                .addIngredient(Items.LAPIS_LAZULI)
                .addIngredient(DSItems.ELDER_DRAGON_DUST.value())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(DSItems.ELDER_DRAGON_DUST.value())
                .save(output);

        // 荧光酱
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.LUMINOUS_SAUCE), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(Items.GLOW_INK_SAC)
                .addIngredient(Items.SUGAR)
                .addIngredient(Items.NETHER_WART)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(Items.NETHER_WART)
                .save(output);

        // 海鲜酱
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.SEAFOOD_SAUCE), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(Items.INK_SAC)
                .addIngredient(Tags.Items.FOODS_RAW_FISH)
                .addIngredient(Ingredient.of(Items.KELP, Items.SEA_PICKLE))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(Items.INK_SAC)
                .save(output);
    }

    private static void cookMeals(RecipeOutput output) {
        // 金箔烤鱼
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.ROAST_FISH_WITH_GOLD_FOIL), 1, NORMAL_COOKING, MEDIUM_EXP, Items.GOLD_INGOT)
                .addIngredient(CompoundIngredient.of(Ingredient.of(Tags.Items.FOODS_COOKED_FISH), Ingredient.of(Tags.Items.FOODS_RAW_FISH)))
                .addIngredient(ModItems.get(ModItemEnum.LUMINOUS_SAUCE))
                .addIngredient(Ingredient.of(DSItems.CHARRED_MUSHROOM.value(), DSItems.CHARRED_VEGETABLE.value()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(ModItems.get(ModItemEnum.LUMINOUS_SAUCE))
                .save(output);

        // 胜利之宴（三种头盔容器）
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.FEAST_OF_VICTORY), 1, NORMAL_COOKING, MEDIUM_EXP, DSBlocks.BLACK_KNIGHT_HELMET.value().asItem())
                .addIngredient(ModItems.get(ModItemEnum.CURING_HUMAN_MEAT))
                .addIngredient(CompoundIngredient.of(Ingredient.of(Tags.Items.FOODS_RAW_MEAT), Ingredient.of(Items.ROTTEN_FLESH)))
                .addIngredient(Items.GOLD_NUGGET)
                .addIngredient(Ingredient.of(Tags.Items.FOODS_VEGETABLE))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByItems("has_black_knight_helmet", DSBlocks.BLACK_KNIGHT_HELMET.value().asItem())
                .save(output, ResourceLocation.fromNamespaceAndPath(DragonsDelight.MODID, "cooking/feast_of_victory_black_knight_helmet"));
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.FEAST_OF_VICTORY), 1, NORMAL_COOKING, MEDIUM_EXP, DSBlocks.GRAY_KNIGHT_HELMET.value().asItem())
                .addIngredient(ModItems.get(ModItemEnum.CURING_HUMAN_MEAT))
                .addIngredient(CompoundIngredient.of(Ingredient.of(Tags.Items.FOODS_RAW_MEAT), Ingredient.of(Items.ROTTEN_FLESH)))
                .addIngredient(Items.GOLD_NUGGET)
                .addIngredient(Ingredient.of(Tags.Items.FOODS_VEGETABLE))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByItems("has_gray_knight_helmet", DSBlocks.GRAY_KNIGHT_HELMET.value().asItem())
                .save(output, ResourceLocation.fromNamespaceAndPath(DragonsDelight.MODID, "cooking/feast_of_victory_gray_knight_helmet"));
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.FEAST_OF_VICTORY), 1, NORMAL_COOKING, MEDIUM_EXP, DSBlocks.GOLDEN_KNIGHT_HELMET.value().asItem())
                .addIngredient(ModItems.get(ModItemEnum.CURING_HUMAN_MEAT))
                .addIngredient(CompoundIngredient.of(Ingredient.of(Tags.Items.FOODS_RAW_MEAT), Ingredient.of(Items.ROTTEN_FLESH)))
                .addIngredient(Items.GOLD_NUGGET)
                .addIngredient(Ingredient.of(Tags.Items.FOODS_VEGETABLE))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByItems("has_golden_knight_helmet", DSBlocks.GOLDEN_KNIGHT_HELMET.value().asItem())
                .save(output, ResourceLocation.fromNamespaceAndPath(DragonsDelight.MODID, "cooking/feast_of_victory_golden_knight_helmet"));

        // 火山蛋
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.VOLCANIC_EGG), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(Tags.Items.EGGS)
                .addIngredient(Items.BLAZE_POWDER)
                .addIngredient(Items.AMETHYST_SHARD)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(Items.BLAZE_POWDER, Items.AMETHYST_SHARD)
                .save(output);

        // 海龟蛋羹
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.TURTLE_EGG_CUSTARD), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(Items.TURTLE_EGG)
                .addIngredient(CommonTags.Items.CROPS_ONION)
                .addIngredient(ModItems.get(ModItemEnum.LUMINOUS_SAUCE))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(Items.TURTLE_EGG)
                .save(output);

        // 墨汁鱼肉冻
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.INKY_FISH_ASPIC), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(Items.INK_SAC)
                .addIngredient(Items.SALMON)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(Items.SALMON, Items.INK_SAC)
                .save(output);

        // 热泉鱼
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.SPRING_FISH), 1, NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(Tags.Items.FOODS_RAW_FISH)
                .addIngredient(DSItems.CHARRED_SEAFOOD.value())
                .addIngredient(ModItems.get(ModItemEnum.ORE_SAUCE))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(DSItems.CHARRED_SEAFOOD.value())
                .save(output);

        // 皇族盒饭
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.ROYAL_LUNCHBOX), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(Ingredient.of(Tags.Items.FOODS_VEGETABLE), 2)
                .addIngredient(CommonTags.Items.CROPS_RICE)
                .addIngredient(Tags.Items.FOODS_RAW_MEAT)
                .addIngredient(ItemTags.FLOWERS)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByItems("has_rice", vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .save(output);

        // 皇族粮
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.get(ModItemEnum.ROYAL_FOOD), 1, NORMAL_COOKING, MEDIUM_EXP, Items.BOWL)
                .addIngredient(Tags.Items.FOODS_VEGETABLE)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .addIngredient(Tags.Items.FOODS_COOKED_MEAT)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(vectorwing.farmersdelight.common.registry.ModItems.COOKED_RICE.get())
                .save(output);
    }

    private static void cookMiscellaneous(RecipeOutput output) {
        // 此处可添加其它需要烹饪锅但分类不明显的配方
    }
}
