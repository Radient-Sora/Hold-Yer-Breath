package sora.holdyerbreath.event;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import sora.holdyerbreath.HoldYerBreath;
import sora.holdyerbreath.init.ModItems;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = HoldYerBreath.MOD_ID)
public class ModelEventHandler {
    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        registerModel(ModItems.SMALL_REINFORCED_AIR_BOTTLE);
        registerModel(ModItems.MEDIUM_REINFORCED_AIR_BOTTLE);
        registerModel(ModItems.LARGE_REINFORCED_AIR_BOTTLE);
    }

    private static void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(
                item, 0,
                new ModelResourceLocation(
                        Objects.requireNonNull(item.getRegistryName()),
                        "inventory"
                )
        );
    }
}
