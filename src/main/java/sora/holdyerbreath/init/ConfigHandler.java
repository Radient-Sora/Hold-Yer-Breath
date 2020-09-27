package sora.holdyerbreath.init;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import sora.holdyerbreath.HoldYerBreath;

@Config(modid = HoldYerBreath.MOD_ID)
public class ConfigHandler {
    @Config.Comment("Does the bottle get used up after use")
    public static boolean usesBottleUp = false;

    @Config.RangeInt(min = 1, max = 10)
    @Config.Comment("How many bubbles of air's are restored per use")
    public static int restoredAirBubbles = 5;

    @Mod.EventBusSubscriber(modid = HoldYerBreath.MOD_ID)
    public static class ReloadConfig {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent event) {
            if (event.getModID().equals(HoldYerBreath.MOD_ID)) {
                ConfigManager.sync(HoldYerBreath.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}
