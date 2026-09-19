package dragonsdelightteam.by.dragonsdelight.items;

import net.minecraft.world.food.FoodProperties;

/**
 * 人类可食用物品的食物属性。
 * <p>
 * 策划表中的“饱和度/份”是总饱和度，而 {@link FoodProperties.Builder#saturationModifier(float)}
 * 接收的是倍率，最终总饱和度 = 饱食度 × 倍率 × 2，故此处需要反算倍率。
 */
public class ModFoods {
    public static final FoodProperties COOKED_HUMAN_MEAT = food(6, 9.6F);
    public static final FoodProperties COOKED_HUMAN_MEAT_SLICE = food(3, 4.8F);
    public static final FoodProperties ROYAL_LUNCHBOX = food(10, 16.0F);
    public static final FoodProperties ROYAL_FOOD = food(8, 12.0F);

    private static FoodProperties food(int nutrition, float saturation) {
        return new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(saturation / (nutrition * 2.0F))
                .build();
    }
}
