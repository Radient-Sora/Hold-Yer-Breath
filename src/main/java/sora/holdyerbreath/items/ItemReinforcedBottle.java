package sora.holdyerbreath.items;

import com.google.common.collect.Lists;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import sora.holdyerbreath.HoldYerBreath;
import sora.holdyerbreath.init.ConfigHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemReinforcedBottle extends Item {
    private static final String FLUID = "fluid";

    private final int uses;
    private final int regenerateAmount;

    public ItemReinforcedBottle(
            String name,
            int uses,
            int regenerateAmount
    ) {
        super();

        this.setRegistryName(new ResourceLocation(HoldYerBreath.MOD_ID, name));
        this.setUnlocalizedName(name);
        this.setCreativeTab(HoldYerBreath.CREATIVE_TAB);

        this.addPropertyOverride(
                new ResourceLocation(HoldYerBreath.MOD_ID, FLUID),
                (stack, worldIn, entityIn) -> {
                    NBTTagCompound compound = stack.getSubCompound(HoldYerBreath.MOD_ID);

                    if (compound == null) {
                        return 0.0F;
                    } else {
                        return compound.getFloat(FLUID) > 0f ? 1f : 0f;
                    }
                }
        );

        this.uses = uses;
        this.regenerateAmount = regenerateAmount;
    }

    @SuppressWarnings("SameParameterValue")
    private static float getOrSetFluid(
            ItemStack itemStack,
            float amount
    ) {
        NBTTagCompound compound = itemStack.getOrCreateSubCompound(HoldYerBreath.MOD_ID);
        if (!compound.hasKey(FLUID)) {
            compound.setFloat(FLUID, amount);
        }
        return compound.getFloat(FLUID);
    }

    private static void setFluid(
            ItemStack itemStack,
            float amount
    ) {
        itemStack.getOrCreateSubCompound(HoldYerBreath.MOD_ID).setFloat(FLUID, amount);
    }

    private static float getFluid(
            ItemStack itemStack
    ) {
        return itemStack.getOrCreateSubCompound(HoldYerBreath.MOD_ID).getFloat(FLUID);
    }

    private static boolean isEmpty(
            ItemStack itemStack
    ) {
        return getOrSetFluid(itemStack, 0f) <= 0f;
    }

    private static boolean isLessThanFull(
            ItemStack itemStack
    ) {
        float amount = getOrSetFluid(itemStack, 0f);
        return amount >= 0f && amount < 1f;
    }

    @Override
    public int getItemStackLimit(
            @Nonnull ItemStack stack
    ) {
        return 1;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean showDurabilityBar(
            @Nonnull ItemStack stack
    ) {
        return true;
    }

    @Override
    public double getDurabilityForDisplay(
            ItemStack stack
    ) {
        if (stack.getSubCompound(HoldYerBreath.MOD_ID) != null) {
            return (this.uses - getFluid(stack)) / this.uses;
        } else {
            return getOrSetFluid(stack, 0f);
        }
    }

    @Nonnull
    @Override
    public EnumAction getItemUseAction(
            @Nonnull ItemStack stack
    ) {
        if (isEmpty(stack)) {
            return EnumAction.BOW;
        } else {
            return EnumAction.DRINK;
        }
    }

    @Override
    public int getMaxItemUseDuration(
            @Nonnull ItemStack stack
    ) {
        if (isEmpty(stack)) {
            return 20 * this.uses;
        } else {
            return 16 * this.uses;
        }
    }

    @Override
    public void addInformation(
            @Nonnull ItemStack stack,
            @Nullable World worldIn,
            List<String> tooltip,
            @Nonnull ITooltipFlag flagIn
    ) {
        tooltip.addAll(Lists.newArrayList(
                String.format("Restores %d per use", ConfigHandler.restoredAirBubbles),
                String.format("Can be used %d times", this.uses),
                String.format("Takes %d ticks to fill, and %d ticks to drink", 20 * this.uses, 16 * this.uses),
                String.format("Air: %.2f", getOrSetFluid(stack, 0f))
        ));
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(
            @Nonnull World worldIn,
            EntityPlayer playerIn,
            @Nonnull EnumHand handIn
    ) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if (isLessThanFull(playerIn.getHeldItem(handIn))) {
            // If it's not full and the player is NOT in water:
            //     right-clicking will set this item to be active
            if (!worldIn.isRemote && !playerIn.isInsideOfMaterial(Material.WATER)) {
                playerIn.setActiveHand(handIn);
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        } else {
            // If it's not full and the player is in water:
            //     right-clicking will set this item to be active
            if (playerIn.isInsideOfMaterial(Material.WATER)) {
                if (!worldIn.isRemote && !stack.isEmpty()) {
                    playerIn.setActiveHand(handIn);
                }
                return new ActionResult<>(EnumActionResult.SUCCESS, stack);
            } else {
                return new ActionResult<>(EnumActionResult.FAIL, stack);
            }
        }
    }

    @Nonnull
    @Override
    public ItemStack onItemUseFinish(
            @Nonnull ItemStack stack,
            @Nonnull World worldIn,
            @Nonnull EntityLivingBase entityLiving
    ) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;

            boolean notEmptyOrFull = isLessThanFull(stack);

            // Play sounds
            if (notEmptyOrFull) {
                worldIn.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 1.0F, 1.0F);
            } else {
                worldIn.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_WITCH_DRINK, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }

            // This should be done on the server, then sent a packet to the client
            if (!player.isInWater()) {
                // They're not in water and the bottle isn't full:
                //     Fill it up
                if (notEmptyOrFull) {
                    setFluid(stack, this.uses);
                }
            } else {
                player.setAir(Math.min(player.getAir() + 30 * this.regenerateAmount, 300));

                if (ConfigHandler.usesBottleUp && !player.isCreative()) {
                    setFluid(stack, Math.max(0, getFluid(stack) - 1));
                    return stack;
                }
            }

            return stack;
        }

        return stack;
    }
}
