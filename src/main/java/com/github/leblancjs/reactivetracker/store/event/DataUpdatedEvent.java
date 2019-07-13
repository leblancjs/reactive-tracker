package com.github.leblancjs.reactivetracker.store.event;

import java.util.Objects;

public class DataUpdatedEvent<Data> extends DataStoreEvent<Data> {
    private final Data oldData;

    public DataUpdatedEvent(final Data newData, final Data oldData) {
        super(newData);

        this.oldData = Objects.requireNonNull(oldData);
    }

    @Override
    public String toString() {
        return "DataStoreEvent{" +
                "type=" + getType() +
                ", newData=" + getData() +
                ", oldData=" + oldData +
                '}';
    }

    @Override
    public Type getType() {
        return Type.DATA_UPDATED;
    }

    public Data getNewData() {
        return getData();
    }

    public Data getOldData() {
        return oldData;
    }
}
