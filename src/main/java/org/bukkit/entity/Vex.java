package org.bukkit.entity;

/**
 * Represents a Vex.
 */
public interface Vex extends Monster {
    /**
     * @return What Entity (most likely an Evoker, but not guaranteed) summoned this Vex
     */
    Mob getSummoner(); // Paper

}
