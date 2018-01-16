package com.destroystokyo.paper.profile;

import com.mojang.authlib.GameProfile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a players profile for the game, such as UUID, Name, and textures.
 */
public interface PlayerProfile {

    /**
     * @return The players name, if set
     */
    @Nullable String getName();

    /**
     * @return The players unique identifier, if set
     */
    @Nullable UUID getId();

    /**
     * @return A Mutable set of this players properties, such as textures.
     * Values specified here are subject to implementation details.
     */
    @Nonnull Set<ProfileProperty> getProperties();

    /**
     * Sets a property. If the property already exists, the previous one will be replaced
     * @param property Property to set.
     */
    void setProperty(ProfileProperty property);

    /**
     * Sets multiple properties. If any of the set properties already exist, it will be replaced
     * @param properties The properties to set
     */
    void setProperties(Collection<ProfileProperty> properties);

    /**
     * Removes a specific property from this profile
     * @param property The property to remove
     * @return If a property was removed
     */
    boolean removeProperty(String property);

    /**
     * Removes a specific property from this profile
     * @param property The property to remove
     * @return If a property was removed
     */
    default boolean removeProperty(@Nonnull ProfileProperty property) {
        return removeProperty(property.getName());
    }

    /**
     * Removes all properties in the collection
     * @param properties The properties to remove
     * @return If any property was removed
     */
    default boolean removeProperties(Collection<ProfileProperty> properties) {
        boolean removed = false;
        for (ProfileProperty property : properties) {
            if (removeProperty(property)) {
                removed = true;
            }
        }
        return removed;
    }

    /**
     * Clears all properties on this profile
     */
    void clearProperties();

    /**
     * @return Does this profile have Name, UUID and Textures filled in
     */
    boolean isComplete();

    /**
     * @deprecated Will be removed in 1.13
     */
    @Deprecated
    GameProfile getGameProfile();
}
