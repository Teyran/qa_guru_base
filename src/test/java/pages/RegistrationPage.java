package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;


import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private SelenideElement

            pageTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            inputState = $("#state"),
            inputCity = $("#city"),
            buttonSubmit = $("#submit"),
            inputCurrentAddress = $("#currentAddress");

    CalendarComponent calendarComponent = new CalendarComponent();

    @Step("Open page with automation-practice-form")
    public RegistrationPage openPage() {
        open(baseUrl.concat("/automation-practice-form"));
        pageTitle.shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }
    @Step("Set student's first name")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Set student's last name")
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Set student's email address")
    public RegistrationPage setEmailInput(String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step("Set student's gender")
    public RegistrationPage setGenderWrapper(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Set student's number")
    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }
    @Step("Set student's date of birth")
    public RegistrationPage setDateOfBirth(String month, String day, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(month, day, year);
        return this;
    }

    @Step("Set student's subject")
    public RegistrationPage setSubject(String value) {
        subjectInput.val(value).pressEnter();
        return this;
    }

    @Step("Set student's hobbies")
    public RegistrationPage setHobbies(String... hobbies) {

        for (int i = 0; i < hobbies.length; i++) {
            $(byText(hobbies[i])).click();
        }
        return this;
    }

    @Step("Set student's current address")
    public RegistrationPage enterCurrentAddress(String address) {
        inputCurrentAddress.sendKeys(address);
        return this;
    }

    @Step("Upload student's photo")
    public RegistrationPage fileUpLoad(String picture) {
        $("#uploadPicture").uploadFromClasspath(picture);
        return this;
    }
    @Step("Upload student's state")
    public RegistrationPage enterState(String state) {
        inputState.click();
        $("#stateCity-wrapper").$(byText(state)).click();
        return this;
    }

    @Step("Upload student's city")
    public RegistrationPage enterCity(String city) {
        inputCity.click();
        $("#stateCity-wrapper").$(byText(city)).click();
        return this;
    }

    @Step("Click submit button to send form")
    public RegistrationPage sendForm() {
        buttonSubmit.click();
        return this;
    }

    @Step("Check if invalid phone number is provided and red border is appeared on the field")
    public RegistrationPage checkInvalidPhoneNumber() {
        userNumberInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        return this;
    }


}
