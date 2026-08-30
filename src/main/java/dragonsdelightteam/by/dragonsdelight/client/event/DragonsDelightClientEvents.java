package dragonsdelightteam.by.dragonsdelight.client.event;

import com.google.gson.JsonObject;
import java.io.Reader;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import dragonsdelightteam.by.dragonsdelight.blockentities.ModBlockEntities;
import dragonsdelightteam.by.dragonsdelight.client.model.PlateModelManager;
import dragonsdelightteam.by.dragonsdelight.client.renderer.PlateBlockRenderer;

@EventBusSubscriber(modid = DragonsDelight.MODID, value = Dist.CLIENT)
public class DragonsDelightClientEvents {

    private static final String PLATE_MODEL_DIR = "dragonsdelight/plate_model";

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.PLATE.get(), PlateBlockRenderer::new);
    }

    @SubscribeEvent
    public static void registerReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(PlateModelManager.INSTANCE);
    }

    @SubscribeEvent
    public static void onRegisterAdditionalModels(ModelEvent.RegisterAdditional event) {
        // 把资源包中 plate_model 映射 JSON 引用的模型都注册为 standalone，
        // 这样 ModelManager 才会烘焙它们，Renderer 里才能通过 standalone key 取到。
        ResourceManager manager = Minecraft.getInstance().getResourceManager();
        Map<ResourceLocation, Resource> resources = manager.listResources(PLATE_MODEL_DIR,
                id -> id.getPath().endsWith(".json"));

        for (Map.Entry<ResourceLocation, Resource> entry : resources.entrySet()) {
            try (Reader reader = entry.getValue().openAsReader()) {
                JsonObject json = GsonHelper.parse(reader);
                if (json.has("model")) {
                    ResourceLocation modelId = ResourceLocation.parse(GsonHelper.getAsString(json, "model"));
                    event.register(ModelResourceLocation.standalone(modelId));
                }
            } catch (Exception e) {
                DragonsDelight.LOGGER.warn("Failed to load plate model mapping from {}: {}", entry.getKey(), e.getMessage());
            }
        }
    }
}
