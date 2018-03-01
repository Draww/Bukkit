/*
 * Copyright (c) 2018 Daniel Ennis (Aikar) MIT License
 *
 *  Permission is hereby granted, free of charge, to any person obtaining
 *  a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including
 *  without limitation the rights to use, copy, modify, merge, publish,
 *  distribute, sublicense, and/or sell copies of the Software, and to
 *  permit persons to whom the Software is furnished to do so, subject to
 *  the following conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 *  LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 *  OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 *  WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package co.aikar.timings;

import co.aikar.util.Counter;
import co.aikar.util.LoadingIntMap;
import co.aikar.util.LoadingMap;
import co.aikar.util.MRUMapCache;
import com.google.common.base.Function;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class TimingRegion {
    final TimingRegionId regionId;
    int seen = 0;

    @SuppressWarnings("unchecked")
    final Map<EntityType, Counter> entityCounts = MRUMapCache.of(LoadingMap.of(
        new EnumMap<EntityType, Counter>(EntityType.class), Counter.LOADER
    ));
    @SuppressWarnings("unchecked")
    final Map<Material, Counter> tileEntityCounts = MRUMapCache.of(LoadingMap.of(
        new EnumMap<Material, Counter>(Material.class), Counter.LOADER
    ));

    private final Int2ObjectOpenHashMap<TimingData> timings = new LoadingIntMap<>(TimingData::new);

    TimingRegion(TimingRegionId id) {
        this.regionId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TimingRegion that = (TimingRegion) o;

        return regionId.equals(that.regionId);

    }

    @Override
    public int hashCode() {
        return regionId.hashCode();
    }

    public void add(TimingHandler handler, long diff) {
        this.timings.get(handler.id).add(diff);
    }

    public void reset() {
        seen = 0;
        this.timings.clear();
        this.tileEntityCounts.clear();
        this.entityCounts.clear();
    }

    public void resetCounts() {
        entityCounts.clear();
        tileEntityCounts.clear();
    }

    public void tickChunk(Chunk chunk) {
        seen++;
        for (Entity entity : chunk.getEntities()) {
            if (entity == null) {
                Bukkit.getLogger().warning("Null entity detected in chunk at position x: " + chunk.getX() + ", z: " + chunk.getZ());
                continue;
            }

            entityCounts.get(entity.getType()).increment();
        }

        for (BlockState tileEntity : chunk.getTileEntities()) {
            if (tileEntity == null) {
                Bukkit.getLogger().warning("Null tileentity detected in chunk at position x: " + chunk.getX() + ", z: " + chunk.getZ());
                continue;
            }

            tileEntityCounts.get(tileEntity.getBlock().getType()).increment();
        }

    }
}
