package dragonsdelightteam.by.dragonsdelight.client.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlateModelManager extends SimpleJsonResourceReloadListener {
    public static final PlateModelManager INSTANCE = new PlateModelManager();
    private static final String DIRECTORY = "dragonsdelight/plate_model";

    private final Map<ResourceLocation, ResourceLocation> itemToModel = new HashMap<>();

    private PlateModelManager() {
        super(new Gson(), DIRECTORY);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> objects, ResourceManager resourceManager, ProfilerFiller profiler) {
        itemToModel.clear();
        for (Map.Entry<ResourceLocation, JsonElement> entry : objects.entrySet()) {
            ResourceLocation itemId = entry.getKey();
            JsonObject json = GsonHelper.convertToJsonObject(entry.getValue(), "top element");
            String modelStr = GsonHelper.getAsString(json, "model");
            ResourceLocation modelId = ResourceLocation.parse(modelStr);
            itemToModel.put(itemId, modelId);
        }
    }

    public Optional<ResourceLocation> getModelFor(Item item) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
        return Optional.ofNullable(itemToModel.get(itemId));
    }
}
