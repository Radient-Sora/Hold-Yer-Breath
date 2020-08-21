package sora.holdyerbreath.event;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import sora.holdyerbreath.HoldYerBreath;
import sora.holdyerbreath.init.ModItems;

@Mod.EventBusSubscriber(modid = HoldYerBreath.MOD_ID)
public class RegistryHandler {
    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                ModItems.AIR_BOTTLE,
                ModItems.EMPTY_AIR_BOTTLE
        );
    }
}
