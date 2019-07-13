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
 * @param <Data> the type of data that is stored
 */
public class DataStoreObservableSupport<Data> implements DataStoreObservable<Data> {
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

    public void notifyDataAdded(final Data data) {
        notifyDataStoreChanged(new DataAddedEvent<>(data));
    }

    public void notifyDataRemoved(final Data data) {
        notifyDataStoreChanged(new DataRemovedEvent<>(data));
    }

    public void notifyDataChanged(final Data newData, final Data oldData) {
        notifyDataStoreChanged(new DataUpdatedEvent<>(newData, oldData));
    }

    private void notifyDataStoreChanged(final DataStoreEvent<Data> event) {
        observers.get().forEach(observer -> observer.notifyDataStoreChanged(event));
    }
}
