package com.github.leblancjs.reactivetracker.store;

/**
 * A DataStore is a container where data can be stored and managed.
 *
 * @param <Data> the type of the data that is stored
 */
public interface DataStore<Data> {
    void add(Data data);

    void remove(Data data);

    void update(Data data);
}
