package ui.tests.selenide;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDropTests {

    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
        baseUrl = "https://the-internet.herokuapp.com/drag_and_drop";
        pageLoadStrategy = "eager";
        holdBrowserOpen = false;
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    void testDragAndDropUsingLocators() {

        open(baseUrl);
        $(byId("content")).should(appear);
        $$("div[draggable]").shouldHave(texts("A", "B"));

        actions().moveToElement($("#column-a")).clickAndHold()
                 .moveToElement($("#column-b")).release().perform();

        $$("div[draggable]").shouldHave(texts("B", "A"));
    }

    @Test
    void testDragAndDropUsingOffsets() {

        open(baseUrl);
        $(byId("content")).should(appear);
        $$("div[draggable]").shouldHave(texts("A", "B"));

        Point firstTriangleLocation = $("#column-a").getLocation();
        Point secondTriangleLocation = $("#column-b").getLocation();

        int deltaX = firstTriangleLocation.getX() - secondTriangleLocation.getX();
        int deltaY = firstTriangleLocation.getY() - secondTriangleLocation.getY();

        actions().moveToElement($("#column-b"))
                .clickAndHold()
                .moveByOffset(deltaX, deltaY)
                .release()
                .perform();

        $$("div[draggable]").shouldHave(texts("B", "A"));
    }


    @Test
    void testDragAndDropUsingDragAndDropMethod() {

        open(baseUrl);
        $(byId("content")).should(appear);
        $$("div[draggable]").shouldHave(texts("A", "B"));

        $("#column-a").dragAndDrop(to("#column-b"));

        $$("div[draggable]").shouldHave(texts("B", "A"));
    }
}
