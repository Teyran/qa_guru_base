package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.ResultComponent;

public class PracticeRegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    ResultComponent resultComponent = new ResultComponent();

    @Test
    void fillFormTest() {

        registrationPage
                .openPage()
                .setFirstName("Teyran")
                .setLastName("Atamov")
                .setEmailInput("teyranAtamov@mymail.com")
                .setGenderWrapper("Male")
                .setUserNumber("1234567890")
                .setDateOfBirth("May", "11", "1993" )
                .setSubject("Physics")
                .setHobbies("Sports", "Reading")
                .fileUpLoad()
                .enterCurrentAddress("Some address")
                .enterState("Haryana")
                .enterCity("Karnal")
                .sendForm();

        resultComponent.checkResult("Student Name", "Teyran Atamov")
                .checkResult("Student Email", "teyranAtamov@mymail.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "1234567890")
                .checkResult("Date of Birth", "11 May,1993")
                .checkResult("Subjects", "Physics")
                .checkResult("Hobbies", "Sports, Reading")
                .checkResult("Picture", "picture.png")
                .checkResult("Address", "Some address")
                .checkResult("State and City", "Haryana Karnal");
    }
    @Test
    void fillFormWithRequiredFieldsTestf() {
        registrationPage.openPage()
                .setFirstName("Hello")
                .setLastName("World")
                .setUserNumber("0123456789")
                .setGenderWrapper("Male")
                .setDateOfBirth("December","15","1992")
                .sendForm();
        resultComponent.checkResult("Student Name", "Hello World")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "0123456789")
                .checkResult("Date of Birth", "15 December,1992");
    }

    @Test
    void invalidPhoneNumberTest() {
        registrationPage.openPage()
                .setFirstName("Hello")
                .setLastName("World")
                .setUserNumber("012345")
                .setGenderWrapper("Male")
                .setDateOfBirth("December","15","1992")
                .sendForm()
                .checkInvalidPhoneNumber();
    }
}

