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
import net.minecraftforge.items.ItemHandlerHelper;
import sora.holdyerbreath.HoldYerBreath;
import sora.holdyerbreath.init.ConfigHandler;
import sora.holdyerbreath.init.ModItems;

public class ItemEmptyAirBottle extends Item {
    public ItemEmptyAirBottle(String name) {
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
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 20;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote && !playerIn.isInsideOfMaterial(Material.WATER)) {
            playerIn.setActiveHand(handIn);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 1.0F, 1.0F);
            if (!player.isCreative()) stack.shrink(1);
            if (stack.isEmpty()) {
                return new ItemStack(ModItems.AIR_BOTTLE);
            } else {
                ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(ModItems.AIR_BOTTLE));
            }
        }
        return stack;
    }
}
