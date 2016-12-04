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

package com.destroystokyo.paper.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;

/**
 * Different from ProjectileHitEvent in that it will let you cancel if the Arrow will
 * "trigger" the block, such as TNT.
 *
 */
public class ArrowHitBlockEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;

    private final Block block;

    public ArrowHitBlockEvent(Arrow entity, Block block) {
        super(entity);
        this.block = block;
    }

    /**
     * @return The Arrow that hit the block
     */
    public Arrow getArrow() {
        return (Arrow) entity;
    }

    /**
     * @return The Arrow that hit the block
     */
    public Arrow getEntity() {
        return (Arrow) entity;
    }

    /**
     * @return The block hit by the arrow that may trigger behavior
     */
    public Block getBlock() {
        return block;
    }

    /**
     * @return If the arrow activating a block should be cancelled
     */
    public boolean isCancelled() {
        return canceled;
    }

    /**
     * Whether or not to cancel any behavior that would occur from the arrow hitting the block
     * @param cancel true if you wish to cancel this event
     */
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
