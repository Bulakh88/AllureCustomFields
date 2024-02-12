import io.qameta.allure.Allure;
import io.qameta.allure.model.Label;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bulakh.enums.EpicConstant;
import ru.bulakh.enums.LocationConstant;
import ru.bulakh.enums.StoryConstant;
import ru.bulakh.extention.Doc;

import java.util.ArrayList;
import java.util.List;

@Doc(
        epic = EpicConstant.FIRST_EPIC,
        story = StoryConstant.SECOND_STORY,
        location = {
                LocationConstant.FIRST_LOCATION,
                LocationConstant.THIRD_LOCATION}
)
public class WithCustomFieldTest {

    @Test
    @DisplayName("Использование аннотации @Doc")
    @Doc(
            epic = EpicConstant.SECOND_EPIC,
            story = {
                    StoryConstant.FIRST_STORY,
                    StoryConstant.THIRD_STORY
            }
    )
    void test1() {
        Allure.step("Epic: 1,2; Story: 1,2,3; Location: 1,3");
        List<String> expectedEpic = new ArrayList<>();
        List<String> expectedStory= new ArrayList<>();
        List<String> expectedLocation = new ArrayList<>();


        //По алфавиту
        expectedEpic.add(EpicConstant.SECOND_EPIC.toString());
        expectedEpic.add(EpicConstant.FIRST_EPIC.toString());
        expectedStory.add(StoryConstant.SECOND_STORY.toString());
        expectedStory.add(StoryConstant.FIRST_STORY.toString());
        expectedStory.add(StoryConstant.THIRD_STORY.toString());
        expectedLocation.add(LocationConstant.FIRST_LOCATION.toString());
        expectedLocation.add(LocationConstant.THIRD_LOCATION.toString());

        List<String> actualEpic = new ArrayList<>();
        List<String> actualStory= new ArrayList<>();
        List<String> actualLocation = new ArrayList<>();

        Allure.getLifecycle().updateTestCase(test -> {
            List<Label> labels = test.getLabels();
            for (Label label : labels) {
                if (label.getName().equals("epic")) {
                    actualEpic.add(label.getValue());
                }
                if (label.getName().equals("story")) {
                    actualStory.add(label.getValue());
                }
                if (label.getName().equals("location")) {
                    actualLocation.add(label.getValue());
                }
            }
        });

        List<String> actualSortedEpic = actualEpic.stream().sorted().toList();
        List<String> actualSortedStory= actualStory.stream().sorted().toList();
        List<String> actualSortedLocation = actualLocation.stream().sorted().toList();

        Assertions.assertArrayEquals(new List[]{expectedEpic}, new List[]{actualSortedEpic});
        Assertions.assertArrayEquals(new List[]{expectedStory}, new List[]{actualSortedStory});
        Assertions.assertArrayEquals(new List[]{expectedLocation}, new List[]{actualSortedLocation});
    }

    @Doc(location = LocationConstant.SECOND_LOCATION)
    @DisplayName("Использование аннотации @Doc")
    @Test
    void test2() {
        Allure.step("Epic: 1; Story: 2; Location: 1,2,3");

        List<String> expectedEpic = new ArrayList<>();
        List<String> expectedStory= new ArrayList<>();
        List<String> expectedLocation = new ArrayList<>();

        //По алфавиту
        expectedEpic.add(EpicConstant.FIRST_EPIC.toString());
        expectedStory.add(StoryConstant.SECOND_STORY.toString());
        expectedLocation.add(LocationConstant.SECOND_LOCATION.toString());
        expectedLocation.add(LocationConstant.FIRST_LOCATION.toString());
        expectedLocation.add(LocationConstant.THIRD_LOCATION.toString());

        List<String> actualEpic = new ArrayList<>();
        List<String> actualStory= new ArrayList<>();
        List<String> actualLocation = new ArrayList<>();

        Allure.getLifecycle().updateTestCase(test -> {
            List<Label> labels = test.getLabels();
            for (Label label : labels) {
                if (label.getName().equals("epic")) {
                    actualEpic.add(label.getValue());
                }
                if (label.getName().equals("story")) {
                    actualStory.add(label.getValue());
                }
                if (label.getName().equals("location")) {
                    actualLocation.add(label.getValue());
                }
            }
        });

        List<String> actualSortedEpic = actualEpic.stream().sorted().toList();
        List<String> actualSortedStory= actualStory.stream().sorted().toList();
        List<String> actualSortedLocation = actualLocation.stream().sorted().toList();

        Assertions.assertArrayEquals(new List[]{expectedEpic}, new List[]{actualSortedEpic});
        Assertions.assertArrayEquals(new List[]{expectedStory}, new List[]{actualSortedStory});
        Assertions.assertArrayEquals(new List[]{expectedLocation}, new List[]{actualSortedLocation});
    }
}
