package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemPropertiesTests {

    @Test
    void testSystemProperty() {
        String browser = System.getProperty("browser");
        System.out.println(browser); // null
    }

    @Test
    void testSystemPropertyWithSettingExplicitlyValue (){
        System.setProperty("browser", "chrome");
        String browser = System.getProperty("browser");
        System.out.println(browser); //chrome
    }

    @Test
    void testSystemPropertyWithSettingExplicitlyValueAdnDefault (){
        System.setProperty("browser", "mozilla");
        String browser = System.getProperty("browser", "chrome");
        System.out.println(browser); //chrome
    }

    @Test
    @Tag("property")
    void testSystemPropertyProvidingFromJenkins () {
        String browser = System.getProperty("browser", "mozilla");
        assertEquals(browser, "opera");
        System.out.println(browser); //opera
    }
}
