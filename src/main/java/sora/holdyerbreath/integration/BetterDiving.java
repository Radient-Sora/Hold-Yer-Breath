package sora.holdyerbreath.integration;

import meldexun.betterDiving.capability.DivingAttributesProvider;
import meldexun.betterDiving.capability.IDivingAttributes;
import net.minecraft.entity.player.EntityPlayer;

public class BetterDiving {

  public static void setCurrentAir(EntityPlayer player, int airAmount){
    IDivingAttributes attributes = player.getCapability(DivingAttributesProvider.DIVING, null);
    attributes.setCurrentAir(airAmount);
  }

  public static int getCurrentAir(EntityPlayer player){
    IDivingAttributes attributes = player.getCapability(DivingAttributesProvider.DIVING, null);
  return attributes.getCurrentAir();
  }

  public static int getMaxAir(EntityPlayer player){
    IDivingAttributes attributes = player.getCapability(DivingAttributesProvider.DIVING, null);
    return attributes.getMaxAir();
  }
}
