package dragonsdelightteam.by.dragonsdelight.datagen.data_maps;

import by.dragonsurvivalteam.dragonsurvival.common.codecs.DietEntry;
import by.dragonsurvivalteam.dragonsurvival.registry.DSConditions;
import by.dragonsurvivalteam.dragonsurvival.registry.DSDataMaps;
import by.dragonsurvivalteam.dragonsurvival.registry.DSEffects;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.BuiltInDragonSpecies;
import dragonsdelightteam.by.dragonsdelight.items.ModItemEnum;
import dragonsdelightteam.by.dragonsdelight.items.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.common.data.DataMapProvider;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 生成本模组食物对应的龙种饮食数据（{@code dragonsurvival:diet_entries} data map）。
 * <p>
 * 本模组生成的文件位于 {@code data/dragonsdelight/data_maps/dragonsurvival/dragon_species/}，
 * 与 DragonSurvival 自身生成的同名 data map 是两个不同命名空间的文件，
 * 加载时由 {@code DataMapLoader} 一并读取并通过 {@code DietEntryMerger} 合并，因此不会互相覆盖。
 * <p>
 * {@code nutrition} = 饱食度/份，{@code saturation} = 饱和度/份
 * （{@link DietEntry.Builder#saturation(float)} 接收的就是总饱和度，无需再换算倍率）。
 * <p>
 * 人类食物不在此处注册：龙类本就无法食用人类食物，人类食用依靠物品自身的 {@code .food(...)} 属性。
 */
public class DietEntryProvider extends DataMapProvider {
    public DietEntryProvider(final PackOutput output, final CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void gather(HolderLookup.@NotNull Provider provider) {
        builder(DSDataMaps.DIET_ENTRIES)
                .add(BuiltInDragonSpecies.CAVE_DRAGON, caveDiet(), false, DSConditions.CAVE_DRAGON_LOADED)
                .add(BuiltInDragonSpecies.FOREST_DRAGON, forestDiet(), false, DSConditions.FOREST_DRAGON_LOADED)
                .add(BuiltInDragonSpecies.SEA_DRAGON, seaDiet(), false, DSConditions.SEA_DRAGON_LOADED);
    }

    /** 三龙共通的食物 */
    private static List<DietEntry> commonDiet() {
        return List.of(
                entry(ModItemEnum.ROAST_FISH_WITH_GOLD_FOIL, 8, 10.0F,
                        new MealEffect(DSEffects.HUNTER_OMEN, 180, 1)),
                entry(ModItemEnum.FEAST_OF_VICTORY, 10, 16.0F),
                entry(ModItemEnum.HUMAN_FLESH, 4, 1.2F),
                entry(ModItemEnum.HUMAN_FLESH_SLICE, 2, 0.6F),
                entry(ModItemEnum.CURING_HUMAN_MEAT, 6, 9.0F),
                entry(ModItemEnum.CURING_HUMAN_MEAT_SLICE, 3, 4.5F)
        );
    }

    /** 洞穴龙 */
    private static List<DietEntry> caveDiet() {
        return diet(
                entry(ModItemEnum.ORE_SAUCE, 2, 1.0F),
                // 喝完能下水一分半
                entry(ModItemEnum.LAVA_DRINK, 2, 4.0F,
                        new MealEffect(DSEffects.FIRE, 90, 0)),
                // 生命恢复一分钟
                entry(ModItemEnum.CAVE_SOUP, 10, 14.0F,
                        new MealEffect(MobEffects.REGENERATION, 60, 0)),
                // 滋养五分钟，能下水三分钟
                entry(ModItemEnum.EMBER_NOODLES, 12, 19.2F,
                        new MealEffect(ModEffects.NOURISHMENT, 300, 0),
                        new MealEffect(DSEffects.FIRE, 180, 0)),
                // 滋养五分钟，急迫两分钟
                entry(ModItemEnum.ORE_NOODLES, 12, 19.2F,
                        new MealEffect(ModEffects.NOURISHMENT, 300, 0),
                        new MealEffect(MobEffects.DIG_SPEED, 120, 0)),
                entry(ModItemEnum.CAVE_BARBECUE_ON_A_STICK, 8, 15.0F),
                // 滋养五分钟，能下水两分钟
                entry(ModItemEnum.VOLCANIC_EGG, 10, 16.0F,
                        new MealEffect(ModEffects.NOURISHMENT, 300, 0),
                        new MealEffect(DSEffects.FIRE, 120, 0)),
                entry(ModItemEnum.RED_HEAT_SANDWICH, 10, 16.0F),
                // 森林龙 + 洞穴龙
                entry(ModItemEnum.BEGGARS_RABBIT_COOKED, 12, 19.2F,
                        new MealEffect(ModEffects.NOURISHMENT, 180, 0)),
                // 洞穴龙 + 海洋龙
                entry(ModItemEnum.CRISPY_ROLL, 10, 16.0F)
        );
    }

    /** 森林龙 */
    private static List<DietEntry> forestDiet() {
        return diet(
                entry(ModItemEnum.LUMINOUS_SAUCE, 2, 1.0F),
                // 滋养五分钟
                entry(ModItemEnum.LUSH_NOODLES, 10, 16.0F,
                        new MealEffect(ModEffects.NOURISHMENT, 300, 0)),
                // 速度半分钟
                entry(ModItemEnum.MOSS_PIE, 8, 15.0F,
                        new MealEffect(MobEffects.MOVEMENT_SPEED, 30, 0)),
                entry(ModItemEnum.SLICE_OF_MOSS_PIE, 3, 1.8F,
                        new MealEffect(MobEffects.MOVEMENT_SPEED, 30, 0)),
                entry(ModItemEnum.BLOSSOM_PIE, 3, 1.8F,
                        new MealEffect(MobEffects.MOVEMENT_SPEED, 30, 0)),
                entry(ModItemEnum.SLICE_OF_BLOSSOM_PIE, 3, 1.8F,
                        new MealEffect(MobEffects.MOVEMENT_SPEED, 30, 0)),
                // 滋养三分钟
                entry(ModItemEnum.BEGGARS_RABBIT, 12, 19.2F,
                        new MealEffect(ModEffects.NOURISHMENT, 180, 0)),
                entry(ModItemEnum.POISONOUS_POTATO_COOKIE, 2, 0.6F),
                entry(ModItemEnum.MEAT_ZONGZI, 8, 12.8F),
                // 生命恢复II五秒
                entry(ModItemEnum.JUNGLE_SALAD, 6, 7.2F,
                        new MealEffect(MobEffects.REGENERATION, 5, 1)),
                // 滋养一分钟
                entry(ModItemEnum.GRASS_MASHED_POTATOES, 10, 12.0F,
                        new MealEffect(ModEffects.NOURISHMENT, 60, 0)),
                entry(ModItemEnum.SHREDDED_POISONOUS_POTATO, 2, 0.6F),
                entry(ModItemEnum.MASHED_POISONOUS_POTATO, 2, 0.6F),
                entry(ModItemEnum.POISONOUS_POTATO_NOODLES, 2, 1.0F),
                // 森林龙 + 海洋龙
                entry(ModItemEnum.LUMINOUS_PIE, 3, 1.8F,
                        new MealEffect(MobEffects.MOVEMENT_SPEED, 30, 0)),
                entry(ModItemEnum.SLICE_OF_LUMINOUS_PIE, 3, 1.8F,
                        new MealEffect(MobEffects.MOVEMENT_SPEED, 30, 0)),
                // 森林龙 + 洞穴龙
                entry(ModItemEnum.BEGGARS_RABBIT_COOKED, 12, 19.2F)
        );
    }

    /** 海洋龙 */
    private static List<DietEntry> seaDiet() {
        return diet(
                // 滋养五分钟
                entry(ModItemEnum.BISQUE_NOODLES, 12, 19.2F,
                        new MealEffect(ModEffects.NOURISHMENT, 300, 0)),
                // 滋养五分钟
                entry(ModItemEnum.RELIC_NOODLES, 12, 19.2F,
                        new MealEffect(ModEffects.NOURISHMENT, 300, 0)),
                // 滋养五分钟
                entry(ModItemEnum.WETLAND_NOODLES, 10, 16.0F,
                        new MealEffect(ModEffects.NOURISHMENT, 300, 0)),
                // 滋养五分钟，生命恢复一分钟
                entry(ModItemEnum.SUMMER_NOODLES, 10, 16.0F,
                        new MealEffect(ModEffects.NOURISHMENT, 300, 0),
                        new MealEffect(MobEffects.REGENERATION, 60, 0)),
                // 滋养三分钟
                entry(ModItemEnum.GRILLED_COLD_NOODLES, 14, 21.0F,
                        new MealEffect(ModEffects.NOURISHMENT, 180, 0)),
                entry(ModItemEnum.FISH_ROLL, 8, 12.8F),
                // 生命恢复II一分钟
                entry(ModItemEnum.TURTLE_EGG_CUSTARD, 5, 5.0F,
                        new MealEffect(MobEffects.REGENERATION, 60, 1)),
                // 滋养三分钟
                entry(ModItemEnum.INKY_FISH_ASPIC, 14, 21.0F,
                        new MealEffect(ModEffects.NOURISHMENT, 180, 0)),
                entry(ModItemEnum.SEAFOOD_SAUCE, 4, 4.0F),
                // 急迫五分钟
                entry(ModItemEnum.CORAL_CHEW_BAR, 5, 5.0F,
                        new MealEffect(MobEffects.DIG_SPEED, 300, 0)),
                entry(ModItemEnum.SPRING_FISH, 8, 12.8F),
                // 森林龙 + 海洋龙
                entry(ModItemEnum.LUMINOUS_PIE, 3, 1.8F,
                        new MealEffect(MobEffects.MOVEMENT_SPEED, 30, 0)),
                entry(ModItemEnum.SLICE_OF_LUMINOUS_PIE, 3, 1.8F,
                        new MealEffect(MobEffects.MOVEMENT_SPEED, 30, 0)),
                // 洞穴龙 + 海洋龙
                entry(ModItemEnum.CRISPY_ROLL, 10, 16.0F)
        );
    }

    /** 三龙共通的食物 + 该龙种专属（或与其它龙种共享）的食物。 */
    private static List<DietEntry> diet(DietEntry... entries) {
        List<DietEntry> diet = new ArrayList<>(commonDiet());
        Collections.addAll(diet, entries);
        return List.copyOf(diet);
    }

    /**
     * 构造一条龙的饮食数据。
     * <p>
     * 传入 {@link MealEffect} 时，龙食用该食物后必定获得对应效果（probability = 1.0F）。
     * 可传入多个：{@code entry(ModItemEnum.ORE_NOODLES, 12, 19.2F, new MealEffect(ModEffects.NOURISHMENT, 300, 0), new MealEffect(MobEffects.DIG_SPEED, 120, 0))}。
     */
    private static DietEntry entry(ModItemEnum item, int nutrition, float saturation, MealEffect... effects) {
        DietEntry.Builder builder = DietEntry.create(ModItems.get(item).get())
                .nutrition(nutrition)
                .saturation(saturation);

        for (MealEffect effect : effects) {
            builder.effect(effect::instance, 1.0F);
        }

        return builder.build();
    }

    /**
     * 食用后必定触发的一个效果。
     *
     * @param effect    效果（如 {@code MobEffects.FIRE_RESISTANCE}）
     * @param seconds   持续时间（秒），内部换算为游戏刻
     * @param amplifier 等级，0 表示 I 级、1 表示 II 级，以此类推
     */
    private record MealEffect(Holder<MobEffect> effect, int seconds, int amplifier) {
        private MobEffectInstance instance() {
            return new MobEffectInstance(effect, seconds * 20, amplifier);
        }
    }

    @Override
    public @NotNull String getName() {
        return "Dragon's Delight Diet Entries";
    }
}
