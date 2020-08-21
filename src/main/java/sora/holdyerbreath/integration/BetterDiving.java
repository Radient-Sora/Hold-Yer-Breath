package sora.holdyerbreath.integration;

import meldexun.better_diving.capability.diving.CapabilityDivingAttributesProvider;
import meldexun.better_diving.capability.diving.ICapabilityDivingAttributes;
import net.minecraft.entity.player.EntityPlayer;

public class BetterDiving {
    public static void setCurrentAir(EntityPlayer player, int airAmount) {
        ICapabilityDivingAttributes attributes = player.getCapability(CapabilityDivingAttributesProvider.DIVING_ATTRIBUTES, null);
        assert attributes != null;
        attributes.setOxygen(airAmount);
    }

    public static int getCurrentAir(EntityPlayer player) {
        ICapabilityDivingAttributes attributes = player.getCapability(CapabilityDivingAttributesProvider.DIVING_ATTRIBUTES, null);
        assert attributes != null;
        return attributes.getOxygen();
    }

    public static int getMaxAir(EntityPlayer player) {
        ICapabilityDivingAttributes attributes = player.getCapability(CapabilityDivingAttributesProvider.DIVING_ATTRIBUTES, null);
        assert attributes != null;
        return attributes.getOxygenCapacity();
    }
}
