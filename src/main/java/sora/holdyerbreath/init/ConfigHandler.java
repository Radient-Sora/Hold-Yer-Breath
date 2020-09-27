package sora.holdyerbreath.init;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigHandler {

  public static class General{
    public final ForgeConfigSpec.ConfigValue<Integer> maxStackSize;
    public final ForgeConfigSpec.ConfigValue<Boolean> usesBottleUp;
    public final ForgeConfigSpec.ConfigValue<Integer> restoredAirBubbles;

    General(ForgeConfigSpec.Builder builder){
      builder.push("General");
      maxStackSize = builder
          .comment("Bottle Stacksize")
          .define("maxStackSize", 64);
      usesBottleUp = builder
          .comment("Does the bottle get used up after use")
          .define("usesBottleUp",false);
      restoredAirBubbles = builder
          .comment("How many bubbles of air's are restored per use")
          .define("restoredAirBubbles", 5);
      builder.pop();
    }
  }
  private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
  public static final General general = new General(BUILDER);
  public static final ForgeConfigSpec configSpec = BUILDER.build();
}
