package tests.parametrization;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pages.parametrization.MainSteamPage;

import java.util.List;
import java.util.stream.Stream;

public class SteamLanguageTests {

    private final MainSteamPage mainSteamPage = new MainSteamPage();

    @ValueSource(strings = {
            "Cyberpunk", "CS", "half life"
    })
    @ParameterizedTest(name = "Search result should not be empty")
    void searchResultsShouldNotBeEmptyTest(String searchQuery) {
        mainSteamPage.openPage()
                .sendingSearchRequest(searchQuery)
                .checkSearchResult();
    }

    @CsvSource(value = {
            "Dansk , Din butik",
            "Deutsch, Ihr Shop",
            "Nederlands , Jouw winkel"
    })
    @ParameterizedTest(name = "Check if text in 'shop' button translated correctly for language {0}")
    void correctLanguageShouldDisplayedTest(String language, String expectedResult) {
        mainSteamPage.openPage()
                .openLanguageDropDown()
                .chooseLanguage(language)
                .checkLanguageOfShopButton(expectedResult);
    }

    @EnumSource(Language.class)
    @ParameterizedTest(name = "Check if text in 'download' button translated correctly for language {0}")
    void shouldDisplayedCorrectTextVerifyWithEnumTest(Language language) {
        mainSteamPage.openPage()
                .openLanguageDropDown()
                .chooseLanguage(language.setLanguage())
                .checkLanguageOfInstallButton(language.description);
    }

    static Stream<Arguments> steamShouldDisplayCorrectButtonsTest() {
        return Stream.of(
                Arguments.of(
                        "English",
                        List.of("Your Store", "New & Noteworthy", "Categories")),
                Arguments.of(
                        "Deutsch",
                        List.of("Ihr Shop", "Neu und nennenswert", "Kategorien"))
        );
    }
    @MethodSource
    @ParameterizedTest(name = "Check if text translated correctly for language {0}")
    void steamShouldDisplayCorrectButtonsTest(String language, List<String> expectedButtons) {
        mainSteamPage.openPage()
                .openLanguageDropDown()
                .chooseLanguage(language)
                .checkLanguageOfButtons(expectedButtons);
    }

    enum Language {

        NORSK("Installer Steam") {
            String setLanguage() {
                return "Norsk";
            }
        },
        DEUTSCH("Steam installieren") {
            String setLanguage() {
                return "Deutsch";
            }
        };

        abstract String setLanguage();
        private String description;
        Language(String description) {
            this.description = description;
        }
    }

}

