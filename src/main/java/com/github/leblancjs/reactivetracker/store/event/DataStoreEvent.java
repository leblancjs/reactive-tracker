package com.github.leblancjs.reactivetracker.store.event;

import java.util.Objects;

/**
 * A DataStoreEvent represents a change that was made to the contents of a {@link com.github.leblancjs.reactivetracker.store.DataStore data store}.
 *
 * @param <Data>
 */
public abstract class DataStoreEvent<Data> {
    enum Type {
        DATA_ADDED,
        DATA_REMOVED,
        DATA_UPDATED
    }

    private final Data data;

    public DataStoreEvent(final Data data) {
        this.data = Objects.requireNonNull(data);
    }

    @Override
    public String toString() {
        return "DataStoreEvent{" +
                "type=" + getType() +
                ", data=" + data +
                '}';
    }

    public abstract Type getType();

    public Data getData() {
        return data;
    }
}
