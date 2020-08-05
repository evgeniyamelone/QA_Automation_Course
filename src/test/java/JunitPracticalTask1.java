import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Assertions;

import java.util.List;


public class JunitPracticalTask1 {

    @Test
    public void equal() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void notEqual_Test() {
        Assertions.assertNotEquals(1, 2);
    }

    @Test
    public void assertTrue_Test() {
        Assertions.assertTrue(2 > 1);
    }

    @Test
    public void assertFalse_Test() {
        Assertions.assertFalse(2 < 1);
    }

    @Test
    public void failTest() {
        List<String> list = List.of(
                "One",
                "Two",
                "Three");
        for (String s : list) {
            if (s.equals("")) {
                Assertions.fail("Empty string in prohibited!");
            }
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"one", "two", "three"})
    public void paramTest(String value){
        Assertions.
        Assertions.assertNotEquals("", value);
    }


}



