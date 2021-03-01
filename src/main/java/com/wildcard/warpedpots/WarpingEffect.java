package com.wildcard.warpedpots;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.vector.Vector3d;

import java.util.HashMap;

public class WarpingEffect extends Effect {
    private static HashMap<LivingEntity, Vector3d> WARP_MAP = new HashMap<LivingEntity, Vector3d>();
    public WarpingEffect() {
        super(EffectType.NEUTRAL, 1027700);
    }

    @Override
    public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
        WARP_MAP.put(entityLivingBaseIn, new Vector3d(entityLivingBaseIn.getPosX(), entityLivingBaseIn.getPosY(), entityLivingBaseIn.getPosZ()));
        super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
        if(WARP_MAP.containsKey(entityLivingBaseIn)) {
            Vector3d pos = WARP_MAP.get(entityLivingBaseIn);
            entityLivingBaseIn.attemptTeleport(pos.getX(), pos.getY(), pos.getZ(), true);
        }
        WARP_MAP.remove(entityLivingBaseIn);
        super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }
}
