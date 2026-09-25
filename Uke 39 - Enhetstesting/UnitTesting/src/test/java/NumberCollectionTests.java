import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
Enhetstester for en enhet som ikke returnerer noen verdi, men som istedet påvirker noe utenfor seg selv (her en liste)
 */
public class NumberCollectionTests {


    @Test
    @DisplayName("addPositiveNumber(): Positive number are added to collection in order")
    public void addPositiveNumber_PositiveNumbersAreAddedToCollectionInOrder() {
        // Arrange
        NumberCollection numberCollection = new NumberCollection();

        // Act
        numberCollection.addPositiveNumber(0);
        numberCollection.addPositiveNumber(1);
        numberCollection.addPositiveNumber(100);

        // Assert
        Assertions.assertEquals(3, numberCollection.getNumberCollection().size());
        Assertions.assertEquals(0, numberCollection.getNumberCollection().getFirst());
        Assertions.assertEquals(1, numberCollection.getNumberCollection().get(1));
        Assertions.assertEquals(100, numberCollection.getNumberCollection().get(2));
    }

    @Test
    @DisplayName("addPositiveNumber(): Negative numbers are ignored")
    public void addPositiveNumber_NegativeNumbersAreIgnored() {
        // Arrange
        NumberCollection numberCollection = new NumberCollection();

        // Act
        numberCollection.addPositiveNumber(-1);
        numberCollection.addPositiveNumber(-100);

        // Assert
        Assertions.assertEquals(0, numberCollection.getNumberCollection().size());
    }
}
