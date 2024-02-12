package ru.bulakh.enums;

public enum EpicConstant {
    FIRST_EPIC("Первый эпик"),
    SECOND_EPIC("Второй эпик"),
    THIRD_EPIC("Третий эпик");

    private final String epicName;

    EpicConstant(String epicName) {
        this.epicName = epicName;
    }

    @Override
    public String toString() {
        return epicName;
    }
}
