package sora.holdyerbreath.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import sora.holdyerbreath.HoldYerBreath;

@Mod.EventBusSubscriber(modid = HoldYerBreath.MODID)
public class RegistryHandler {

  @SubscribeEvent
  public static void registerItems(RegistryEvent.Register<Item> event){
    event.getRegistry().register(ModItems.AIR_BOTTLE);
    event.getRegistry().register(ModItems.EMPTY_AIR_BOTTLE);
  }
}
