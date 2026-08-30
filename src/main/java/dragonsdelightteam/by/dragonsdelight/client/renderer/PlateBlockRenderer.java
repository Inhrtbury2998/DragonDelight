package dragonsdelightteam.by.dragonsdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.Optional;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import dragonsdelightteam.by.dragonsdelight.blockentities.PlateBlockEntity;
import dragonsdelightteam.by.dragonsdelight.blocks.PlateBlock;
import dragonsdelightteam.by.dragonsdelight.client.model.PlateModelManager;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class PlateBlockRenderer implements BlockEntityRenderer<PlateBlockEntity> {
    private static final float CUSTOM_MODEL_SCALE = 0.75F;
    public static final float CUSTOM_MODEL_Y_OFFSET = 0.44F;
    public static final float HANDHELD_Y_OFFSET = 0.26F;

    private final ItemRenderer itemRenderer;

    public PlateBlockRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(PlateBlockEntity be, float partialTick, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemStack stack = be.getStoredItem();
        if (stack.isEmpty()) {
            return;
        }

        BlockState state = be.getBlockState();
        Direction facing = state.getValue(PlateBlock.FACING);

        poseStack.pushPose();
        poseStack.translate(0.5D, 0, 0.5D);
        poseStack.mulPose(Axis.YP.rotationDegrees(getRotationY(facing)));

        Optional<ResourceLocation> customModel = PlateModelManager.INSTANCE.getModelFor(stack.getItem());
        if (customModel.isPresent()) {
            ModelManager modelManager = Minecraft.getInstance().getModelManager();
            BakedModel model = modelManager.getModel(ModelResourceLocation.standalone(customModel.get()));

            if (model == modelManager.getMissingModel()) {
                // 映射的模型不存在时回退到手持模型，避免紫黑块
                applyHandheldTransform(poseStack);
                this.itemRenderer.renderStatic(stack, ItemDisplayContext.FIRST_PERSON_RIGHT_HAND,
                        packedLight, packedOverlay, poseStack, bufferSource, be.getLevel(), 0);
            } else {
                applyCustomModelTransform(poseStack);
                this.itemRenderer.render(stack, ItemDisplayContext.FIXED, false, poseStack, bufferSource,
                        packedLight, packedOverlay, model);
            }
        } else {
            applyHandheldTransform(poseStack);
            this.itemRenderer.renderStatic(stack, ItemDisplayContext.FIRST_PERSON_RIGHT_HAND,
                    packedLight, packedOverlay, poseStack, bufferSource, be.getLevel(), 0);
        }

        poseStack.popPose();
    }
    /**
     * 应用自定义模型的缩放和位移
     */
    private void applyCustomModelTransform(PoseStack poseStack) {
        poseStack.translate(0, CUSTOM_MODEL_Y_OFFSET, 0);
        poseStack.scale(CUSTOM_MODEL_SCALE, CUSTOM_MODEL_SCALE, CUSTOM_MODEL_SCALE);
    }

    /**
     * 应用手持物品的缩放和位移
     */
    private void applyHandheldTransform(PoseStack poseStack) {
        poseStack.translate(0, HANDHELD_Y_OFFSET, 0);
    }

    /**
     * 根据盘子朝向计算物品需要绕 Y 轴旋转的角度，
     * 使物品正面与盘子朝向保持一致。
     */
    private static float getRotationY(Direction facing) {
        return switch (facing) {
            case NORTH -> 0.0F;
            case EAST -> 90.0F;
            case SOUTH -> 180.0F;
            case WEST -> 270.0F;
            default -> 0.0F;
        };
    }
}
