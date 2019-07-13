package com.github.leblancjs.reactivetracker.store.event;

import java.util.Objects;

public abstract class Event<Data> {
    enum Type {
        DATA_ADDED,
        DATA_REMOVED,
        DATA_UPDATED
    }

    private final Data data;

    public Event(final Data data) {
        this.data = Objects.requireNonNull(data);
    }

    @Override
    public String toString() {
        return "Event{" +
                "type=" + getType() +
                ", data=" + data +
                '}';
    }

    public abstract Type getType();

    public Data getData() {
        return data;
    }
}
