package dragonsdelightteam.by.dragonsdelight.items;

import by.dragonsurvivalteam.dragonsurvival.registry.DSItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.item.KnifeItem;

/**
 * 黯影龙刀：由下界合金刀在锻造台中用黑暗升级模板与龙心升级而来。
 * <p>
 * 与农夫乐事的刀同类（{@link KnifeItem}），攻击伤害比下界合金刀高 0.5，
 * 耐久更高；额外的击杀掉落效果由数据包实现（见
 * {@code data/dragonsdelight/loot_modifiers/dark_dragon_knife.json} 与
 * {@code data/dragonsdelight/loot_table/dark_dragon_knife_bonus.json}），
 * 掉落概率等可在数据包中调整，无需改动代码。
 */
public class DarkDragonKnifeItem extends KnifeItem {
    public static final Tier DARK_DRAGON_TIER = new Tier() {
        @Override
        public int getUses() {
            return 3000;
        }

        @Override
        public float getSpeed() {
            return 9.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 4.5F;
        }

        @Override
        public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
        }

        @Override
        public int getEnchantmentValue() {
            return 15;
        }

        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(DSItems.ELDER_DRAGON_HEART.value());
        }
    };

    public DarkDragonKnifeItem(Item.Properties properties) {
        super(DARK_DRAGON_TIER, properties);
    }
}
