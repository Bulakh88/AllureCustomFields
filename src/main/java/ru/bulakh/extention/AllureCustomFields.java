package ru.bulakh.extention;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Label;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import ru.bulakh.enums.EpicConstant;
import ru.bulakh.enums.LocationConstant;
import ru.bulakh.enums.StoryConstant;

import java.util.ArrayList;
import java.util.List;

public class AllureCustomFields implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), Doc.class).ifPresent(this::setLabelToAllure);
        AnnotationSupport.findAnnotation(context.getRequiredTestClass(), Doc.class).ifPresent(this::setLabelToAllure);
    }

    private void setLabelToAllure(Doc annotationValue) {
        EpicConstant[] epicAnnotation = annotationValue.epic();
        StoryConstant[] storyAnnotation = annotationValue.story();
        LocationConstant[] locationAnnotation = annotationValue.location();

        List<Label> labels = new ArrayList<>();

        for (EpicConstant epic : epicAnnotation) {
            Label epicLabel = new Label().setName("epic").setValue(epic.toString());
            labels.add(epicLabel);
        }

        for (StoryConstant story : storyAnnotation) {
            Label storyLabel = new Label().setName("story").setValue(story.toString());
            labels.add(storyLabel);
        }

        for (LocationConstant location : locationAnnotation) {
            Label locationLabel = new Label().setName("location").setValue(location.toString());
            labels.add(locationLabel);
        }

        Allure.getLifecycle().updateTestCase(test -> test.getLabels().addAll(labels));
    }
}

