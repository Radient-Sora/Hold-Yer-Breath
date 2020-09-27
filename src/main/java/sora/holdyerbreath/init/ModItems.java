package sora.holdyerbreath.init;

import net.minecraft.item.Item;
import sora.holdyerbreath.items.ItemReinforcedBottle;

public class ModItems {
    public static Item SMALL_REINFORCED_AIR_BOTTLE = new ItemReinforcedBottle(
            "small_reinforced_bottle", 1, ConfigHandler.restoredAirBubbles
    );
    public static Item MEDIUM_REINFORCED_AIR_BOTTLE = new ItemReinforcedBottle(
            "medium_reinforced_bottle", 2, ConfigHandler.restoredAirBubbles
    );
    public static Item LARGE_REINFORCED_AIR_BOTTLE = new ItemReinforcedBottle(
            "large_reinforced_bottle", 4, ConfigHandler.restoredAirBubbles
    );
}
