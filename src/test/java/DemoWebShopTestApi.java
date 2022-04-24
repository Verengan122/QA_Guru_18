import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DemoWebShopTestApi {
    @Test
    void SubscriptionWithInvalidMail() {
        given()
                .formParam("email", "RedCat12")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .post("http://demowebshop.tricentis.com/subscribenewsletter")
                .then()
                .statusCode(200)
                .body("Result", Matchers.is("Enter valid email"))
                .body("Success", Matchers.is(false));
    }

    @Test
    void SubscriptionWithTrueMail() {
        given()
                .formParam("email", "RedCat12@mail.ru")
                .when()
                .post("http://demowebshop.tricentis.com/subscribenewsletter")
                .then()
                .log().all()
                .statusCode(200)
                .body("Result", Matchers.is("Thank you for signing up! A verification email has been sent. We appreciate your interest."))
                .body("Success", Matchers.is(true));
    }
}
