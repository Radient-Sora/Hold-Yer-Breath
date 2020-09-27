package sora.holdyerbreath.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.UseAction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import sora.holdyerbreath.init.ConfigHandler;
import sora.holdyerbreath.init.ModItems;

public class ItemEmptyAirBottle extends BaseItem {



  public ItemEmptyAirBottle(){
    super("empty_air_bottle");
  }

  @Override
  public int getItemStackLimit(ItemStack stack) {
    return ConfigHandler.general.maxStackSize.get();
  }

  @Override
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.BOW;
  }

  @Override
  public int getUseDuration(ItemStack stack) {
    return 20;
  }

  @Override
  public ActionResultType onItemUse(ItemUseContext context) {
    return super.onItemUse(context);
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
    ItemStack stack = playerIn.getHeldItem(handIn);
    if(!worldIn.isRemote() && !playerIn.areEyesInFluid(FluidTags.WATER)){
      playerIn.setActiveHand(handIn);
    }
    return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
  }

  @Override
  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
    if(entityLiving instanceof PlayerEntity){
      PlayerEntity player = (PlayerEntity) entityLiving;
      worldIn.playSound(player,player.getPosX(),player.getPosY(),player.getPosZ(),SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 1.0F, 1.0F);
      if(!player.isCreative()) stack.shrink(1);
      if(stack.isEmpty()){
        return new ItemStack(ModItems.AIR_BOTTLE);
      } else {
        ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(ModItems.AIR_BOTTLE));
      }
    }
    return stack;
  }

}
