package data;

import com.github.javafaker.Faker;

import java.util.Locale;

import static data.TestData.State.*;

public class TestData {


    enum State {

        NCR("NCR") {
            String setCity() {
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            }
        },
        UTTAR_PRADESH("Uttar Pradesh") {
            String setCity() {
                return faker.options().option("Agra", "Lucknow", "Merrut");
            }
        },
        HARYANA("Haryana") {
            String setCity() {
                return faker.options().option("Karnal", "Panipat");
            }
        },
        RAJASTHAN("Rajasthan") {
            String setCity() {
                return faker.options().option("Jaipur", "Jaiselmer");
            }

        };

        abstract String setCity();

        private String state;

        State(String state) {
            this.state = state;
        }

    }

    private State stateEnum = faker.options().option(NCR, UTTAR_PRADESH, HARYANA, RAJASTHAN);

    private static final Faker faker = new Faker(new Locale("EN"));
    private final Faker fakerEU = new Faker(new Locale("eu-US"));

    public String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = fakerEU.internet().emailAddress(),
            phoneNumber = faker.phoneNumber().subscriberNumber(10),
            wrongPhoneNumber = faker.phoneNumber().subscriberNumber(4),
            gender = faker.options().option("Male", "Female", "Other"),
            yearBirth = String.valueOf(faker.number().numberBetween(1960, 1999)),
            month = faker.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"),
            dayOfBirth = String.format("%02d", faker.number().numberBetween(1, 28)),
            subjects = faker.options().option("Arts", "Maths", "Hindi"),
            hobbies = faker.options().option("Sports", "Reading", "Music"),
            currentAddress = faker.address().fullAddress(),
            state = stateEnum.state,
            city = stateEnum.setCity();


//
//    public static String setRandomCity(String state) {
//        Faker faker = new Faker();
//       return switch (state) {
//            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
//            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
//            case "Haryana" -> faker.options().option("Karnal", "Panipat");
//            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
//             default -> throw new IllegalArgumentException("Invalid state: " + state);
//        };
//    }


}
