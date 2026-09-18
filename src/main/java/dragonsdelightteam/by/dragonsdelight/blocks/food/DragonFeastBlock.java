package dragonsdelightteam.by.dragonsdelight.blocks.food;

import by.dragonsurvivalteam.dragonsurvival.common.capability.DragonStateProvider;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.DragonSpecies;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Supplier;

/**
 * 可放置（蛋糕式）的食物方块。所有食谱方块均由本类统一实现，
 * 每种食物在 {@link dragonsdelightteam.by.dragonsdelight.blocks.ModBlocks} 注册时传入各自的参数。
 *
 * <ul>
 *     <li>所有食谱方块直接挖掘都不会掉落（未注册战利品表）。</li>
 *     <li>只有符合 {@code allowedSpecies} 的龙种玩家方可食用，人类及其余龙种无法食用。</li>
 *     <li>食用时调用 {@code player.getFoodData().eat(...)}，从而被 DragonSurvival 的
 *         {@code FoodDataMixin} 拦截并套用对应龙种的饮食（pending block food）。
 *     </li>
 *     <li>当 {@code hasLeftovers} 为 {@code true} 且份数归零时，再次右键会移除方块并掉落
 *         {@code leftoverDrops}（盛装物与额外物品）。</li>
 *     <li>选中框与碰撞箱按份数取 {@code shapes} 中对应下标的形状，因此被吃掉的部分不再有体积。</li>
 * </ul>
 */
public class DragonFeastBlock extends Block {
    public static final MapCodec<DragonFeastBlock> CODEC = simpleCodec(DragonFeastBlock::new);

    /**
     * 份数属性必须是静态的：{@link Block} 的构造器会回调 {@link #createBlockStateDefinition}，
     * 此时实例字段尚未赋值，使用实例字段会 NPE（参考 Farmer's Delight 的 FeastBlock）。
     * 取值范围固定为 0~4，各食物通过默认值控制实际最大份数。
     */
    public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, 4);

    /** 仅供 {@link #CODEC} 反序列化使用；实际注册的方块都会传入自己的形状。 */
    private static final VoxelShape[] DEFAULT_SHAPES = new VoxelShape[]{
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D)
    };

    private final Set<ResourceKey<DragonSpecies>> allowedSpecies;
    private final boolean hasLeftovers;
    private final Supplier<ItemStack[]> leftoverDrops;
    /** 下标 = servings 数值；同时用于选中框与碰撞箱（方块描边取 {@code getShape}，碰撞默认复用同一形状）。 */
    private final VoxelShape[] shapes;

    /** 仅供 {@link #CODEC} 反序列化使用，实际注册的方块一律使用带参数的构造器。 */
    private DragonFeastBlock(BlockBehaviour.Properties properties) {
        this(properties, 1, false, Set.of(), () -> new ItemStack[0], DEFAULT_SHAPES);
    }

    public DragonFeastBlock(BlockBehaviour.Properties properties,
                            int maxServings,
                            boolean hasLeftovers,
                            Set<ResourceKey<DragonSpecies>> allowedSpecies,
                            Supplier<ItemStack[]> leftoverDrops,
                            VoxelShape[] shapes) {
        super(properties);
        this.hasLeftovers = hasLeftovers;
        this.allowedSpecies = allowedSpecies;
        this.leftoverDrops = leftoverDrops;
        this.shapes = shapes;
        this.registerDefaultState(this.stateDefinition.any().setValue(SERVINGS, maxServings));
    }

    @Override
    protected @NotNull MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SERVINGS);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return this.shapes[Math.min(state.getValue(SERVINGS), this.shapes.length - 1)];
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, BlockPos pos) {
        return canSupportRigidBlock(level, pos.below());
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull Direction facing, @NotNull BlockState facingState,
                                           @NotNull LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
        return facing == Direction.DOWN && !state.canSurvive(level, currentPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public boolean isPathfindable(@NotNull BlockState state, @NotNull PathComputationType type) {
        return false;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return state.getValue(SERVINGS);
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                                                       @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        return this.dragonUse(level, pos, state, player);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                                                        @NotNull Player player, @NotNull BlockHitResult hitResult) {
        ItemInteractionResult result = this.dragonUse(level, pos, state, player);
        return result.consumesAction() ? InteractionResult.sidedSuccess(level.isClientSide) : InteractionResult.PASS;
    }

    private ItemInteractionResult dragonUse(final Level level, final BlockPos pos, final BlockState state, final Player player) {
        if (!this.canEat(player)) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.translatable("block.dragonsdelight.cannot_eat"), true);
            }
            return ItemInteractionResult.FAIL;
        }
        if (!player.canEat(false)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        // 在客户端仅返回成功以播放右键动画（实际逻辑在服务端执行）
        if (level.isClientSide) {
            return ItemInteractionResult.SUCCESS;
        }

        int servingsNow = state.getValue(SERVINGS);

        if (servingsNow == 0) {
            this.clearBlock(level, pos);
            return ItemInteractionResult.SUCCESS;
        }

        player.getFoodData().eat(2, 0.1F);
        level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1.0F, 1.0F);
        level.setBlock(pos, state.setValue(SERVINGS, servingsNow - 1), 3);
        player.awardStat(Stats.ITEM_USED.get(state.getBlock().asItem()));
        return ItemInteractionResult.SUCCESS;
    }

    /** 份数归零后的处理：若有盛装物则掉落并移除方块；否则直接移除方块。 */
    private void clearBlock(final Level level, final BlockPos pos) {
        if (this.hasLeftovers) {
            for (ItemStack drop : this.leftoverDrops.get()) {
                ItemEntity entity = new ItemEntity(level,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, drop.copy());
                entity.setDefaultPickUpDelay();
                level.addFreshEntity(entity);
            }
        }
        level.removeBlock(pos, false);
    }

    private boolean canEat(final Player player) {
        if (!DragonStateProvider.isDragon(player)) {
            return false;
        }
        Holder<DragonSpecies> species = DragonStateProvider.getData(player).species();
        for (ResourceKey<DragonSpecies> key : this.allowedSpecies) {
            if (species.is(key)) {
                return true;
            }
        }
        return false;
    }
}
