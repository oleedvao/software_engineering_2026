import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/*
Enhetstester for en typisk enhet som returnerer én enkelt verdi
 */
public class InformationCheckerTests {
    // Testene skrevet i forelesning, men omgjort til parameteriserte tester.
    // Les om parameteriserte tester i egen modul på Canvas

    // Siden Arrange er lik for alle testene kan denne opprettes som en global variabel for klassen
    InformationChecker informationChecker = new InformationChecker();

    @ParameterizedTest(name = "Age {0} should return {1}")
    @CsvSource({
            "40, 40",
            "120, 120",
            "0, 0"
    })
    @DisplayName("controlAge(): Age within acceptable range is handled correctly.")
    public void controlAge_ageWithinAcceptableRangeIsHandledCorrectly(int age, int expectedResult) {
        // Arrange
        //InformationChecker informationChecker = new InformationChecker();

        // Act
        int result = informationChecker.controlAge(age);

        // Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @ParameterizedTest(name = "Age {0} should return 120")
    @ValueSource(ints = {121, 5000})
    @DisplayName("controlAge(): Age over acceptable range returns 120.")
    public void controlAge_ageOverAcceptableRangeReturns120(int age) {
        // Arrange
        //InformationChecker informationChecker = new InformationChecker();

        // Act
        int result = informationChecker.controlAge(age);

        // Assert
        Assertions.assertEquals(120, result);
    }

    @ParameterizedTest(name = "Age {0} should return 0")
    @ValueSource(ints = {-1, -33333})
    @DisplayName("controlAge(): Age under acceptable range returns 0")
    public void controlAge_ageUnderAcceptableRangeReturns0_parameterized(int age) {
        // Arrange
        //InformationChecker informationChecker = new InformationChecker();

        // Act
        int result = informationChecker.controlAge(age);

        // Assert
        Assertions.assertEquals(0, result);
    }

    // Testene slik de originalt ble skrevet i forelesning
    /*
    @Test
    @DisplayName("controlAge(): Age within acceptable range is handled correctly")
    public void controlAge_ageWithinAcceptableRangeIsHandledCorrectly() {
        // Arrange
        InformationChecker checker = new InformationChecker();

        // Act
        int result = checker.controlAge(40);
        int highestAgeResult = checker.controlAge(120);
        int lowestAgeResult = checker.controlAge(0);

        // Assert
        Assertions.assertEquals(40, result);
        Assertions.assertEquals(120, highestAgeResult);
        Assertions.assertEquals(0, lowestAgeResult);
    }

    @Test
    public void controlAge_ageOverAcceptableRangeReturns120() {
        // Arrange
        InformationChecker checker = new InformationChecker();

        // Act
        int lowestAgeResult = checker.controlAge(121);
        int result = checker.controlAge(5000);

        // Assert
        Assertions.assertEquals(120, lowestAgeResult);
        Assertions.assertEquals(120, result);
    }

    @Test
    public void controlAge_ageUnderAcceptableRangeReturns0() {
        // Arrange
        InformationChecker checker = new InformationChecker();

        // Act
        int highestAgeResult = checker.controlAge(-1);
        int result = checker.controlAge(-33333);

        // Assert
        Assertions.assertEquals(0, highestAgeResult);
        Assertions.assertEquals(0, result);
    }
     */

}
