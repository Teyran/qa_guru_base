package tests.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE = "80";

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open main page", () -> {
            open("https://github.com");
        });
        step("Search for repo " + REPOSITORY, () -> {
            $("[placeholder='Search or jump to...']")
                    .shouldBe(and("clickable", exist, enabled)).click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Click on Repo link " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Open Issues tab", () -> {
            $("#issues-tab").click();
        });
        step("Check if an issue exist " + ISSUE, () -> {
            $(withTagAndText("span", "#" + ISSUE)).should(exist);
        });
    }

    @Test
    public void testAnnotatedStep () {
        WebSteps steps = new WebSteps();
        SelenideLogger.addListener("allure", new AllureSelenide());
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWitNumber(ISSUE);
    }

}
