package dragonsdelightteam.by.dragonsdelight.datagen.tags;

import by.dragonsurvivalteam.dragonsurvival.registry.DSBlocks;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.Translation;
import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class DragonsDelightItemTags extends ItemTagsProvider {
    @Translation(
            comments = {"Knight Helmet"}
    )
    public static final TagKey<Item> KNIGHT_HELMET = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(DragonsDelight.MODID, "knight_helmets"));
    public DragonsDelightItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                   CompletableFuture<TagsProvider.TagLookup<Block>> blockTagLookup) {
        super(output, lookupProvider, blockTagLookup);
    }
    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // 创建标签，并添加方块
        tag(KNIGHT_HELMET)
                .add(DSBlocks.BLACK_KNIGHT_HELMET.value().asItem())
                .add(DSBlocks.GOLDEN_KNIGHT_HELMET.value().asItem())
                .add(DSBlocks.GRAY_KNIGHT_HELMET.value().asItem());
    }
}
