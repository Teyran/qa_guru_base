package pages.steam;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class MainSteamPage {
    private SelenideElement languageDropDown = $("#language_pulldown"),
            searchField = $("#store_nav_search_term"),
            navigationArea = $("#store_nav_area"),
            installButton = $(".header_installsteam_btn_content");
    private ElementsCollection firstThreeNavElements = $$(".store_nav_bg span a.pulldown_desktop"),
            searchResult =$$("#search_results a");

    public MainSteamPage openPage() {
        open("https://store.steampowered.com/");
        return this;
    }

    public MainSteamPage openLanguageDropDown() {
        languageDropDown.click();
        return this;
    }

    public MainSteamPage chooseLanguage(String value) {
        $(withText(value)).click();
        return this;
    }

    public MainSteamPage checkLanguageOfButtons(List<String> expectedButtons) {
        firstThreeNavElements.shouldHave(texts(expectedButtons));
        return this;
    }

    public MainSteamPage sendingSearchRequest(String value) {
        searchField.setValue(value).pressEnter();
        return this;
    }

    public MainSteamPage checkSearchResult() {
        searchResult.shouldBe(sizeGreaterThan(0));
        return this;
    }

    public MainSteamPage checkLanguageOfShopButton(String value) {
        navigationArea.shouldHave(text(value));
        return this;
    }

    public MainSteamPage checkLanguageOfInstallButton(String value) {
        installButton.shouldHave(text(value));
        return this;
    }
}