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

import com.destroystokyo.paper.entity.RangedEntity;
import org.bukkit.entity.Mob;
import com.empireminecraft.api.EntityTask.TaskHandler;
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
import org.bukkit.entity.Wolf;

import java.util.List;

public interface EAPI_Entity {

    byte[] serializeEntity(Entity craftentity);
    Entity deserializeEntity(byte[] data, World world);

    void cancelTasks(Entity entity);

    default <T extends Entity> EntityTask<T> scheduleTask(T entity, int interval, Runnable task) {
        return scheduleTask(entity, interval, new EntityTask<T>() {
            @Override
            public void run(T entity) {
                task.run();
            }
        });
    }

    default <T extends Entity> EntityTask<T> scheduleDelayedTask(T entity, int delay, Runnable task) {
        return scheduleTask(entity, delay, new EntityTask<T>(1) {
            @Override
            public void run(T entity) {
                task.run();
            }
        });
    }

    default <T extends Entity> EntityTask<T> scheduleDelayedTask(T entity, int delay, TaskHandler<T> task) {
        return scheduleTask(entity, delay, new EntityTask<T>(1) {
            @Override
            public void run(T entity) {
                task.run(entity, this);
            }
        });
    }

    default <T extends Entity> EntityTask<T> scheduleTask(T entity, int interval, TaskHandler<T> task) {
        return scheduleTask(entity, interval, new EntityTask<T>() {
            @Override
            public void run(T entity) {
                task.run(entity, this);
            }
        });
    }

    default <T extends Entity> EntityTask<T> scheduleTask(T entity, int interval, int limit, TaskHandler<T> task) {
        return scheduleTask(entity, interval, new EntityTask<T>(limit) {
            @Override
            public void run(T entity) {
                task.run(entity, this);
            }
        });
    }

    default <T extends Entity> EntityTask<T> scheduleTask(T entity, int interval, int limit, Runnable task) {
        return scheduleTask(entity, interval, new EntityTask<T>(limit) {
            @Override
            public void run(T entity) {
                task.run();
            }
        });
    }

    <T extends Entity> EntityTask<T> scheduleTask(T entity, int interval, EntityTask<T> task);

    boolean isEntityDisabled(org.bukkit.entity.Entity entity);

    void setItemCanDespawn(Item item, boolean canDespawn);

    /**
     * Disables Ticking on an Entity
     *
     * @param entity
     * @param disabled
     */
    void setDisabledEntity(org.bukkit.entity.Entity entity, boolean disabled);

    int getLove(Animals animal);

    void setLove(Animals animal, int love);

    void setFireProof(Mob entity, boolean flag);

    void setFirePanicProof(Mob entity, boolean flag);

    void makeAggressive(Creature creature, float range);

    void makeAggressiveDuringDay(Spider spider, boolean attack);

    boolean isAggressive(Creature creature);

    void makePeaceful(Mob entity);

    void addGoalByName(Mob entity, String goalName, boolean isTargetGoal, int tickRate, Class<?>[] argClasses, Object... args);

    void removeGoalByName(Mob entity, String goalName);

    void setEntitySize(org.bukkit.entity.Entity entity, float width, float height);

    void setTargetRange(Mob entity, float range);

    @Deprecated
    default void setArrowAttackRange(RangedEntity monster, float range) {
        setRangedAttackDistance(monster, range);
    }

    @Deprecated
    default void setArrowAttackSpeed(RangedEntity monster, Integer min, Integer max) {
        setRangedAttackSpeed(monster, min, max);
    }

    void setRangedAttackDistance(RangedEntity monster, float range);

    default void setRangedAttackSpeed(RangedEntity monster, int speed) {
        setRangedAttackSpeed(monster, speed, speed);
    }
    void setRangedAttackSpeed(RangedEntity monster, Integer min, Integer max);

    void setAlwaysAngry(Wolf wolf, boolean alwaysAngry);

    void setEntityMaxPathfindingRange(Mob creature, float range);

    void respawnEntity(org.bukkit.entity.Entity entity);

    void setEnderSignalLife(EnderSignal enderSignal, int lifeTime);

    int getEnderSignalLife(EnderSignal enderSignal);

    void setEnderSignalSpeed(EnderSignal enderSignal, double speed);

    void setEnderSignalDestination(EnderSignal enderSignal, Location target);

    boolean hasEntityPath(Mob entity);
    Location getEntityPathDestination(Mob entity);
    Location getEntityPathDestination(Mob entity, Location loc);
    Location getEntityPathDestination(Mob entity, LivingEntity target);
    default boolean setEntityDestination(Mob entity, Location loc) {
        return setEntityDestination(entity, loc, 1);
    }
    default boolean setEntityDestination(Mob entity, LivingEntity target) {
        return setEntityDestination(entity, target, 1);
    }
    boolean setEntityDestination(Mob entity, Location loc, double speed);
    boolean setEntityDestination(Mob entity, LivingEntity target, double speed);
    Integer getEntityPathIndex(Mob entity);
    Location getEntityNextPathLoc(Mob entity);
    List<Location> getEntityPathPoints(Mob entity);
}
