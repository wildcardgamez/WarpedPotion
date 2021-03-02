package com.wildcard.warpedpots;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import java.util.*;

public class WarpSavedData extends WorldSavedData {
    private static final HashMap<UUID, WarpPos> WARP_MAP = new HashMap<UUID, WarpPos>();

    public WarpSavedData() {
        super(WarpedPotions.MOD_ID + "_WarpData");
    }

    public static WarpSavedData get(ServerWorld world) {
        return world.getSavedData().getOrCreate(WarpSavedData::new, WarpedPotions.MOD_ID + "_WarpData");
    }

    public void addWarp(LivingEntity entity) {
        WARP_MAP.put(entity.getUniqueID(), new WarpPos(new BlockPos(entity.getPosX(), entity.getPosY(), entity.getPosZ()), entity.getEntityWorld().getDimensionKey().toString()));
        markDirty();
    }

    public BlockPos getWarp(LivingEntity entity) {
        if (WARP_MAP.containsKey(entity.getUniqueID())) {
            markDirty();
            if (entity.getEntityWorld().getDimensionKey().toString().equals(WARP_MAP.get(entity.getUniqueID()).getWorld()))
                return WARP_MAP.remove(entity.getUniqueID()).pos;
            WARP_MAP.remove(entity.getUniqueID());
        }
        return null;
    }

    @Override
    public void read(CompoundNBT nbt) {
        ListNBT list = nbt.getList("warp_map", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundNBT compound = list.getCompound(i);
            WarpPos pos = new WarpPos(new BlockPos(compound.getInt("x"), compound.getInt("y"), compound.getInt("z")), compound.getString("world"));
            WARP_MAP.put(UUID.fromString(compound.getString("entity")), pos);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ListNBT list = new ListNBT();
        for (Map.Entry<UUID, WarpPos> i: WARP_MAP.entrySet()) {
            if(i.getKey() != null) {
                CompoundNBT nbt = new CompoundNBT();
                nbt.putString("entity", i.getKey().toString());
                nbt.putString("world", i.getValue().getWorld());
                nbt.putDouble("x", i.getValue().getPos().getX());
                nbt.putDouble("y", i.getValue().getPos().getY());
                nbt.putDouble("z", i.getValue().getPos().getZ());
                list.add(nbt);
            }
        }
        compound.put("warp_map", list);
        return compound;
    }

    private static class WarpPos {
        public WarpPos(BlockPos loc, String dim) {
            pos = loc;
            world = dim;
        }
        
        private final BlockPos pos;
        private final String world;
        
        public BlockPos getPos() {
            return pos;
        }
        public String getWorld() {
            return world;
        }
    }
}
