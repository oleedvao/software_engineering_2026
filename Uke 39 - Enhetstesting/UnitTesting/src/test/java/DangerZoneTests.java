import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
Tester en enhet som kaster en Exception
 */
public class DangerZoneTests {

    @Test
    @DisplayName("killTheProgram(): RuntimeException is thrown when method is called")
    public void killTheProgram_RuntimeExceptionIsThrownWhenMethodIsCalled() {
        DangerZone dangerZone = new DangerZone();

        Assertions.assertThrows(RuntimeException.class, () -> {
            dangerZone.killTheProgram();
        });
    }
}
