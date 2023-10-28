package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
        baseUrl = "https://demoqa.com";
        pageLoadStrategy = "eager";
        timeout = 10000;
    }

    @Test
    void fillFormTest() {
        open(baseUrl.concat("/automation-practice-form"));
        executeJavaScript("$('#fixedban').remove()");

        $("#firstName").setValue("Teyran");
        $("#lastName").setValue("Atamov");
        $("#userEmail").setValue("teyranAtamov@mymail.com");
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byValue("4")).click();
        $(".react-datepicker__year-select").click();
        $(byValue("1993")).click();
        $(".react-datepicker__day--011").click();
        $("#subjectsInput").val("Physics").pressEnter();
        $("[for='hobbies-checkbox-1']").click();
        $("[for='hobbies-checkbox-2']").click();
        $("[for='hobbies-checkbox-3']").click();
        $("#uploadPicture").uploadFromClasspath("picture.png");
        $("#currentAddress").setValue("Some address");
        $("#state").scrollTo().click();
        $("#react-select-3-input").val("Haryana").pressEnter();
        $("#city").click();
        $("#react-select-4-input").val("Karnal").pressEnter().pressTab().pressEnter();

        $x("//td[text()='Student Name']/following::td[text()='Teyran Atamov']").shouldBe(visible);
        $x("//td[text()='Student Email']/following::td[text()='teyranAtamov@mymail.com']").shouldBe(visible);
        $x("//td[text()='Gender']/following::td[text()='Male']").shouldBe(visible);
        $x("//td[text()='Mobile']/following::td[text()='1234567890']").shouldBe(visible);
        $x("//td[text()='Date of Birth']/following::td[text()='11 May,1993']").shouldBe(visible);
        $x("//td[text()='Subjects']/following::td[text()='Physics']").shouldBe(visible);
        $x("//td[text()='Hobbies']/following::td[text()='Sports, Reading, Music']").shouldBe(visible);
        $x("//td[text()='Picture']/following::td[text()='picture.png']").shouldBe(visible);
        $x("//td[text()='Address']/following::td[text()='Some address']").shouldBe(visible);
        $x("//td[text()='State and City']/following::td[text()='Haryana Karnal']").shouldBe(visible);
    }
}

