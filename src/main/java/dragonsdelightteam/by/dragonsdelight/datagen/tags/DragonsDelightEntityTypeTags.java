package dragonsdelightteam.by.dragonsdelight.datagen.tags;

import by.dragonsurvivalteam.dragonsurvival.registry.DSEntities;
import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * 被击杀后掉落人肉的实体。
 * <p>
 * 其它模组或数据包可以通过向 {@code dragonsdelight:drops_human_flesh} 追加条目，
 * 把自己添加的人类类实体纳入掉落范围。
 */
public class DragonsDelightEntityTypeTags extends EntityTypeTagsProvider {
    public static final TagKey<EntityType<?>> DROPS_HUMAN_FLESH = TagKey.create(Registries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(DragonsDelight.MODID, "drops_human_flesh"));

    public DragonsDelightEntityTypeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                        ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, DragonsDelight.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(DROPS_HUMAN_FLESH)
                .add(EntityType.VILLAGER)
                .add(EntityType.WANDERING_TRADER)
                // 灾厄村民：evoker / illusioner / pillager / vindicator
                .addTag(EntityTypeTags.ILLAGER)
                // DragonSurvival 人类公会成员；猎犬（hunter_hound）与狮鹫（hunter_griffin）不是人类，故不纳入
                .add(DSEntities.HUNTER_SPEARMAN.get())
                .add(DSEntities.HUNTER_KNIGHT.get())
                .add(DSEntities.HUNTER_AMBUSHER.get())
                .add(DSEntities.HUNTER_LEADER.get());
    }
}
