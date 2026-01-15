import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.logging.Logger;

@ExtendWith(LoggerExtension.class)
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
    public void paramTest(String value) {
        Assertions.assertNotEquals("", value);
    }

    @Test
    public void softAssertTest() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(2 > 1).isTrue();
        softAssertions.assertThat(5).isEqualTo(5);
        softAssertions.assertThat(9).isGreaterThan(7);
        softAssertions.assertAll();
    }
}



