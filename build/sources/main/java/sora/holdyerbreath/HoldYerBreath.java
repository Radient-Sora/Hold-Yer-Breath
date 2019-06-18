package sora.holdyerbreath;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import sora.holdyerbreath.init.ModItems;
import sora.holdyerbreath.proxy.CommonProxy;

@Mod(modid = HoldYerBreath.MODID , name = HoldYerBreath.MODNAME)
public class HoldYerBreath {

  public static final String MODID = "holdyerbreath";
  public static final String MODNAME = "Hold Yer Breath";
  public static final String CLIENTPROXY = "sora.holdyerbreath.proxy.ClientProxy";
  public static final String COMMONPROXY = "sora.holdyerbreath.proxy.CommonProxy";

  @Mod.Instance
  public static HoldYerBreath INSTANCE;

  @SidedProxy(clientSide = HoldYerBreath.CLIENTPROXY, serverSide = HoldYerBreath.COMMONPROXY)
  public static CommonProxy proxy;

  public static void preInit(FMLPreInitializationEvent event) {
    proxy.preInit(event);
  }

  public static void Init(FMLInitializationEvent event) {
    proxy.Init(event);
  }

  public static void postInit(FMLPostInitializationEvent event) {
    proxy.postInit(event);
  }

  public static final CreativeTabs CREATIVE_TABS = new CreativeTabs(HoldYerBreath.MODID) {
    @Override
    public ItemStack getTabIconItem() {
      return new ItemStack(ModItems.AIR_BOTTLE);
    }
  };
}
