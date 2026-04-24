package day8;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import org.testng.ITestContext;

public class GetUserTestContext {

    static final String BaseURL = "https://gorest.co.in/public/v2/users";
    static final String token = "1aaafe25fb21a47cf51c1a7f6cd43060bbbd6713f2b62ee6301b24108f0e66ae";

    @Test
    void getUser(ITestContext context) {
                     //Type casting as you set object
        int userID = (int) context.getAttribute("userID");

        given()
            .auth().oauth2(token)
            .pathParam("id", userID)
        .when()
            .get(BaseURL + "/{id}")
        .then()
            .statusCode(200)
            .log().body();
    }
}