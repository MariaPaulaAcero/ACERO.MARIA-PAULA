import com.github.javafaker.Faker;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ExecutiveSynthesizerTest {

    private static Faker faker;

    private Project wellFormedProject;
    private Project badFormedProject1; // Project without iterations
    private Project badFormedProject2; // Project where an iteration hasn't activities
    private Project badFormedProject3; // Project where a normal activity hasn't steps
    private Project badFormedProject4; // Project where a documented activity hasn't questions
    private Project badFormedProject5; // Project where a documented activity hasn't normal activity
    private Project badFormedProject6; // Project where a documented activity with normal activity hasn't steps

    public ExecutiveSynthesizerTest() {

        faker = new Faker(new Locale("en-US"));
    }

    @BeforeEach
    public void setup() {

        setupWellFormedProject();
        setupBadFormedProject1();
        setupBadFormedProject2();
        setupBadFormedProject3();
        setupBadFormedProject4();
        setupBadFormedProject5();
        setupBadFormedProject6();
    }

    @Test
    @DisplayName("GIVEN a well formed project WHEN get duration THEN return the project duration")
    public void shouldGetDurationWhenWellFormedProject() {

        try {
            Duration duration = wellFormedProject.getDuration();
            assertEquals(3, duration.toDays());
        } catch (SabanaResearchException e) {
            fail(e.getMessage());
        }
    }

    private void setupWellFormedProject() {

        Group group = new Group(faker.team().name());
        wellFormedProject = new Project(faker.team().name(), LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), group);
        Iteration iteration = new Iteration(faker.team().name(), wellFormedProject);

        // Create a Normal Activity
        NormalActivity normalActivity = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration);
        normalActivity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));

        // Create a Documented Activity
        NormalActivity activity = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, null);
        activity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));
        DocumentedActivity documentedActivity = new DocumentedActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration, activity);
        documentedActivity.addQuestion(new Question(Question.EASY_QUESTION, faker.team().name(), Duration.ofDays(1)));
    }

    private void setupBadFormedProject1() {

        Group group = new Group(faker.team().name());
        badFormedProject1 = new Project(faker.team().name(), LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), group);
    }

    private void setupBadFormedProject2() {

        Group group = new Group(faker.team().name());
        badFormedProject2 = new Project(faker.team().name(), LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), group);
        Iteration iteration = new Iteration(faker.team().name(), badFormedProject2);

        // Create a Normal Activity
        NormalActivity normalActivity = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration);
        normalActivity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));

        // Create a Documented Activity
        NormalActivity activity = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, null);
        activity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));
        DocumentedActivity documentedActivity = new DocumentedActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration, activity);
        documentedActivity.addQuestion(new Question(Question.EASY_QUESTION, faker.team().name(), Duration.ofDays(1)));

        // Create Iteration without activities
        new Iteration(faker.team().name(), badFormedProject2);
    }

    private void setupBadFormedProject3() {

        Group group = new Group(faker.team().name());
        badFormedProject3 = new Project(faker.team().name(), LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), group);
        Iteration iteration = new Iteration(faker.team().name(), badFormedProject3);

        // Create a Normal Activity
        NormalActivity normalActivity = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration);
        normalActivity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));

        // Create a Documented Activity
        NormalActivity activity = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, null);
        activity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));
        DocumentedActivity documentedActivity = new DocumentedActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration, activity);
        documentedActivity.addQuestion(new Question(Question.EASY_QUESTION, faker.team().name(), Duration.ofDays(1)));

        // Create a Normal Activity without steps
        new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration);
    }

    private void setupBadFormedProject4() {

        Group group = new Group(faker.team().name());
        badFormedProject4 = new Project(faker.team().name(), LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), group);
        Iteration iteration = new Iteration(faker.team().name(), badFormedProject4);

        // Create a Normal Activity
        NormalActivity normalActivity = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration);
        normalActivity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));

        // Create a Documented Activity
        NormalActivity activity = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, null);
        activity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));
        DocumentedActivity documentedActivity = new DocumentedActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration, activity);
        documentedActivity.addQuestion(new Question(Question.EASY_QUESTION, faker.team().name(), Duration.ofDays(1)));

        // Create a Documented Activity without questions
        NormalActivity activity2 = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, null);
        activity2.addStep(new Step(faker.team().name(), Duration.ofDays(1)));
        new DocumentedActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration, activity2);
    }

    private void setupBadFormedProject5() {

        Group group = new Group(faker.team().name());
        badFormedProject5 = new Project(faker.team().name(), LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), group);
        Iteration iteration = new Iteration(faker.team().name(), badFormedProject5);

        // Create a Normal Activity
        NormalActivity normalActivity = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration);
        normalActivity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));

        // Create a Documented Activity
        NormalActivity activity = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, null);
        activity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));
        DocumentedActivity documentedActivity = new DocumentedActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration, activity);
        documentedActivity.addQuestion(new Question(Question.EASY_QUESTION, faker.team().name(), Duration.ofDays(1)));

        // Create a Documented Activity without normal activity
        DocumentedActivity documentedActivity2 = new DocumentedActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration, null);
        documentedActivity2.addQuestion(new Question(Question.EASY_QUESTION, faker.team().name(), Duration.ofDays(1)));
    }

    private void setupBadFormedProject6() {

        Group group = new Group(faker.team().name());
        badFormedProject6 = new Project(faker.team().name(), LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), group);
        Iteration iteration = new Iteration(faker.team().name(), badFormedProject6);

        // Create a Normal Activity
        NormalActivity normalActivity = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration);
        normalActivity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));

        // Create a Documented Activity
        NormalActivity activity = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, null);
        activity.addStep(new Step(faker.team().name(), Duration.ofDays(1)));
        DocumentedActivity documentedActivity = new DocumentedActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration, activity);
        documentedActivity.addQuestion(new Question(Question.EASY_QUESTION, faker.team().name(), Duration.ofDays(1)));

        // Create a Documented Activity with normal activity without steps
        NormalActivity activity2 = new NormalActivity(faker.team().name(), Activity.ACTIVE_STATE, null);
        DocumentedActivity documentedActivity2 = new DocumentedActivity(faker.team().name(), Activity.ACTIVE_STATE, iteration, activity2);
        documentedActivity2.addQuestion(new Question(Question.EASY_QUESTION, faker.team().name(), Duration.ofDays(1)));
    }

    @Test
    public void shouldGetDurationActivitiesExecutives() throws SabanaResearchException {
        Iteration iteration1 =new Iteration("pepe",wellFormedProject);
        wellFormedProject.getIterations().add(iteration1);
        Activity activities1 = new Activity("Test in humans",Duration.ofDays(3)) {
            @Override
            public Duration getDuration() throws SabanaResearchException {
                return Duration.ofDays(3);
            }
        };
        iteration1.getActivities().add(activities1);

        assertEquals(Duration.ofDays(0),wellFormedProject.summarize());
        System.out.println(wellFormedProject.summarize().toDays());
    }
}
