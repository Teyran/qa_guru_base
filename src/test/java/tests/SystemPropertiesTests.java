package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;
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
        System.out.println(browser);
    }

    @Test
    @Tag("hello")
    void testSystemProperty1 () {
        String name = System.getProperty("name", "default student");
        String message = format("Hello, %s!", name);
        System.out.println(message);
    }
}
