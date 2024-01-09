package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultComponent {

    private SelenideElement resultltModalForm = $("tbody");

    @Step("Check if data is presented accordingly in popup")
    public ResultComponent checkResult(String key, String value) {
        resultltModalForm.$(byText(key)).parent()
                .shouldHave(text(value));

        return this;
    }
}