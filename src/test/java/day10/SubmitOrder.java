package day10;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class SubmitOrder {

    private static final String token = "c917cf8b1817c275480897bafcb1a2c6358a0f56d65c765353fbbd5364eb8780";
    private static final String baseURL = "https://simple-books-api.click";

    String orderId;

    @Test
    void submitOrder() {

        JSONObject data = new JSONObject();
        data.put("bookId", 1);
        data.put("customerName", "John");

        orderId =
                given()
                    .contentType("application/json")
                    .auth().oauth2(token)
                    .body(data.toString())
                .when()
                    .post(baseURL + "/orders")
                .then()
                    .log().body()
                    .statusCode(201)
                    .extract().jsonPath().getString("orderId");
    }

    @Test(dependsOnMethods = "submitOrder")
    void deleteOrder() {

        given()
            .auth().oauth2(token)
            .pathParam("orderId", orderId)
        .when()
            .delete(baseURL + "/orders/{orderId}")
        .then()
            .log().body()
            .statusCode(204);
    }
}