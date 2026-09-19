package dragonsdelightteam.by.dragonsdelight.blocks;

import by.dragonsurvivalteam.dragonsurvival.registry.DSBlocks;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.BuiltInDragonSpecies;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.DragonSpecies;
import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import dragonsdelightteam.by.dragonsdelight.blocks.food.DragonFeastBlock;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DragonsDelight.MODID);

    public static final DeferredBlock<PlateBlock> PLATE = BLOCKS.register("plate",
            () -> new PlateBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GRAY)
                    .strength(0.5F)
                    .noOcclusion()
                    .isRedstoneConductor ((state, level, pos) -> false)
                    .isSuffocating ((state, level, pos) -> false)
                    .isViewBlocking ((state, level, pos) -> false)
            ));

    // 可放置（蛋糕式）的食物方块：直接挖掘均不会掉落；只有特定龙种可食用。
    // 统一使用 DragonFeastBlock，参数依次为：最大份数、是否有盛装物掉落、可食用的龙种、盛装物掉落物。
    private static BlockBehaviour.Properties foodProperties() {
        return BlockBehaviour.Properties.of()
                .strength(0.5F);
    }

    private static final Set<ResourceKey<DragonSpecies>> ALL_DRAGONS =
            Set.of(BuiltInDragonSpecies.CAVE_DRAGON, BuiltInDragonSpecies.FOREST_DRAGON, BuiltInDragonSpecies.SEA_DRAGON);

    // 形状数组下标 = servings 数值，超出数组长度时复用最后一个。
    // 这些形状同时用作方块选中框（描边）和碰撞箱，并以 facing=north 为基准编写，

    /** 烤鱼与胜利盛宴的模型含旋转元素，VoxelShape 只能轴对齐，故用随份数递减高度的粗略方框。 */
    private static final VoxelShape[] FEAST_SHAPES = new VoxelShape[]{
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(2, 0, 2, 14, 4, 14),
            Block.box(2, 0, 2, 14, 6, 14),
            Block.box(2, 0, 2, 14, 8, 14),
            Block.box(2, 0, 2, 14, 8, 14)
    };

    /** 派类模板 template_pie*：整派 2~14 见方、高 4，每吃一份少掉四分之一。 */
    private static final VoxelShape[] PIE_SHAPES = new VoxelShape[]{
            Block.box(8, 0, 2, 14, 4, 8),
            Block.box(2, 0, 2, 14, 4, 8),
            Shapes.or(Block.box(2, 0, 8, 8, 4, 14), Block.box(2, 0, 2, 14, 4, 8)),
            Block.box(2, 0, 2, 14, 4, 14)
    };

    /** 苔藓派在派模板基础上多了中央凸起。 */
    private static final VoxelShape[] MOSS_PIE_SHAPES = new VoxelShape[]{
            Shapes.or(Block.box(8, 0, 2, 14, 4, 8), Block.box(8, 4, 7, 9, 6, 8)),
            Shapes.or(Block.box(2, 0, 2, 14, 4, 8), Block.box(7, 4, 7, 9, 6, 8)),
            Shapes.or(Block.box(2, 0, 8, 8, 4, 14), Block.box(2, 0, 2, 14, 4, 8),
                    Block.box(7, 4, 7, 9, 6, 8), Block.box(7, 4, 8, 8, 6, 9)),
            Shapes.or(Block.box(2, 0, 2, 14, 4, 14), Block.box(7, 4, 7, 9, 6, 9))
    };

    /** 生叫花兔只有一份，形态不变。 */
    private static final VoxelShape[] BEGGARS_RABBIT_SHAPES = new VoxelShape[]{
            Block.box(3, 0, 3, 13, 3, 13),
            Block.box(3, 0, 3, 13, 3, 13)
    };

    /** 熟叫花兔四份，随份数由小变大。 */
    private static final VoxelShape[] BEGGARS_RABBIT_COOKED_SHAPES = new VoxelShape[]{
            Block.box(4, 0, 4, 12, 2, 12),
            Block.box(3, 0, 3, 13, 3, 13),
            Block.box(2, 0, 2, 14, 4, 14),
            Block.box(2, 0, 2, 14, 4, 14),
            Block.box(2, 0, 2, 14, 5, 14)
    };

    public static final DeferredBlock<DragonFeastBlock> ROAST_FISH_WITH_GOLD_FOIL =
            BLOCKS.register("roast_fish_with_gold_foil", () -> new DragonFeastBlock(foodProperties(),
                    4, true, ALL_DRAGONS,
                    () -> new ItemStack[]{
                            new ItemStack(Items.GOLD_INGOT),
                            new ItemStack(Items.BONE),
                            new ItemStack(Items.GOLD_NUGGET)
                    }, FEAST_SHAPES));

    public static final DeferredBlock<DragonFeastBlock> FEAST_OF_VICTORY =
            BLOCKS.register("feast_of_victory", () -> new DragonFeastBlock(foodProperties(),
                    4, true, ALL_DRAGONS,
                    () -> new ItemStack[]{
                            new ItemStack(DSBlocks.GOLDEN_KNIGHT_HELMET.value()),
                            new ItemStack(Items.GOLD_NUGGET)
                    }, FEAST_SHAPES));

    public static final DeferredBlock<DragonFeastBlock> MOSS_PIE =
            BLOCKS.register("moss_pie", () -> new DragonFeastBlock(foodProperties(),
                    3, false, Set.of(BuiltInDragonSpecies.FOREST_DRAGON), () -> new ItemStack[0], MOSS_PIE_SHAPES));

    public static final DeferredBlock<DragonFeastBlock> BLOSSOM_PIE =
            BLOCKS.register("blossom_pie", () -> new DragonFeastBlock(foodProperties(),
                    3, false, Set.of(BuiltInDragonSpecies.FOREST_DRAGON), () -> new ItemStack[0], PIE_SHAPES));

    public static final DeferredBlock<DragonFeastBlock> LUMINOUS_PIE =
            BLOCKS.register("luminous_pie", () -> new DragonFeastBlock(foodProperties(),
                    3, false, Set.of(BuiltInDragonSpecies.FOREST_DRAGON, BuiltInDragonSpecies.SEA_DRAGON), () -> new ItemStack[0], PIE_SHAPES));

    public static final DeferredBlock<DragonFeastBlock> BEGGARS_RABBIT =
            BLOCKS.register("beggars_rabbit", () -> new DragonFeastBlock(foodProperties(),
                    1, false, Set.of(BuiltInDragonSpecies.FOREST_DRAGON), () -> new ItemStack[0], BEGGARS_RABBIT_SHAPES));

    public static final DeferredBlock<DragonFeastBlock> BEGGARS_RABBIT_COOKED =
            BLOCKS.register("beggars_rabbit_cooked", () -> new DragonFeastBlock(foodProperties(),
                    4, false, Set.of(BuiltInDragonSpecies.FOREST_DRAGON, BuiltInDragonSpecies.CAVE_DRAGON), () -> new ItemStack[0],
                    BEGGARS_RABBIT_COOKED_SHAPES));
}
