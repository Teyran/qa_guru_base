package ui.tests.files.home.assignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import ui.tests.files.model.JsonData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JsonTest {
    ClassLoader cl = JsonTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void jsonParsingTest() throws IOException {

        try (InputStream is = cl.getResourceAsStream("data.json");
             InputStreamReader isr = new InputStreamReader(is)) {
            JsonData data = objectMapper.readValue(isr, JsonData.class);
            Assertions.assertEquals("John", data.firstName);
            Assertions.assertEquals("Johns", data.lastName);
            Assertions.assertEquals(30, data.age);
            Assertions.assertEquals(List.of("English","Turkish"), data.languages);
            Assertions.assertEquals(List.of("Math","Science"), data.hobbies);
        }
    }
}
