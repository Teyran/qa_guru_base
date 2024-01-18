package ui.tests.simple;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
@DisplayName("Github Enterprise solution tests")
public class GithubEnterpriseSolutionTests {

    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
        baseUrl = "https://github.com";
        pageLoadStrategy = "eager";
        holdBrowserOpen = false;
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    void testEnterpriseSolutionTitle() {
        open(baseUrl);
        $(byTagAndText("button", "Solutions")).shouldBe(visible).hover();
        $("#solutions-for-heading").ancestor(".HeaderMenu-dropdown").should(appear);
        $("#solutions-for-heading").parent().$(byTagAndText("a", "Enterprise")).click();
        $("h1#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform."));
    }

}
