package com.wildcard.warpedpots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class WarpedPearlItem extends EnderPearlItem {
    public WarpedPearlItem() {
        super(new Item.Properties().group(ItemGroup.BREWING).maxStackSize(16));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.addPotionEffect(new EffectInstance(RegistryHandler.WARPING.get(), 150));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
