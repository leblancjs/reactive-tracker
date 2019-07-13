package com.github.leblancjs.reactivetracker.store;

import com.github.leblancjs.reactivetracker.store.event.DataStoreEvent;

/**
 * A <code>DataStoreObserver</code> is notified when the data store it observes is changed.
 *
 * @param <Data> the type of data that is stored
 */
public interface DataStoreObserver<Data> {
    void notifyDataStoreChanged(DataStoreEvent<Data> event);
}
