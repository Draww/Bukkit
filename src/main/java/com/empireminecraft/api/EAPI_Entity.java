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

package com.empireminecraft.api;

import com.empireminecraft.api.attributes.Attribute;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Animals;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EnderSignal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface EAPI_Entity {

    byte[] serializeEntity(Entity craftentity);
    Entity deserializeEntity(byte[] data, World world);

    void cancelTasks(Entity entity);
    EntityTask scheduleTask(Entity entity, int interval, Runnable task);
    EntityTask scheduleTask(Entity entity, int interval, EntityTask task);

    boolean isEntityDisabled(org.bukkit.entity.Entity entity);
    void setItemCanDespawn(Item item, boolean canDespawn);

    /**
     * Disables Ticking on an Entity
     * @param entity
     * @param disabled
     */
    void setDisabledEntity(org.bukkit.entity.Entity entity, boolean disabled);
    int getLove(Animals animal);
    void setLove(Animals animal, int love);

    void setFireProof(Creature creature, boolean flag);
    void setFirePanicProof(Creature creature, boolean flag);
    void makeAggressive(Creature creature, float range);
    void makeAggressiveDuringDay(Spider spider, boolean attack);

    void makePeaceful(Creature creature);

    void addGoalByName(LivingEntity creature, String goalName, boolean isTargetGoal, int tickRate, Class<?>[] argClasses, Object... args);
    void removeGoalByName(LivingEntity creature, String goalName);

    void setEntitySize(org.bukkit.entity.Entity entity, float width, float height);

    void setTargetRange(LivingEntity entity, float range);

    void setArrowAttackRange(Monster monster, float range);
    void setArrowAttackSpeed(Monster monster, Integer min, Integer max);

    void setEntityMaxPathfindingRange(Creature creature, float range);

    int getDisabledSlots(ArmorStand armorStand);
    void setDisabledSlots(ArmorStand armorStand, int i);

    void respawnEntity(org.bukkit.entity.Entity entity);

    void setEnderSignalLife(EnderSignal enderSignal, int lifeTime);
    int getEnderSignalLife(EnderSignal enderSignal);

    void setEnderSignalDestination(EnderSignal enderSignal, Location target);

    boolean hasEntityPath(Creature entity);

    Location getEntityPathDestination(Creature entity);
    boolean setEntityDestination(Creature entity, Location loc);
    boolean setEntityDestination(Creature entity, LivingEntity target);
}
