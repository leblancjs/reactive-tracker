package com.github.leblancjs.reactivetracker.store.event;

public class DataRemovedEvent<Data> extends DataStoreEvent<Data> {
    public DataRemovedEvent(final Data data) {
        super(data);
    }

    @Override
    public Type getType() {
        return Type.DATA_REMOVED;
    }
}
