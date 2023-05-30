package efs.task.unittests;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }
    @Test
    void shouldThrowIllegalArgumentException_whenHeightIsEqualZero() {
        //given
        double weight = 102.5; //any
        double height = 0.0;

        //when
        Class expectedExpection = IllegalArgumentException.class;

        //then
        Assertions.assertThrows(expectedExpection, () -> FitCalculator.isBMICorrect(weight, height));
    }

    @ParameterizedTest(name = "weight: {0}")
    @ValueSource(doubles = {83.1, 93.4, 105.5})
    void shouldReturnTrue_whenDietIsRecommended(double weight) {
        //given
        double height = 1.82;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "weight = {0}, height = {1}")
    @CsvSource({"75.4, 182.4", "125.4, 181.4", "99.6, 182.5"})
    void shouldReturnFalse_whenDietIsNotRecommended(double weight, double height) {
        //given

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @ParameterizedTest(name = "weight = {0}, height = {1}")
    @CsvFileSource(files = "src/test/resources/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_whenDietIsNotRecommendedFromFile(double weight, double height) {
        //given

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void shouldReturnUserWithTheWorstBMI() {
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;
        double height = 1.79;
        double weight = 97.3;

        //when
        User user = FitCalculator.findUserWithTheWorstBMI(users);

        //then
        Assertions.assertEquals(height, user.getHeight());
        Assertions.assertEquals(weight, user.getWeight());
    }

    @Test
    void shouldReturnNull() {
        //given
        List<User> users = new ArrayList<>();

        //when
        User userWithTheWorstBMI = FitCalculator.findUserWithTheWorstBMI(users);

        //then
        Assertions.assertNull(userWithTheWorstBMI);
    }

    @Test
    void shouldReturnTestUsersBMI() {
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;

        //when
        double [] BMIScores = FitCalculator.calculateBMIScore(users);

        //then
        assertArrayEquals(BMIScores, TestConstants.TEST_USERS_BMI_SCORE);
    }
}