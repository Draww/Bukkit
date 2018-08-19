package org.bukkit.inventory.meta;

import org.bukkit.entity.EntityType;

/**
 * Represents a spawn egg and it's spawned type.
 */
public interface SpawnEggMeta extends ItemMeta {

    // Paper start

    /**
     * Copies the Entity as is into the spawn egg.
     *
     * All non positional data properties will be saved
 * @param entity
     */
    default void setSpawnedEntity(org.bukkit.entity.Entity entity) {
        setSpawnedEntity(entity, null);
    }
    /**
     * Copies the Entity into the spawn egg, giving you
     * control over which NBT Tags are to be saved.
     *
     * Note that while Mojang has made data properties more
     * of a user facing detail, there is no guarantee that
     * they will not change names between versions.
     *
     * If a key is filtered out, it will assume the default
     * value upon spawn as a normal spawned entity of this type
     * might receive. If one key is fitlered that has a strong
     * relationship to another key, such as Villager Professions
     * and Careers, you should filter them both/all to avoid buggy
     * behavior in spawning the entity.
     *
     * Filtering by values is not supported.
     *
     * Certain keys such as "UUIDLeast", "UUIDMost", "Pos",
     * "PortalCooldown", "Dimension"
     *
     * are default filtered.
     *
     * @param entity
     * @param keyFilter
     */
    void setSpawnedEntity(org.bukkit.entity.Entity entity, java.util.function.Predicate<String> keyFilter);
    // Paper end

    /**
     * Get the type of entity this egg will spawn.
     *
     * @return The entity type. May be null for implementation specific default.
     * @deprecated different types are different items
     */
    @Deprecated
    EntityType getSpawnedType();

    /**
     * Set the type of entity this egg will spawn.
     *
     * @param type The entity type. May be null for implementation specific
     * default.
     * @deprecated different types are different items
     */
    @Deprecated
    void setSpawnedType(EntityType type);

    @Override
    SpawnEggMeta clone();
}
