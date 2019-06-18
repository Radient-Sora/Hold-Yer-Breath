package sora.holdyerbreath.init;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import sora.holdyerbreath.HoldYerBreath;

@Config(modid = HoldYerBreath.MODID)
public class ConfigHandler {

  @Config.Name("Better Diving")
  public static final BetterDivingConfig betterDiving = new BetterDivingConfig();

  @Config.RangeInt(min = 1, max = 64)
  @Config.Comment("Bottle Stacksize")
  public static int maxStackSize = 64;

  @Config.Comment("Does the bottle get used up after use")
  public static boolean usesBottleUp = false;

  @Config.RangeInt(min = 1, max = 10)
  @Config.Comment("How many bubbles of air's are restored per use")
  public static int restoredAirBubbles = 5;

  public static class BetterDivingConfig{
    @Config.Comment("Allow Better Diving Integration")
    public  boolean allowBetterDivingIntegration = true;

    @Config.RangeInt(min = 1, max = 100)
    @Config.Comment("Better Diving Air Percentage")
    public  int betterDivingAirPercentage = 20;
  }



  @Mod.EventBusSubscriber(modid = HoldYerBreath.MODID)
  public static class ReloadConfig{
    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent event){
      if(event.getModID().equals(HoldYerBreath.MODID)){
        ConfigManager.sync(HoldYerBreath.MODID, Config.Type.INSTANCE);
      }
    }
  }

}
