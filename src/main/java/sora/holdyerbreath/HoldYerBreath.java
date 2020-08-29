package sora.holdyerbreath;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import sora.holdyerbreath.init.ModItems;

@Mod(modid = HoldYerBreath.MOD_ID, name = HoldYerBreath.MOD_NAME)
public class HoldYerBreath {
    public static final String MOD_ID = "holdyerbreath";
    public static final String MOD_NAME = "Hold Yer Breath";

    @SuppressWarnings("unused")
    @Mod.Instance
    public static HoldYerBreath INSTANCE;

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(HoldYerBreath.MOD_ID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.SMALL_REINFORCED_AIR_BOTTLE);
        }
    };
}
