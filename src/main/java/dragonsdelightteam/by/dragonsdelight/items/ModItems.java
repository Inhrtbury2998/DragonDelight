package dragonsdelightteam.by.dragonsdelight.items;

import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DragonsDelight.MODID);

    private static final Map<ModItemEnum, DeferredItem<Item>> ITEM_MAP = new EnumMap<>(ModItemEnum.class);

    static {
        for (ModItemEnum itemEnum : ModItemEnum.values()) {
            DeferredItem<Item> registeredItem = ITEMS.register(itemEnum.getId(), itemEnum::createItem);
            ITEM_MAP.put(itemEnum, registeredItem);
        }
    }

    public static DeferredItem<Item> get(ModItemEnum itemEnum) {
        return ITEM_MAP.get(itemEnum);
    }
}
