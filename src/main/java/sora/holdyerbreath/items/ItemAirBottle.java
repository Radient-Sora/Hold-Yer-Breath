package sora.holdyerbreath.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import sora.holdyerbreath.init.ConfigHandler;
import sora.holdyerbreath.init.ModItems;

public class ItemAirBottle extends BaseItem {

  public ItemAirBottle() {
    super("air_bottle");
  }

  @Override
  public int getItemStackLimit(ItemStack stack) {
    return ConfigHandler.general.maxStackSize.get();
  }

  @Override
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.DRINK;
  }

  @Override
  public int getUseDuration(ItemStack stack) {
    return 16;
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
    ItemStack stack = playerIn.getHeldItem(handIn);
    if(playerIn.areEyesInFluid(FluidTags.WATER)){
      if(!worldIn.isRemote && !stack.isEmpty()){
        playerIn.setActiveHand(handIn);
      }
      return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
    } else {
      return  new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
    }

  }

  @Override
  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
    if(entityLiving instanceof  PlayerEntity && !worldIn.isRemote){}
    PlayerEntity player = (PlayerEntity) entityLiving;
    worldIn.playSound(player,player.getPosX(), player.getPosY(), player.getPosZ(),SoundEvents.ENTITY_WITCH_DRINK, SoundCategory.PLAYERS,1.0F, 1.0F);
    if(!player.isCreative()) stack.shrink(1);
    player.setAir(Math.min(player.getAir() + 30 * ConfigHandler.general.restoredAirBubbles.get(), 300));
    if(!ConfigHandler.general.usesBottleUp.get()){
      if(stack.isEmpty()){
        return new ItemStack(ModItems.EMPTY_AIR_BOTTLE);
      } else {
        ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(ModItems.EMPTY_AIR_BOTTLE));
      }
    }
    return stack;
  }

}


