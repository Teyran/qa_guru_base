package tests.simple;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.pageLoadStrategy;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {
    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
        baseUrl = "https://demoqa.com";
        pageLoadStrategy = "eager";
        holdBrowserOpen = true;
        timeout = 5000;
    }

    @AfterEach
    void afterEach() {
        closeWindow();
    }

    @Test
    void fillFormTest() {
        open(baseUrl.concat("/text-box"));
        executeJavaScript("$('#fixedban').remove()");

        $("#userName").setValue("Alex");
        $("#userEmail").setValue("aleks@gmail.com");
        $("#currentAddress").setValue("Some street 1");
        $("#permanentAddress").setValue("Some street 2");
        $("#submit").click();

        $("#output #name").shouldHave(Condition.text("Alex"));
        $("#output #email").shouldHave(Condition.text("aleks@gmail.com"));
        $("#output #currentAddress").shouldHave(Condition.text("Some street 1"));
        $("#output #permanentAddress").shouldHave(Condition.text("Some street 2"));
    }
}
