package dragonsdelightteam.by.dragonsdelight.items;

import dragonsdelightteam.by.dragonsdelight.blocks.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public enum ModItemEnum {
    // 基础物品
    ROAST_FISH_WITH_GOLD_FOIL("roast_fish_with_gold_foil", p -> p),
    FEAST_OF_VICTORY("feast_of_victory", p -> p),
    ORE_SAUCE("ore_sauce", p -> p),
    LUMINOUS_SAUCE("luminous_sauce", p -> p),
    LAVA_DRINK("lava_drink", p -> p),
    CAVE_SOUP("cave_soup", p -> p),
    EMBER_NOODLES("ember_noodles", p -> p),
    ORE_NOODLES("ore_noodles", p -> p),
    BISQUE_NOODLES("bisque_noodles", p -> p),
    RELIC_NOODLES("relic_noodles", p -> p),
    LUSH_NOODLES("lush_noodles", p -> p),
    WETLAND_NOODLES("wetland_noodles", p -> p),
    SUMMER_NOODLES("summer_noodles", p -> p),
    CAVE_BARBECUE_ON_A_STICK("cave_barbecue_on_a_stick", p -> p),
    MOSS_PIE("moss_pie", p -> p),
    SLICE_OF_MOSS_PIE("slice_of_moss_pie", p -> p),
    BLOSSOM_PIE("blossom_pie", p -> p),
    SLICE_OF_BLOSSOM_PIE("slice_of_blossom_pie", p -> p),
    LUMINOUS_PIE("luminous_pie", p -> p),
    SLICE_OF_LUMINOUS_PIE("slice_of_luminous_pie", p -> p),
    VOLCANIC_EGG("volcanic_egg", p -> p),
    RED_HEAT_SANDWICH("red_heat_sandwich", p -> p),
    BEGGARS_RABBIT("beggars_rabbit", p -> p),
    GRILLED_COLD_NOODLES("grilled_cold_noodles", p -> p),
    POISONOUS_POTATO_COOKIE("poisonous_potato_cookie", p -> p),
    MEAT_ZONGZI("meat_zongzi", p -> p),
    JUNGLE_SALAD("jungle_salad", p -> p),
    GRASS_MASHED_POTATOES("grass_mashed_potatoes", p -> p),
    FISH_ROLL("fish_roll", p -> p),
    TURTLE_EGG_CUSTARD("turtle_egg_custard", p -> p),
    INKY_FISH_ASPIC("inky_fish_aspic", p -> p),
    SEAFOOD_SAUCE("seafood_sauce", p -> p),
    CORAL_CHEW_BAR("coral_chew_bar", p -> p),
    SPRING_FISH("spring_fish", p -> p),
    CRISPY_ROLL("crispy_roll", p -> p),
    ROYAL_LUNCHBOX("royal_lunchbox", p -> p),
    ROYAL_FOOD("royal_food", p -> p),
    SHREDDED_POISONOUS_POTATO("shredded_poisonous_potato", p -> p),
    MASHED_POISONOUS_POTATO("mashed_poisonous_potato", p -> p),
    POISONOUS_POTATO_NOODLES("poisonous_potato_noodles", p -> p),
    HUMAN_FLESH("human_flesh", p -> p),
    HUMAN_FLESH_SLICE("human_flesh_slice", p -> p),
    COOKED_HUMAN_MEAT("cooked_human_meat", p -> p),
    COOKED_HUMAN_MEAT_SLICE("cooked_human_meat_slice", p -> p),
    CURING_HUMAN_MEAT("curing_human_meat", p -> p),
    CURING_HUMAN_MEAT_SLICE("curing_human_meat_slice", p -> p),
    PLATE("plate", props -> new BlockItem(ModBlocks.PLATE.get(), props), p -> p),
    ;

    private final String id;
    private final Function<Item.Properties, Item.Properties> propertyModifier;
    private final ItemFactory itemFactory;

    ModItemEnum(String id, Function<Item.Properties, Item.Properties> propertyModifier) {
        this(id, Item::new, propertyModifier);
    }

    ModItemEnum(String id, ItemFactory itemFactory, Function<Item.Properties, Item.Properties> propertyModifier) {
        this.id = id;
        this.itemFactory = itemFactory;
        this.propertyModifier = propertyModifier;
    }

    public String getId() { return id; }

    public Item createItem() {
        Item.Properties props = propertyModifier.apply(new Item.Properties());
        return itemFactory.create(props);
    }

    @FunctionalInterface
    public interface ItemFactory {
        Item create(Item.Properties properties);
    }
}