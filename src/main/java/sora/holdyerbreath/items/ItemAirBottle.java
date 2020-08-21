package sora.holdyerbreath.items;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.items.ItemHandlerHelper;
import sora.holdyerbreath.HoldYerBreath;
import sora.holdyerbreath.init.ConfigHandler;
import sora.holdyerbreath.init.ModItems;
import sora.holdyerbreath.integration.BetterDiving;

public class ItemAirBottle extends Item {

    public ItemAirBottle(String name) {
        super();
        this.setRegistryName(new ResourceLocation(HoldYerBreath.MOD_ID, name));
        this.setUnlocalizedName(name);
        this.setCreativeTab(HoldYerBreath.CREATIVE_TABS);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return ConfigHandler.maxStackSize;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 16;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (playerIn.isInsideOfMaterial(Material.WATER)) {
            if (!worldIn.isRemote && !stack.isEmpty()) {
                playerIn.setActiveHand(handIn);
            }
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
        } else {
            return new ActionResult<>(EnumActionResult.FAIL, stack);
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer && !worldIn.isRemote) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_WITCH_DRINK, SoundCategory.PLAYERS, 1.0F, 1.0F);
            if (!player.isCreative()) stack.shrink(1);
            if (Loader.isModLoaded("better_diving") && ConfigHandler.betterDiving.allowBetterDivingIntegration) {
                BetterDiving.setCurrentAir(player, Math.min(BetterDiving.getCurrentAir(player) + BetterDiving.getMaxAir(player) * ConfigHandler.betterDiving.betterDivingAirPercentage / 100, BetterDiving.getMaxAir(player)));
            } else {
                player.setAir(Math.min(player.getAir() + 30 * ConfigHandler.restoredAirBubbles, 300));
            }
            if (!ConfigHandler.usesBottleUp) {
                if (stack.isEmpty()) {
                    return new ItemStack(ModItems.EMPTY_AIR_BOTTLE);
                } else {

                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(ModItems.EMPTY_AIR_BOTTLE));
                }
            }


        }

        return stack;
    }

}


