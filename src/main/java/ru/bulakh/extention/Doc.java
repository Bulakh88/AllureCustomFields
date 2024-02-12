package ru.bulakh.extention;

import org.junit.jupiter.api.extension.ExtendWith;
import ru.bulakh.enums.EpicConstant;
import ru.bulakh.enums.StoryConstant;
import ru.bulakh.enums.LocationConstant;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@ExtendWith(AllureCustomFields.class)
public @interface Doc {

    EpicConstant[] epic() default {};
    StoryConstant[] story() default {};
    LocationConstant[] location() default {};
}

