package com.github.leblancjs.reactivetracker.entity;

import java.util.UUID;

public abstract class Entity {
    private final String id;

    public Entity() {
        this.id = UUID.randomUUID().toString();
    }

    public Entity(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
