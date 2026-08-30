package dragonsdelightteam.by.dragonsdelight;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@EventBusSubscriber(modid = DragonsDelight.MODID)
public class ModResourcePacks {
    @SubscribeEvent
    public static void onAddPackFinders(AddPackFindersEvent event) {
        // 确保只在注册客户端资源包时执行
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {

            // 资源包唯一的 ResourceLocation 标识
            // 格式: ResourceLocation.fromNamespaceAndPath("modid", "资源包文件夹相对路径")
            ResourceLocation packId = ResourceLocation.fromNamespaceAndPath(
                    DragonsDelight.MODID,
                    "resourcepacks/dragonsdelight_3d_model"
            );

            // 注册内置资源包
            event.addPackFinders(
                    packId,
                    PackType.CLIENT_RESOURCES,
                    Component.translatable("resourcepack.dragonsdelight.3d_model"), // 显示在资源包列表中的名称
                    PackSource.BUILT_IN,             // 标记来源为内置 (BUILT_IN)
                    true,                            // 是否默认开启 (true = 默认开启, false = 默认关闭)
                    Pack.Position.TOP                 // 开启时的层级位置 (TOP 或 BOTTOM)
            );
        }
    }
}
