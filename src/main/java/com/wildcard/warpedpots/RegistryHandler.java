package com.wildcard.warpedpots;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WarpedPotions.MOD_ID);
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, WarpedPotions.MOD_ID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, WarpedPotions.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Item> WARPED_PEARL = ITEMS.register("warped_pearl", WarpedPearlItem::new);
    public static final RegistryObject<Item> WARPED_FRUIT = ITEMS.register("warped_fruit", WarpedFruitItem::new);

    public static final RegistryObject<Effect> WARPING = EFFECTS.register("warping", WarpingEffect::new);

    public static final RegistryObject<Potion> WARPING_NORMAL = POTIONS.register("warping", () -> new Potion(new EffectInstance(WARPING.get(), 1200)));
    public static final RegistryObject<Potion> WARPING_LONG = POTIONS.register("warping_long", () -> new Potion(new EffectInstance(WARPING.get(), 18000)));
    public static final RegistryObject<Potion> WARPING_LONGER = POTIONS.register("warping_longer", () -> new Potion(new EffectInstance(WARPING.get(), 72000)));
    public static final RegistryObject<Potion> WARPING_LONGEST = POTIONS.register("warping_longest", () -> new Potion(new EffectInstance(WARPING.get(), 180000)));

    public static void brewingSetup() {
        PotionBrewing.addMix(Potions.AWKWARD, WARPED_PEARL.get(), WARPING_NORMAL.get());
        PotionBrewing.addMix(Potions.AWKWARD, WARPED_FRUIT.get(), WARPING_NORMAL.get());
        PotionBrewing.addMix(WARPING_NORMAL.get(), Items.REDSTONE, WARPING_LONG.get());
        PotionBrewing.addMix(WARPING_LONG.get(), Items.REDSTONE_BLOCK, WARPING_LONGER.get());
        PotionBrewing.addMix(WARPING_LONGER.get(), Items.REDSTONE_LAMP, WARPING_LONGEST.get());
    }
}
