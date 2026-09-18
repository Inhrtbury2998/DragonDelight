package dragonsdelightteam.by.dragonsdelight.datagen;

import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = DragonsDelight.MODID)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // 物品模型
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        // 方块状态与方块物品模型
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
        // 配方
        generator.addProvider(event.includeServer(), new DragonsDelightRecipes(packOutput, lookupProvider));
        // 龙种饮食数据（决定哪些龙可食用哪些食物）
//        generator.addProvider(event.includeServer(), new DietEntryProvider(packOutput, lookupProvider));
    }
}
