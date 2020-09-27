package sora.holdyerbreath.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sora.holdyerbreath.HoldYerBreath;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod.EventBusSubscriber(bus = MOD, modid = HoldYerBreath.MODID)
public class ModEvents {

  @SubscribeEvent
  public static void onItemRegistry(RegistryEvent.Register<Item> event){
    event.getRegistry().registerAll(
        ModItems.AIR_BOTTLE,
        ModItems.EMPTY_AIR_BOTTLE
    );
  }
}
