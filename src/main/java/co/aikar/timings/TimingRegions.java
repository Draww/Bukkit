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

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;

import javax.annotation.Nonnull;
import java.util.UUID;

public final class TimingRegions {
    static final Table<UUID, TimingRegionId, TimingRegion> REGIONS = HashBasedTable.create();

    private TimingRegions() {
    }

    public static void calculateCounts() {
        for (TimingRegion region : REGIONS.values()) {
            region.resetCounts();
        }
        Bukkit.getWorlds().forEach(world -> {
            for (Chunk chunk : world.getLoadedChunks()) {
                getRegion(chunk).tickChunk(chunk);
            }
        });
    }

    @Nonnull public static TimingRegion getRegion(Chunk chunk) {
        TimingRegionId regionId = new TimingRegionId(chunk);
        UUID worldId = chunk.getWorld().getUID();
        TimingRegion region = REGIONS.get(worldId, regionId);
        if (region == null) {
            region = new TimingRegion(regionId);
            REGIONS.put(worldId, regionId, region);
        }
        return region;
    }
}
