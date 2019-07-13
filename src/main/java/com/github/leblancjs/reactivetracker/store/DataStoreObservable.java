package com.github.leblancjs.reactivetracker.store;

/**
 * A <code>DataStoreObservable</code> notifies observers of any changes that are made in a data store.
 *
 * @param <Data> the type of data that is stored
 */
public interface DataStoreObservable<Data> {
    /**
     * Registers a new observer of the data store.
     *
     * @param observer the observer to add
     */
    void addDataStoreObserver(DataStoreObserver<Data> observer);

    /**
     * Unregisters an observer of the data store, if it exists.
     *
     * @param observer the observer to remove
     */
    void removeDataStoreObserver(DataStoreObserver<Data> observer);
}
