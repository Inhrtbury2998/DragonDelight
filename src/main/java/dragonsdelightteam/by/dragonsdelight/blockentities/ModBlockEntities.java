package dragonsdelightteam.by.dragonsdelight.blockentities;

import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import dragonsdelightteam.by.dragonsdelight.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, DragonsDelight.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PlateBlockEntity>> PLATE =
            BLOCK_ENTITIES.register("plate", () ->
                    BlockEntityType.Builder.of(PlateBlockEntity::new, ModBlocks.PLATE.get()).build(null));
}
