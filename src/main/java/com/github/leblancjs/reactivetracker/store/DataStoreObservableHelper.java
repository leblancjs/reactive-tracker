package com.github.leblancjs.reactivetracker.store;

import com.github.leblancjs.reactivetracker.store.event.DataAddedEvent;
import com.github.leblancjs.reactivetracker.store.event.DataRemovedEvent;
import com.github.leblancjs.reactivetracker.store.event.DataStoreEvent;
import com.github.leblancjs.reactivetracker.store.event.DataUpdatedEvent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * A <code>DataStoreObservableHelper</code> is a convenient {@link DataStoreObservable} implementation that manages
 * observer registration and provides utility methods to notify observers of data store events. It simplifies the data
 * store implementation, since it does not need to know how to create the various events. The helper's utility methods
 * take care of it.
 * <br>
 * <br>
 * <b>Example Usage</b>
 * <pre>
 * public class MyDataStore implements DataStore<MyData>, DataStoreObservable<MyData> {
 *     private final DataStoreObservableHelper observableHelper = new DataStoreObservableHelper<>();
 *
 *     public void doSomething() {
 *         observableHelper.notifyDataAdded(...);
 *         observableHelper.notifyDataRemoved(...);
 *         observableHelper.notifyDataUpdated(...);
 *     }
 *
 *     {@literal @}Override
 *     public void addDataStoreObserver(final DataStoreObserver<MyData> observer) {
 *         observableHelper.addDataStoreObserver(observer);
 *     }
 *
 *     {@literal @}Override
 *     public void removeDataStoreObserver(final DataStoreObserver<MyData> observer) {
 *        observableHelper.removeDataStoreObserver(observer);
 *    }
 * }
 * </pre>
 *
 * @param <Data> the type of data that is stored
 */
public class DataStoreObservableHelper<Data> implements DataStoreObservable<Data> {
    private final AtomicReference<Set<DataStoreObserver<Data>>> observers = new AtomicReference<>(Collections.emptySet());
    
    @Override
    public void addDataStoreObserver(final DataStoreObserver<Data> observer) {
        var modifiableObservers = new HashSet<>(observers.get());
        modifiableObservers.add(observer);
        observers.set(Collections.unmodifiableSet(modifiableObservers));
    }

    @Override
    public void removeDataStoreObserver(final DataStoreObserver<Data> observer) {
        var filteredObservers = observers.get().stream()
                .filter(o -> o.equals(observer))
                .collect(Collectors.toUnmodifiableSet());
        observers.set(filteredObservers);
    }

    /**
     * Notifies observers that data was added to the store.
     *
     * @param data the data that was added
     */
    public void notifyDataAdded(final Data data) {
        notifyDataStoreChanged(new DataAddedEvent<>(data));
    }

    /**
     * Notifies observers that data was removed from the store.
     *
     * @param data the data that was removed
     */
    public void notifyDataRemoved(final Data data) {
        notifyDataStoreChanged(new DataRemovedEvent<>(data));
    }

    /**
     * Notifies observers that data was changed in the store.
     *
     * @param newData the new, updated data
     * @param oldData the old data that was replaced
     */
    public void notifyDataChanged(final Data newData, final Data oldData) {
        notifyDataStoreChanged(new DataUpdatedEvent<>(newData, oldData));
    }

    private void notifyDataStoreChanged(final DataStoreEvent<Data> event) {
        observers.get().forEach(observer -> observer.notifyDataStoreChanged(event));
    }
}
