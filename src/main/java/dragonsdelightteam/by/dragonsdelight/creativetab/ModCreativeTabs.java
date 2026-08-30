package dragonsdelightteam.by.dragonsdelight.creativetab;

import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import dragonsdelightteam.by.dragonsdelight.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DragonsDelight.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB =
            CREATIVE_MODE_TABS.register("dragons_delight", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + DragonsDelight.MODID + ".tab"))
                    .icon(() -> {
                        ItemStack icon = ItemStack.EMPTY;
                        var entries = ModItems.ITEMS.getEntries();
                        if (!entries.isEmpty()) {
                            icon = new ItemStack(entries.iterator().next().get());
                        }
                        return icon;
                    })
                    // 配置展示的物品
                    .displayItems((parameters, output) -> {
                        // 直接遍历 ModItems 注册器中的所有物品并拉入 Tab
                        ModItems.ITEMS.getEntries().forEach(itemHolder -> output.accept(itemHolder.get()));
                    })
                    .build());
}
