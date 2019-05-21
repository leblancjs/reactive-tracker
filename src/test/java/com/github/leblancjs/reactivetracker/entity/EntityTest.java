package com.github.leblancjs.reactivetracker.entity;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EntityTest {
    private static final String UNIQUE_IDENTIFIER = UUID.randomUUID().toString();

    @Test
    public void testDefaultConstructorWillGenerateUniqueIdentifier() {
        final Entity entity = new Entity() {
        };

        assertNotEquals("", entity.getId());
    }

    @Test
    public void testDefaultConstructorWillGenerateRandomUniqueIdentifier() {
        final Entity firstEntity = new Entity() {
        };
        final Entity secondEntity = new Entity() {
        };

        assertNotEquals(firstEntity.getId(), secondEntity.getId());
    }

    @Test
    public void testConstructorWillSetUniqueIdentifier() {
        final Entity entity = new Entity(UNIQUE_IDENTIFIER) {
        };

        assertEquals(UNIQUE_IDENTIFIER, entity.getId());
    }
}
