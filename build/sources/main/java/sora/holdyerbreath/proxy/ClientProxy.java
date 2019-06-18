package sora.holdyerbreath.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sora.holdyerbreath.HoldYerBreath;
import sora.holdyerbreath.init.ModItems;

@Mod.EventBusSubscriber(modid = HoldYerBreath.MODID)
public class ClientProxy extends CommonProxy {

  @Override
  public void preInit(FMLPreInitializationEvent event){
    super.preInit(event);
  }

  @Override
  public void Init(FMLInitializationEvent event){
    super.Init(event);
  }

  @Override
  public void postInit(FMLPostInitializationEvent event){
    super.postInit(event);
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public static void rendModel(ModelRegistryEvent event){
    regModel(ModItems.AIR_BOTTLE);
    regModel(ModItems.EMPTY_AIR_BOTTLE);
  }

  public static  void regModel(Item item){
    ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
  }

}
