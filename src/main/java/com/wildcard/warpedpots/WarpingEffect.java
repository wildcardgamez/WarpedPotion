package com.wildcard.warpedpots;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.HashMap;

public class WarpingEffect extends Effect {
    public WarpingEffect() {
        super(EffectType.NEUTRAL, 1027700);
    }

    @Override
    public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
        WarpSavedData.get((ServerWorld) entityLivingBaseIn.world).addWarp(entityLivingBaseIn);
        super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
        BlockPos pos = WarpSavedData.get((ServerWorld) entityLivingBaseIn.world).getWarp(entityLivingBaseIn);
        if(pos != null)
            entityLivingBaseIn.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
        super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }
}
