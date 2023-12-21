package basic_github_tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Search Selenide repository in Github")
public class SelenideRepositorySearchTests {
    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
        baseUrl = "https://github.com/";
        pageLoadStrategy = "eager";
        holdBrowserOpen = false;
        timeout = 10000;
    }

    @Test
    void shouldFindSelenideRepositoryAtTheTop() {
        open(baseUrl);
        $("[placeholder='Search or jump to...']").shouldBe(and("clickable", exist, enabled)).click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $$("div[data-testid='results-list'] h3 a").first().click();
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
    }
}
