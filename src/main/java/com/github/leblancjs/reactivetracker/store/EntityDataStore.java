package com.github.leblancjs.reactivetracker.store;

import com.github.leblancjs.reactivetracker.entity.Entity;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * An <code>EntityDataStore</code> is a convenient, thread-safe data store implementation for entities.
 * <p>
 * The entities are persisted using a {@link LinkedHashMap} to make it possible to quickly find an entity using its
 * unique identifier while maintaining the order in which the entities were added.
 *
 * @param <Data> the type of entity that is stored
 */
public class EntityDataStore<Data extends Entity> implements DataStore<Data>, DataStoreObservable<Data> {
    private final AtomicReference<Map<String, Data>> entities = new AtomicReference<>();
    private final DataStoreObservableHelper<Data> observableHelper = new DataStoreObservableHelper<>();

    /**
     * Constructs an empty entity data store.
     */
    public EntityDataStore() {
        entities.set(Collections.emptyMap());
    }

    /**
     * Constructs an entity data store populated with an initial collection of entities.
     *
     * @param initialEntities the initial collection of entities
     */
    public EntityDataStore(final Collection<Data> initialEntities) {
        final Map<String, Data> modifiableEntities = new LinkedHashMap<>();
        initialEntities.forEach(entity -> modifiableEntities.put(entity.getId(), entity));
        entities.set(Collections.unmodifiableMap(modifiableEntities));
    }

    /**
     * Adds an entity to the data store, overwriting any existing entity with the same unique identifier.
     *
     * @param entity the entity to add
     */
    @Override
    public synchronized void add(final Data entity) {
        var modifiableEntities = new LinkedHashMap<>(entities.get());
        modifiableEntities.put(entity.getId(), entity);
        entities.set(Collections.unmodifiableMap(modifiableEntities));
        observableHelper.notifyDataAdded(entity);
    }

    /**
     * Removes an entity from the data store, if it exists. Otherwise, the store remains unchanged.
     *
     * @param entity the entity to remove
     */
    @Override
    public synchronized void remove(final Data entity) {
        var modifiableEntities = new LinkedHashMap<>(entities.get());
        if (modifiableEntities.remove(entity.getId()) != null) {
            entities.set(Collections.unmodifiableMap(modifiableEntities));
            observableHelper.notifyDataRemoved(entity);
        }
    }

    /**
     * Updates an entity in the data store, if it exists. Otherwise, the store remains unchanged.
     *
     * @param entity the entity to update
     */
    @Override
    public synchronized void update(final Data entity) {
        findById(entity.getId()).ifPresent(oldEntity -> {
            var modifiableEntities = new LinkedHashMap<>(entities.get());
            modifiableEntities.put(entity.getId(), entity);
            entities.set(Collections.unmodifiableMap(modifiableEntities));
            observableHelper.notifyDataChanged(entity, oldEntity);
        });
    }

    /**
     * Looks for the entity with the given unique identifier in the data store.
     *
     * @param id the unique identifier of the entity to look for
     * @return the entity with the given unique identifier, if it exists in the data store
     */
    public Optional<Data> findById(final String id) {
        return Optional.ofNullable(entities.get().get(id));
    }

    /**
     * Gets the complete collection of entities in the data store. The entities are ordered according to the order in
     * which they were added to the data store.
     *
     * @return the complete collection of entities in the data store
     */
    public Collection<Data> getAll() {
        return entities.get().values();
    }

    @Override
    public void addDataStoreObserver(final DataStoreObserver<Data> observer) {
        observableHelper.addDataStoreObserver(observer);
    }

    @Override
    public void removeDataStoreObserver(final DataStoreObserver<Data> observer) {
        observableHelper.removeDataStoreObserver(observer);
    }
}
