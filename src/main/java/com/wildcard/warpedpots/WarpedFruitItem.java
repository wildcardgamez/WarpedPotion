package com.wildcard.warpedpots;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class WarpedFruitItem extends Item {
    public WarpedFruitItem() {
        super(new Item.Properties().group(ItemGroup.FOOD).food(Foods.CHORUS_FRUIT));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        entityLiving.addPotionEffect(new EffectInstance(RegistryHandler.WARPING.get(), 300));
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
