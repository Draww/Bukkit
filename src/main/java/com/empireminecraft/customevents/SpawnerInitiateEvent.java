/*
 * Copyright (c) 2016 Starlis LLC / Daniel Ennis (Aikar) - MIT License
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

package com.empireminecraft.customevents;

import org.bukkit.NamespacedKey;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nullable;

public class SpawnerInitiateEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private final Location loc;
    private final World world;
    private final HumanEntity entity;
    private NamespacedKey mob;

    public SpawnerInitiateEvent(NamespacedKey mob, World world, Location loc, HumanEntity entity) {
        this.world = world;
        this.loc = loc;
        this.mob = mob;
        this.entity = entity;
    }

    public World getWorld() {
        return this.world;
    }

    @Nullable
    public EntityType getMobType() {
        return mob != null ? EntityType.fromName(mob.getKey()) : null;
    }

    public void setMobType(NamespacedKey key) {
        this.mob = key;
    }

    public HumanEntity getTriggeringPlayer() {
        return this.entity;
    }

    public Location getSpawnerLocation() {
        return this.loc;
    }

    public boolean isCancelled() {
        return canceled;
    }

    public void setCancelled(boolean cancel) {
        canceled = cancel;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
