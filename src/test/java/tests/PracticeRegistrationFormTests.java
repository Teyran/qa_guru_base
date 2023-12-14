package tests;

import data.TestData;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.ResultComponent;

public class PracticeRegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    ResultComponent resultComponent = new ResultComponent();

    TestData data = new TestData();

    @Test
    void fillFormTest() {

        registrationPage
                .openPage()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setEmailInput(data.email)
                .setGenderWrapper(data.gender)
                .setUserNumber(data.phoneNumber)
                .setDateOfBirth(data.month, data.dayOfBirth, data.yearBirth)
                .setSubject(data.subjects)
                .setHobbies(data.hobbies)
                .fileUpLoad("picture.png")
                .enterCurrentAddress(data.currentAddress)
                .enterState(data.state)
                .enterCity(data.city)
                .sendForm();

        resultComponent
                .checkResult("Student Name", data.firstName.concat(" " + data.lastName))
                .checkResult("Student Email", data.email)
                .checkResult("Gender", data.gender)
                .checkResult("Mobile", data.phoneNumber)
                .checkResult("Date of Birth", data.dayOfBirth + " " + data.month + "," + data.yearBirth)
                .checkResult("Subjects", data.subjects)
                .checkResult("Hobbies", data.hobbies)
                .checkResult("Picture", "picture.png")
                .checkResult("Address", data.currentAddress)
                .checkResult("State and City", data.city);
    }

    @Test
    void fillFormWithRequiredFieldsTest() {

        registrationPage.openPage()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setUserNumber(data.phoneNumber)
                .setGenderWrapper(data.gender)
                .setDateOfBirth(data.month, data.dayOfBirth, data.yearBirth)
                .sendForm();

        resultComponent
                .checkResult("Student Name", data.firstName.concat(" " + data.lastName))
                .checkResult("Gender", data.gender)
                .checkResult("Mobile", data.phoneNumber)
                .checkResult("Date of Birth", data.dayOfBirth + " " + data.month + "," + data.yearBirth);
    }

    @Test
    void invalidPhoneNumberTest() {

        registrationPage.openPage()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setUserNumber(data.wrongPhoneNumber)
                .setGenderWrapper(data.gender)
                .setDateOfBirth(data.month, data.dayOfBirth, data.yearBirth)
                .sendForm()
                .checkInvalidPhoneNumber();
    }
}

