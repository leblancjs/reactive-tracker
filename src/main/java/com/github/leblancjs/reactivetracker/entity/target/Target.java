package com.github.leblancjs.reactivetracker.entity.target;

import com.github.leblancjs.reactivetracker.entity.coordinates.Coordinates;
import com.github.leblancjs.reactivetracker.entity.Entity;

public class Target extends Entity {
    private final String name;
    private final Coordinates coordinates;

    public Target(final String initialName, final Coordinates initialCoordinates) {
        this.name = initialName;
        this.coordinates = initialCoordinates;
    }

    public Target(final String id, final String initialName, final Coordinates initialCoordinates) {
        super(id);

        this.name = initialName;
        this.coordinates = initialCoordinates;
    }

    public Target rename(final String newName) {
        return new Target(getId(), newName, getCoordinates());
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
