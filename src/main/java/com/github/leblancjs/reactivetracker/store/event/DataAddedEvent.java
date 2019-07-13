package com.github.leblancjs.reactivetracker.store.event;

public class DataAddedEvent<Data> extends DataStoreEvent<Data> {
    public DataAddedEvent(final Data data) {
        super(data);
    }

    @Override
    public Type getType() {
        return Type.DATA_ADDED;
    }
}
