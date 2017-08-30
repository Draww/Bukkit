package com.destroystokyo.paper;

import com.google.common.collect.Lists;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public class ParticleBuilder {
    private Particle particle;
    private List<Player> receivers;
    private Player source;
    private Location location;
    private int count = 1;
    private double offsetX = 0, offsetY = 0, offsetZ = 0;
    private double extra = 1;
    private Object data;

    public ParticleBuilder(Particle particle) {
        this.particle = particle;
    }

    public ParticleBuilder spawn() {
        if (this.location == null) {
            throw new IllegalStateException("Please specify location for this particle");
        }
        location.getWorld().spawnParticle(particle, receivers, source,
            location.getX(), location.getY(), location.getZ(),
            count, offsetX, offsetY, offsetZ, extra, data
        );
        return this;
    }

    public Particle particle() {
        return particle;
    }
    public ParticleBuilder particle(Particle particle) {
        this.particle = particle;
        return this;
    }

    public List<Player> receivers() {
        return receivers;
    }

    public ParticleBuilder receivers(List<Player> receivers) {
        this.receivers = Lists.newArrayList(receivers);
        return this;
    }

    public ParticleBuilder receivers(Player... receivers) {
        this.receivers = Lists.newArrayList(receivers);
        return this;
    }

    public Player source() {
        return source;
    }

    public ParticleBuilder source(Player source) {
        this.source = source;
        return this;
    }

    public Location location() {
        return location;
    }

    public ParticleBuilder location(Location location) {
        this.location = location.clone();
        return this;
    }

    public ParticleBuilder location(World world, double x, double y, double z) {
        this.location = new Location(world, x, y, z);
        return this;
    }

    public int count() {
        return count;
    }

    public ParticleBuilder count(int count) {
        this.count = count;
        return this;
    }

    public double offsetX() {
        return offsetX;
    }
    public double offsetY() {
        return offsetY;
    }
    public double offsetZ() {
        return offsetZ;
    }

    public ParticleBuilder offset(double offsetX, double offsetY, double offsetZ) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        return this;
    }

    public double extra() {
        return extra;
    }

    public ParticleBuilder extra(double extra) {
        this.extra = extra;
        return this;
    }

    public <T> T data() {
        return (T) data;
    }

    public <T> ParticleBuilder data(T data) {
        this.data = data;
        return this;
    }

    public ParticleBuilder color(Color color) {
        return color(color.getRed(), color.getGreen(), color.getBlue());
    }

    public ParticleBuilder color(int r, int g, int b) {
        if (particle != Particle.REDSTONE && particle != Particle.SPELL_MOB && particle != Particle.SPELL_MOB_AMBIENT) {
            throw new IllegalStateException("Color may only be set on REDSTONE, SPELL_MOB, or SPELL_MOB_AMBIENT");
        }
        offsetX = convertColorValue(r);
        offsetY = convertColorValue(g);
        offsetZ = convertColorValue(b);
        return this;
    }

    private static double convertColorValue(double value) {
        if (value <= 0.0D) {
            value = -1.0D;
        }

        return value / 255.0D;
    }
}
