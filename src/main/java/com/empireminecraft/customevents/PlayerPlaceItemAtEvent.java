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

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class PlayerPlaceItemAtEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private final ItemStack item;
    private final Location targetLoc;
    private final BlockFace clickFace;
    private final Vector hitVector;
    private final EquipmentSlot hand;
    private ItemStack tempItem;
    private boolean consume = true;


    public PlayerPlaceItemAtEvent(Player player, ItemStack item, Location targetLoc, BlockFace clickFace, Vector hitVector, EquipmentSlot hand) {
        super(player);
        this.item = item;
        this.targetLoc = targetLoc;
        this.clickFace = clickFace;
        this.hitVector = hitVector;
        this.hand = hand;
    }

    public ItemStack getItem() {
        return item;
    }

    public EquipmentSlot getHand() {
        return hand;
    }

    public Location getTargetLoc() {
        return targetLoc;
    }

    public BlockFace getClickFace() {
        return clickFace;
    }

    public Vector getHitVector() {
        return hitVector;
    }

    public ItemStack getTempItem() {
        return this.tempItem;
    }

    public void setTempItem(ItemStack item) {
        if (item == null) {
            canceled = true;
        }
        this.tempItem = item;
    }

    public boolean getConsumeItem() {
        return this.consume;
    }

    public void setConsumeItem(boolean consume) {
        this.consume = consume;
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
