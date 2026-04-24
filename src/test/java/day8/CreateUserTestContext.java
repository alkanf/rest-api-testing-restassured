package day8;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import com.github.javafaker.Faker;

public class CreateUserTestContext {

    static final String BaseURL = "https://gorest.co.in/public/v2/users";
    static final String token = "1aaafe25fb21a47cf51c1a7f6cd43060bbbd6713f2b62ee6301b24108f0e66ae";

    Faker faker = new Faker();

    @Test
    void createNewUser(ITestContext context) {

        JSONObject jsonData = new JSONObject();
        jsonData.put("name", faker.name().fullName());
        jsonData.put("gender", "male");
        jsonData.put("email", faker.internet().emailAddress());
        jsonData.put("status", "inactive");

        int userID =
        given()
            .auth().oauth2(token)
            .contentType("application/json")
            .body(jsonData.toString())
        .when()
            .post(BaseURL)
        .then()
            .statusCode(201)
            .log().body()
            .extract().jsonPath().getInt("id");

        // 🔥 context' as they are in different class
        context.setAttribute("userID", userID);
    }
}