package sora.holdyerbreath.init;

import net.minecraft.item.Item;
import sora.holdyerbreath.items.ItemAirBottle;
import sora.holdyerbreath.items.ItemEmptyAirBottle;

public class ModItems {

  public static Item AIR_BOTTLE = new ItemAirBottle();
  public static Item EMPTY_AIR_BOTTLE = new ItemEmptyAirBottle();

  static {
    new ModItems();
  }

}
