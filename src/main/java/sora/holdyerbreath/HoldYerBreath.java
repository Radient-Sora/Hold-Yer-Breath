package sora.holdyerbreath;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import sora.holdyerbreath.init.ConfigHandler;
import sora.holdyerbreath.proxy.ClientProxy;
import sora.holdyerbreath.proxy.CommonProxy;
import sora.holdyerbreath.proxy.IProxy;

@Mod(HoldYerBreath.MODID)
public class HoldYerBreath {

  public HoldYerBreath instance;
  private static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new CommonProxy());
  public static final String MODID = "holdyerbreath";

  public HoldYerBreath(){
    ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigHandler.configSpec);
    MinecraftForge.EVENT_BUS.register(this);
  }

  public HoldYerBreath getInstance(){
    return instance;
  }

  public static IProxy getProxy(){
    return proxy;
  }

}
