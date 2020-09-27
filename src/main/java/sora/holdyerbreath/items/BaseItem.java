package sora.holdyerbreath.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import sora.holdyerbreath.HoldYerBreath;
import sora.holdyerbreath.HoldYerBreathCreativeTab;

public class BaseItem extends Item {
  public BaseItem(String name) {
    super(new Properties().group(HoldYerBreathCreativeTab.getInstance()));
    this.setRegistryName(new ResourceLocation(HoldYerBreath.MODID, name));
  }
}
