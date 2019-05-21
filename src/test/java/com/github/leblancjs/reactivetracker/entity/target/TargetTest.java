package com.github.leblancjs.reactivetracker.entity.target;

import com.github.leblancjs.reactivetracker.entity.coordinates.Coordinates;
import org.junit.Test;

import static org.junit.Assert.*;

public class TargetTest {
    private static final String UNIQUE_IDENTIFIER = "unique.identifier";
    private static final String INITIAL_NAME = "initial.name";
    private static final Coordinates INITIAL_COORDINATES = new Coordinates(1234d, 4321d);
    private static final String NEW_NAME = "new.name";

    @Test
    public void testDefaultConstructorWillGenerateUniqueIdentifier() {
        final Target target = new Target(INITIAL_NAME, INITIAL_COORDINATES);

        assertNotEquals("", target.getId());
    }

    @Test
    public void testDefaultConstructorWillGenerateRandomUniqueIdentifier() {
        final Target firstTarget = new Target(INITIAL_NAME, INITIAL_COORDINATES);
        final Target secondTarget = new Target(INITIAL_NAME, INITIAL_COORDINATES);

        assertNotEquals(firstTarget.getId(), secondTarget.getId());
    }

    @Test
    public void testDefaultConstructorWillSetInitialName() {
        final Target target = new Target(INITIAL_NAME, INITIAL_COORDINATES);

        assertEquals(INITIAL_NAME, target.getName());
    }

    @Test
    public void testDefaultConstructorWillSetInitialCoordinates() {
        final Target target = new Target(INITIAL_NAME, INITIAL_COORDINATES);

        assertEquals(INITIAL_COORDINATES, target.getCoordinates());
    }

    @Test
    public void testConstructorWillSetUniqueIdentifier() {
        final Target target = new Target(UNIQUE_IDENTIFIER, INITIAL_NAME, INITIAL_COORDINATES);

        assertEquals(UNIQUE_IDENTIFIER, target.getId());
    }

    @Test
    public void testConstructorWillSetInitialName() {
        final Target target = new Target(UNIQUE_IDENTIFIER, INITIAL_NAME, INITIAL_COORDINATES);

        assertEquals(INITIAL_NAME, target.getName());
    }

    @Test
    public void testConstructorWillSetInitialCoordinates() {
        final Target target = new Target(UNIQUE_IDENTIFIER, INITIAL_NAME, INITIAL_COORDINATES);

        assertEquals(INITIAL_COORDINATES, target.getCoordinates());
    }

    @Test
    public void testRenameWillReturnCopyOfTargetWithNewName() {
        final Target target = new Target(INITIAL_NAME, INITIAL_COORDINATES);
        final Target targetWithNewName = target.rename(NEW_NAME);

        assertNotSame(target, targetWithNewName);
        assertEquals(NEW_NAME, targetWithNewName.getName());
        assertNotEquals(NEW_NAME, target.getName());
    }
}
