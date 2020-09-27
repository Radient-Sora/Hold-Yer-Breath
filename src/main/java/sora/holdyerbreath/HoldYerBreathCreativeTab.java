package sora.holdyerbreath;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import sora.holdyerbreath.init.ModItems;

public class HoldYerBreathCreativeTab extends ItemGroup {

  private static final HoldYerBreathCreativeTab INSTANCE = new HoldYerBreathCreativeTab();

  public HoldYerBreathCreativeTab(){
    super(HoldYerBreath.MODID);
  }

  public static HoldYerBreathCreativeTab getInstance(){
    return INSTANCE;
  }

  @Override
  public ItemStack createIcon() {
    return new ItemStack(ModItems.AIR_BOTTLE);
  }
}
