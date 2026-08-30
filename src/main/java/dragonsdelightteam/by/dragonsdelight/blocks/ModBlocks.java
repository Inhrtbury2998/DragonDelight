package dragonsdelightteam.by.dragonsdelight.blocks;

import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

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
}
