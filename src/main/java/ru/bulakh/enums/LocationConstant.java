package ru.bulakh.enums;

public enum LocationConstant {
    FIRST_LOCATION("Первая локация"),
    SECOND_LOCATION("Вторая локация"),
    THIRD_LOCATION("Третья локация");

    private final String locationName;

    LocationConstant(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return locationName;
    }
}
