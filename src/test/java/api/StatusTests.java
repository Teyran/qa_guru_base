package api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasKey;

public class StatusTests {

    @Test
    public void checkTotal() {

        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20))
                .body("browsers.chrome" ,hasKey(100.0));
    }

    @Test
    public void checkTotalWithSomeLogs() {

        given()
                .when()
                .log().uri()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .statusCode(200)
                .body("total", is(20));
    }
}
