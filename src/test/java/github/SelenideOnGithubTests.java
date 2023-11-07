package github;

import org.junit.jupiter.api.BeforeAll;
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
        timeout = 10000;
    }

    @Test
    void testAndreiSolntsevIsTheFirstContributor() {
        open("/selenide/selenide");
        $("div.Layout-sidebar").$(byText("Contributors"))
                //.closest("BorderGrid-cell").$$("ul li").first().hover();
                .closest("h2").sibling(0).$$("li").first().hover();

        $(".Popover .Truncate").shouldHave(text("Andrei Solntsev"));
    }

    @Test
    void testJUnitCodeExamplePresentedOnSelenideWikiPage() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $$(".markdown-body a").findBy(text("Soft Assertions")).click();
        $$("h4 a").findBy(partialText("using JUnit5")).shouldBe(visible);
        $$("pre").findBy(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                                                                        "class Tests {\n" +
                                                                        "  @Test\n" +
                                                                        "  void test() {\n" +
                                                                        "    Configuration.assertionMode = SOFT;\n" +
                                                                        "    open(\"page.html\");\n" +
                                                                        "\n" +
                                                                        "    $(\"#first\").should(visible).click();\n" +
                                                                        "    $(\"#second\").should(visible).click();\n" +
                                                                        "  }\n" +
                                                                        "}"))
                .shouldBe(visible);

        $$("pre").findBy(text("class Tests {\n" +
                                            "  @RegisterExtension \n" +
                                            "  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
                                            "\n" +
                                            "  @Test\n" +
                                            "  void test() {\n" +
                                            "    Configuration.assertionMode = SOFT;\n" +
                                            "    open(\"page.html\");\n" +
                                            "\n" +
                                            "    $(\"#first\").should(visible).click();\n" +
                                            "    $(\"#second\").should(visible).click();\n" +
                                            "  }\n" +
                                            "}"))
                .shouldBe(visible);


    }
}
