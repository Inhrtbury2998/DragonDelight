package dragonsdelightteam.by.dragonsdelight.datagen;

import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import dragonsdelightteam.by.dragonsdelight.items.ModItemEnum;
import dragonsdelightteam.by.dragonsdelight.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DragonsDelight.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (ModItemEnum itemEnum : ModItemEnum.values()) {
            String id = itemEnum.getId();
            DeferredItem<Item> deferredItem = ModItems.get(itemEnum);
            Item item = deferredItem.get();

            // 方块物品（含 PLATE 与可放置食物方块）的模型由方块状态生成器处理
            if (item instanceof net.minecraft.world.item.BlockItem) {
                continue;
            }

            // 生成普通物品模型：parent 为 item/generated，贴图为 items/<id>
            basicItem(item);
        }
    }
}