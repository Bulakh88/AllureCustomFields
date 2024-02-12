package ru.bulakh.enums;

public enum StoryConstant {
    FIRST_STORY("Первый стори"),
    SECOND_STORY("Второй стори"),
    THIRD_STORY("Третий стори");

    private final String storyName;

    StoryConstant(String storyName) {
        this.storyName = storyName;
    }

    @Override
    public String toString() {
        return storyName;
    }
}
