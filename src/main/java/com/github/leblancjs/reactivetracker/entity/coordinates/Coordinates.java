package com.github.leblancjs.reactivetracker.entity.coordinates;

public class Coordinates {
    private final double latitude;
    private final double longitude;

    public Coordinates(final double initialLatitude, final double initialLongitude) {
        this.latitude = initialLatitude;
        this.longitude = initialLongitude;
    }

    public Coordinates(final Coordinates initialCoordinates) {
        this.latitude = initialCoordinates.getLatitude();
        this.longitude = initialCoordinates.getLongitude();
    }

    @Override
    public boolean equals(final Object otherObject) {
        if (!(otherObject instanceof Coordinates)) {
            return false;
        }

        final Coordinates otherCoordinates = (Coordinates) otherObject;
        final boolean latitudeIsEqual = getLatitude() == otherCoordinates.getLatitude();
        final boolean longitudeIsEqual = getLongitude() == otherCoordinates.getLongitude();
        return latitudeIsEqual && longitudeIsEqual;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
