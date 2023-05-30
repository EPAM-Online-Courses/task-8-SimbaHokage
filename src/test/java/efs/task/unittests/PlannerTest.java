package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PlannerTest {

    private Planner planner;

    @BeforeEach
    void set(){
        planner = new Planner();
    }

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void shouldReturnCorrectCaloriesNeeds (ActivityLevel activityLevel) {
        //given
        User user = TestConstants.TEST_USER;
        double dailyCaloriesDemandFromMap = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);

        //when
        double dailyCaloriesDemand = planner.calculateDailyCaloriesDemand(user, activityLevel);

        //then
        Assertions.assertEquals(dailyCaloriesDemand, dailyCaloriesDemandFromMap);
    }

    @Test
    void shouldReturnCorrectIntake() {
        //given
        User user = TestConstants.TEST_USER;
        DailyIntake expectedDailyIntake = TestConstants.TEST_USER_DAILY_INTAKE;

        //when
        DailyIntake resultDailyIntake = planner.calculateDailyIntake(user);

        //then
        Assertions.assertEquals(expectedDailyIntake.getCalories(), resultDailyIntake.getCalories());
        Assertions.assertEquals(expectedDailyIntake.getFat(), resultDailyIntake.getFat());
        Assertions.assertEquals(expectedDailyIntake.getCarbohydrate(), resultDailyIntake.getCarbohydrate());
        Assertions.assertEquals(expectedDailyIntake.getProtein(), resultDailyIntake.getProtein());
    }
}
