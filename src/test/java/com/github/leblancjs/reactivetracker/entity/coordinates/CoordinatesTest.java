package com.github.leblancjs.reactivetracker.coordinates;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CoordinatesTest {
    private static final double INITIAL_LATITUDE = 1234d;
    private static final double INITIAL_LONGITUDE = 4321d;

    @Test
    public void testConstructorWillSetInitialLatitude() {
        final Coordinates coordinates = new Coordinates(INITIAL_LATITUDE, INITIAL_LONGITUDE);

        assertEquals(INITIAL_LATITUDE, coordinates.getLatitude(), 0d);
    }

    @Test
    public void testConstructorWillSetInitialLongitude() {
        final Coordinates coordinates = new Coordinates(INITIAL_LATITUDE, INITIAL_LONGITUDE);

        assertEquals(INITIAL_LONGITUDE, coordinates.getLongitude(), 0d);
    }

    @Test
    public void testConstructorWillCopyInitialCoordinates() {
        final Coordinates initialCoordinates = new Coordinates(INITIAL_LATITUDE, INITIAL_LONGITUDE);
        final Coordinates coordinates = new Coordinates(initialCoordinates);

        assertEquals(initialCoordinates, coordinates);
    }

    @Test
    public void testEqualsWillReturnFalseWhenComparingWithNullObject() {
        final Coordinates coordinates = new Coordinates(INITIAL_LATITUDE, INITIAL_LONGITUDE);

        assertNotEquals(coordinates, null);
    }

    @Test
    public void testEqualsWillReturnFalseWhenComparingWithObjectThatIsNotPairOfCoordinates() {
        final Coordinates coordinates = new Coordinates(INITIAL_LATITUDE, INITIAL_LONGITUDE);

        assertNotEquals(coordinates, new Object());
    }

    @Test
    public void testEqualsWillReturnFalseWhenComparingWithPairOfCoordinatesThatHasDifferentLatitude() {
        final Coordinates coordinates = new Coordinates(INITIAL_LATITUDE, INITIAL_LONGITUDE);
        final Coordinates otherCoordinates = new Coordinates(INITIAL_LATITUDE * 2, INITIAL_LONGITUDE);

        assertNotEquals(coordinates, otherCoordinates);
    }

    @Test
    public void testEqualsWillReturnFalseWhenComparingWithPairOfCoordinatesThatHasDifferentLongitude() {
        final Coordinates coordinates = new Coordinates(INITIAL_LATITUDE, INITIAL_LONGITUDE);
        final Coordinates otherCoordinates = new Coordinates(INITIAL_LATITUDE, INITIAL_LONGITUDE * 2);

        assertNotEquals(coordinates, otherCoordinates);
    }

    @Test
    public void testEqualsWillReturnFalseWhenComparingWithPairOfCoordinatesThatHasDifferentLatitudeAndLongitude() {
        final Coordinates coordinates = new Coordinates(INITIAL_LATITUDE, INITIAL_LONGITUDE);
        final Coordinates otherCoordinates = new Coordinates(INITIAL_LATITUDE * 2, INITIAL_LONGITUDE * 2);

        assertNotEquals(coordinates, otherCoordinates);
    }

    @Test
    public void testEqualsWillReturnTrueWhenComparingWithPairOfCoordinatesThatHasSameLatitudeAndLongitude() {
        final Coordinates coordinates = new Coordinates(INITIAL_LATITUDE, INITIAL_LONGITUDE);
        final Coordinates otherCoordinates = new Coordinates(INITIAL_LATITUDE, INITIAL_LONGITUDE);

        assertEquals(coordinates, otherCoordinates);
    }
}
