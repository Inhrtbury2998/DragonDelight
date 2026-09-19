package dragonsdelightteam.by.dragonsdelight.datagen;

import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import dragonsdelightteam.by.dragonsdelight.blocks.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    /** 与 {@code horizontalBlock} 一致：facing=north 对应 0°，即模型以北方为基准。 */
    private static final int DEFAULT_ANGLE_OFFSET = 180;

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, DragonsDelight.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // 数组下标 = servings 数值，值 = 对应模型文件名（不含前缀目录）
        feast(ModBlocks.ROAST_FISH_WITH_GOLD_FOIL.get(), new String[]{
                "roast_fish_with_gold_foil_4",
                "roast_fish_with_gold_foil_3",
                "roast_fish_with_gold_foil_2",
                "roast_fish_with_gold_foil_1",
                "roast_fish_with_gold_foil"
        });
        feast(ModBlocks.FEAST_OF_VICTORY.get(), new String[]{
                "feast_of_victory_4",
                "feast_of_victory_3",
                "feast_of_victory_2",
                "feast_of_victory_1",
                "feast_of_victory"
        });
        feast(ModBlocks.MOSS_PIE.get(), new String[]{
                "moss_pie_slice3",
                "moss_pie_slice2",
                "moss_pie_slice1",
                "moss_pie"
        });
        feast(ModBlocks.BLOSSOM_PIE.get(), new String[]{
                "blossom_pie_slice3",
                "blossom_pie_slice2",
                "blossom_pie_slice1",
                "blossom_pie"
        });
        feast(ModBlocks.LUMINOUS_PIE.get(), new String[]{
                "luminous_pie_slice3",
                "luminous_pie_slice2",
                "luminous_pie_slice1",
                "luminous_pie"
        });
        feast(ModBlocks.BEGGARS_RABBIT.get(), new String[]{
                "beggars_rabbit",
                "beggars_rabbit"
        });
        feast(ModBlocks.BEGGARS_RABBIT_COOKED.get(), new String[]{
                "beggars_rabbit_cooked_slice4",
                "beggars_rabbit_cooked_slice3",
                "beggars_rabbit_cooked_slice2",
                "beggars_rabbit_cooked_slice1",
                "beggars_rabbit_cooked"
        });
    }

    private void feast(Block block, String[] models) {
        IntegerProperty servings = (IntegerProperty) block.getStateDefinition().getProperty("servings");
        DirectionProperty facing = (DirectionProperty) block.getStateDefinition().getProperty("facing");
        var builder = this.getVariantBuilder(block);
        // servings 属性取值范围固定为 0~4，必须覆盖全部状态；模型不足时复用最后一个模型。
        if (servings != null) {
            for (int s = 0; s < servings.getPossibleValues().size(); s++) {
                ModelFile model = existingModel(models[Math.min(s, models.length - 1)]);
                if (facing != null) {
                    for (Direction direction : facing.getPossibleValues()) {
                        builder.partialState()
                                .with(servings, s)
                                .with(facing, direction)
                                .modelForState()
                                .modelFile(model)
                                // 与形状旋转保持一致：形状以 facing=north 为基准，模型同样按此角度旋转
                                .rotationY(((int) direction.toYRot() + DEFAULT_ANGLE_OFFSET) % 360)
                                .addModel();
                    }
                }
            }
        }
        // BlockItem 模型：使用物品栏贴图（item/generated + item/<id>），而不是方块模型
        this.itemModels().basicItem(block.asItem());
    }

    private ModelFile existingModel(String path) {
        return this.models().getExistingFile(
                ResourceLocation.fromNamespaceAndPath(DragonsDelight.MODID, "block/" + path));
    }
}