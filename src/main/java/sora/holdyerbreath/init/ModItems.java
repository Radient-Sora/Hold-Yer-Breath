package sora.holdyerbreath.init;

import net.minecraft.item.Item;
import sora.holdyerbreath.items.ItemReinforcedBottle;

public class ModItems {
    public static Item REINFORCED_AIR_BOTTLE = new ItemReinforcedBottle(
            "reinforced_air_bottle", 1
    );
    public static Item MEDIUM_REINFORCED_AIR_BOTTLE = new ItemReinforcedBottle(
            "medium_reinforced_air_bottle", 2
    );
    public static Item LARGE_REINFORCED_AIR_BOTTLE = new ItemReinforcedBottle(
            "large_reinforced_air_bottle", 4
    );
}
