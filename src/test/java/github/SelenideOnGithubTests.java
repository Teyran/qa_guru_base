package github;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideOnGithubTests {

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
    void testAndreiSolntsevIsTheFirstContributor() {
        open("/selenide/selenide");
        $("div.Layout-sidebar").$(byText("Contributors"))
                //.closest("BorderGrid-cell").$$("ul li").first().hover();
                .closest("h2").sibling(0).$$("li").first().hover();
        $(".Popover-message").shouldHave(text("Andrei Solntsev"));
    }

    @Test
    void testJUnitCodeExamplePresentedOnSelenideWikiPage() {
        String jUnit5Extend =
                """ 
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                """;

        String jUnit5ExtendInside =
                """
                class Tests {
                  @RegisterExtension
                  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();
                                
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                """;
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $$(".markdown-body a").findBy(text("Soft Assertions")).click();
        $$("h4 a").findBy(partialText("using JUnit5")).shouldBe(visible);
        $$("pre").findBy(text(jUnit5Extend)).shouldBe(visible);
        $$("pre").findBy(text(jUnit5ExtendInside)).shouldBe(visible);
    }
}