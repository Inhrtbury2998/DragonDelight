package dragonsdelightteam.by.dragonsdelight.datagen;

import by.dragonsurvivalteam.dragonsurvival.registry.DSItems;
import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import dragonsdelightteam.by.dragonsdelight.datagen.recipe.CookingRecipes;
import dragonsdelightteam.by.dragonsdelight.items.ModItemEnum;
import dragonsdelightteam.by.dragonsdelight.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.crafting.ingredient.ItemAbilityIngredient;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class DragonsDelightRecipes extends RecipeProvider {

    public DragonsDelightRecipes(final PackOutput output, final CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    private static final Ingredient KNIVES = CompoundIngredient.of(
            new ItemAbilityIngredient(KnifeItem.KNIFE_DIG).toVanilla(),
            Ingredient.of(CommonTags.Items.TOOLS_KNIFE)
    );

    private static final Ingredient SUGAR_OR_HONEY = CompoundIngredient.of(
            Ingredient.of(Items.SUGAR),
            Ingredient.of(Items.HONEY_BOTTLE)
    );

    private static final Ingredient CORALS = Ingredient.of(
            Items.TUBE_CORAL, Items.BRAIN_CORAL, Items.BUBBLE_CORAL, Items.FIRE_CORAL, Items.HORN_CORAL,
            Items.TUBE_CORAL_FAN, Items.BRAIN_CORAL_FAN, Items.BUBBLE_CORAL_FAN, Items.FIRE_CORAL_FAN, Items.HORN_CORAL_FAN
    );

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        buildSmokingAndSmelting(output);
        buildCrafting(output);
        buildCutting(output);
        CookingRecipes.register(output);
    }

    private void buildSmokingAndSmelting(@NotNull RecipeOutput output) {
        // 熟人肉
        foodSmeltingRecipes("pickled_human_meat",
                ModItems.get(ModItemEnum.HUMAN_FLESH),
                ModItems.get(ModItemEnum.COOKED_HUMAN_MEAT), 0.35F, output);

        // 熟人肉片
        foodSmeltingRecipes("pickled_human_meat_slice",
                ModItems.get(ModItemEnum.HUMAN_FLESH_SLICE),
                ModItems.get(ModItemEnum.COOKED_HUMAN_MEAT_SLICE), 0.35F, output);

        // 外酥里嫩（鱼肉卷烟熏）
        SimpleCookingRecipeBuilder.smoking(
                        Ingredient.of(ModItems.get(ModItemEnum.FISH_ROLL)),
                        RecipeCategory.FOOD,
                        ModItems.get(ModItemEnum.CRISPY_ROLL),
                        0.35F, 100)
                .unlockedBy("has_fish_roll", has(ModItems.get(ModItemEnum.FISH_ROLL)))
                .save(output, ResourceLocation.fromNamespaceAndPath("dragonsdelight", "crispy_roll_from_smoking"));
    }

    private void buildCrafting(@NotNull RecipeOutput output) {
        // 腌制人肉
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.CURING_HUMAN_MEAT))
                .requires(ModItems.get(ModItemEnum.HUMAN_FLESH))
                .requires(SUGAR_OR_HONEY)
                .requires(Ingredient.of(Tags.Items.MUSHROOMS))
                .unlockedBy("has_human_flesh", has(ModItems.get(ModItemEnum.HUMAN_FLESH)))
                .save(output);

        // 腌制人肉切片
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.CURING_HUMAN_MEAT_SLICE))
                .requires(ModItems.get(ModItemEnum.HUMAN_FLESH_SLICE))
                .requires(SUGAR_OR_HONEY)
                .requires(Ingredient.of(Tags.Items.MUSHROOMS))
                .unlockedBy("has_human_flesh_slice", has(ModItems.get(ModItemEnum.HUMAN_FLESH_SLICE)))
                .save(output);

        // 岩浆饮
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.LAVA_DRINK))
                .requires(Items.LAVA_BUCKET)
                .requires(DSItems.ELDER_DRAGON_DUST.value())
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_lava_bucket", has(Items.LAVA_BUCKET))
                .save(output);

        // 洞穴烤串
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.CAVE_BARBECUE_ON_A_STICK))
                .requires(Items.BLAZE_ROD)
                .requires(Items.GLOW_BERRIES)
                .requires(ModItems.get(ModItemEnum.HUMAN_FLESH))
                .requires(Items.SPIDER_EYE)
                .requires(Tags.Items.FOODS_RAW_MEAT)
                .unlockedBy("has_human_flesh", has(ModItems.get(ModItemEnum.HUMAN_FLESH)))
                .save(output);

        // 苔藓派
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.get(ModItemEnum.MOSS_PIE))
                .pattern("mmm")
                .pattern("ggg")
                .pattern("hph")
                .define('h', Items.HONEY_BOTTLE)
                .define('m', Items.MOSS_BLOCK)
                .define('g', Items.GOLD_NUGGET)
                .define('p', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .unlockedBy("has_pie_crust", has(vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get()))
                .save(output);

        // 花果派
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.get(ModItemEnum.BLOSSOM_PIE))
                .pattern("fff")
                .pattern("bfb")
                .pattern("bpb")
                .define('b', Tags.Items.FOODS_BERRY)
                .define('f', ItemTags.FLOWERS)
                .define('p', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .unlockedBy("has_pie_crust", has(vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get()))
                .save(output);

        // 荧光派
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.get(ModItemEnum.LUMINOUS_PIE))
                .pattern("ddd")
                .pattern("ggg")
                .pattern("sps")
                .define('s', ModItems.get(ModItemEnum.SEAFOOD_SAUCE))
                .define('d', Items.GLOWSTONE_DUST)
                .define('g', Items.GLOW_BERRIES)
                .define('p', vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get())
                .unlockedBy("has_pie_crust", has(vectorwing.farmersdelight.common.registry.ModItems.PIE_CRUST.get()))
                .save(output);

        // 红热三明治
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.RED_HEAT_SANDWICH))
                .requires(Items.CRIMSON_FUNGUS, 2)
                .requires(DSItems.CHARRED_MEAT.value())
                .requires(DSItems.CHARRED_VEGETABLE.value())
                .requires(DSItems.CHARGED_COAL.value())
                .requires(Items.IRON_NUGGET)
                .unlockedBy("has_charred_meat", has(DSItems.CHARRED_MEAT.value()))
                .save(output);

        // 叫花兔
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.BEGGARS_RABBIT))
                .requires(Items.RABBIT)
                .requires(Items.GOLD_NUGGET)
                .requires(ItemTags.LEAVES)
                .unlockedBy("has_rabbit", has(Items.RABBIT))
                .save(output);

        // 毒薯曲奇
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.POISONOUS_POTATO_COOKIE), 8)
                .requires(ModItems.get(ModItemEnum.MASHED_POISONOUS_POTATO))
                .requires(Ingredient.of(Tags.Items.CROPS_WHEAT), 2)
                .unlockedBy("has_mashed_poisonous_potato", has(ModItems.get(ModItemEnum.MASHED_POISONOUS_POTATO)))
                .save(output);

        // 肉棕
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.MEAT_ZONGZI))
                .requires(Items.DRIED_KELP)
                .requires(Items.ROTTEN_FLESH, 2)
                .requires(CommonTags.Items.CROPS_RICE)
                .unlockedBy("has_rotten_flesh", has(Items.ROTTEN_FLESH))
                .save(output);

        // 丛林沙拉
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.JUNGLE_SALAD))
                .requires(Items.BOWL)
                .requires(ModItems.get(ModItemEnum.SHREDDED_POISONOUS_POTATO))
                .requires(Items.VINE, 2)
                .requires(Ingredient.of(Tags.Items.FOODS_RAW_MEAT), 2)
                .requires(Items.CHORUS_FRUIT)
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.FERMENTED_SPIDER_EYE)
                .unlockedBy("has_shredded_poisonous_potato", has(ModItems.get(ModItemEnum.SHREDDED_POISONOUS_POTATO)))
                .save(output);

        // 青草土豆泥
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.GRASS_MASHED_POTATOES))
                .requires(Items.BOWL)
                .requires(ModItems.get(ModItemEnum.MASHED_POISONOUS_POTATO))
                .requires(Items.MOSS_BLOCK, 2)
                .requires(ItemTags.FLOWERS)
                .unlockedBy("has_mashed_poisonous_potato", has(ModItems.get(ModItemEnum.MASHED_POISONOUS_POTATO)))
                .save(output);

        // 鱼肉卷
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.FISH_ROLL))
                .requires(Tags.Items.FOODS_RAW_FISH)
                .requires(Items.KELP, 8)
                .unlockedBy("has_kelp", has(Items.KELP))
                .save(output);

        // 珊瑚慢食棒
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.CORAL_CHEW_BAR))
                .requires(CORALS)
                .requires(Items.TROPICAL_FISH)
                .requires(Items.SEA_PICKLE)
                .unlockedBy("has_sea_pickle", has(Items.SEA_PICKLE))
                .save(output);

        // 毒薯泥
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.MASHED_POISONOUS_POTATO))
                .requires(ModItems.get(ModItemEnum.SHREDDED_POISONOUS_POTATO))
                .unlockedBy("has_shredded_poisonous_potato", has(ModItems.get(ModItemEnum.SHREDDED_POISONOUS_POTATO)))
                .save(output);

        // 毒薯线
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.POISONOUS_POTATO_NOODLES))
                .requires(ModItems.get(ModItemEnum.MASHED_POISONOUS_POTATO), 2)
                .requires(Items.WATER_BUCKET)
                .unlockedBy("has_mashed_poisonous_potato", has(ModItems.get(ModItemEnum.MASHED_POISONOUS_POTATO)))
                .save(output);

        // 矿石酱
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.ORE_SAUCE), 1)
                .requires(Items.GOLD_NUGGET)
                .requires(Items.REDSTONE)
                .requires(Items.LAPIS_LAZULI)
                .requires(DSItems.ELDER_DRAGON_DUST.value())
                .requires(Items.BOWL)
                .unlockedBy("has_elder_dragon_dust", has(DSItems.ELDER_DRAGON_DUST.value()))
                .save(output, ResourceLocation.fromNamespaceAndPath(DragonsDelight.MODID, "ore_sauce"));

        // 荧光酱 - 无序合成
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.LUMINOUS_SAUCE), 1)
                .requires(Items.GLOW_INK_SAC)
                .requires(Items.SUGAR)
                .requires(Items.NETHER_WART)
                .requires(Items.BOWL)
                .unlockedBy("has_nether_wart", has(Items.NETHER_WART))
                .save(output, ResourceLocation.fromNamespaceAndPath(DragonsDelight.MODID, "luminous_sauce"));

        // 海鲜酱 - 无序合成（使用海带）
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.get(ModItemEnum.SEAFOOD_SAUCE), 1)
                .requires(Items.INK_SAC)
                .requires(Tags.Items.FOODS_RAW_FISH)
                .requires(Ingredient.of(Items.KELP, Items.SEA_PICKLE))
                .requires(Items.BOWL)
                .unlockedBy("has_ink_sac", has(Items.INK_SAC))
                .save(output, ResourceLocation.fromNamespaceAndPath(DragonsDelight.MODID, "seafood_sauce_kelp"));
    }

    private void buildCutting(@NotNull RecipeOutput output) {
        // 人肉片
        CuttingBoardRecipeBuilder.cuttingRecipe(
                        Ingredient.of(ModItems.get(ModItemEnum.HUMAN_FLESH)),
                        KNIVES,
                        ModItems.get(ModItemEnum.HUMAN_FLESH_SLICE), 2)
                .save(output);

        // 毒薯丝
        CuttingBoardRecipeBuilder.cuttingRecipe(
                        Ingredient.of(Items.POISONOUS_POTATO),
                        KNIVES,
                        ModItems.get(ModItemEnum.SHREDDED_POISONOUS_POTATO), 1)
                .setNamespace(DragonsDelight.MODID)
                .save(output);

        // 毒薯泥
        CuttingBoardRecipeBuilder.cuttingRecipe(
                        Ingredient.of(ModItems.get(ModItemEnum.SHREDDED_POISONOUS_POTATO)),
                        KNIVES,
                        ModItems.get(ModItemEnum.MASHED_POISONOUS_POTATO), 1)
                .save(output);

        // 苔藓派切片
        CuttingBoardRecipeBuilder.cuttingRecipe(
                        Ingredient.of(ModItems.get(ModItemEnum.MOSS_PIE)),
                        KNIVES,
                        ModItems.get(ModItemEnum.SLICE_OF_MOSS_PIE), 4)
                .save(output);

        // 花果派切片
        CuttingBoardRecipeBuilder.cuttingRecipe(
                        Ingredient.of(ModItems.get(ModItemEnum.BLOSSOM_PIE)),
                        KNIVES,
                        ModItems.get(ModItemEnum.SLICE_OF_BLOSSOM_PIE), 4)
                .save(output);

        // 荧光派切片
        CuttingBoardRecipeBuilder.cuttingRecipe(
                        Ingredient.of(ModItems.get(ModItemEnum.LUMINOUS_PIE)),
                        KNIVES,
                        ModItems.get(ModItemEnum.SLICE_OF_LUMINOUS_PIE), 4)
                .save(output);
    }

    private static void foodSmeltingRecipes(String name, ItemLike ingredient, ItemLike result, float experience, RecipeOutput output) {
        String namePrefix = ResourceLocation.fromNamespaceAndPath(DragonsDelight.MODID, name).toString();
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 200)
                .unlockedBy(name, has(ingredient))
                .save(output);
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 600)
                .unlockedBy(name, has(ingredient))
                .save(output, namePrefix + "_from_campfire_cooking");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 100)
                .unlockedBy(name, has(ingredient))
                .save(output, namePrefix + "_from_smoking");
    }
}
