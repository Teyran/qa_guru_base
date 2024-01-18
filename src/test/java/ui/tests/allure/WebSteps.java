package ui.tests.allure;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Open main page")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Search for {repo}")
    public void searchForRepository(String repo) {
        $("[placeholder='Search or jump to...']")
                .shouldBe(and("clickable", exist, enabled)).click();
        $("#query-builder-test").setValue(repo).pressEnter();
    }

    @Step("Click on Repo link {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Open Issues tab")
    public void openIssueTab() {
        $("#issues-tab").click();
    }

    @Step("Check if an issue exist {issue}")
    public void shouldSeeIssueWitNumber(String issue) {
        $(withTagAndText("span", "#" + issue)).should(visible);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte [] takeScreenshot() {
        //$(withTagAndText("span", "#" + issue)).should(exist);
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
