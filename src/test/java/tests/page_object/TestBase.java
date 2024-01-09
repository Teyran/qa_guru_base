package tests.page_object;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.*;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
        baseUrl = "https://demoqa.com";
        pageLoadStrategy = "eager";
        remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}
