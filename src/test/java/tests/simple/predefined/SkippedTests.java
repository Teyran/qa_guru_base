package tests.simple.predefined;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("simple")
public class SkippedTests {

    @Test
    @Disabled("JiraId 0")
    void someTest() {
        assertTrue(false);
    }

    @Test
    @Disabled("JiraId 1")
    void someTest1() {
        assertTrue(false);
    }
    @Test
    @Disabled("JiraId 2")
    void someTest2() {
        assertTrue(false);
    }
}
