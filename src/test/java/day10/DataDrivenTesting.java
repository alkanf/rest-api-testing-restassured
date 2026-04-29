package day10;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class DataDrivenTesting {

    private static final String token = "c917cf8b1817c275480897bafcb1a2c6358a0f56d65c765353fbbd5364eb8780";
    private static final String baseURL = "https://simple-books-api.click";
    String orderId;
    
    @Test(dataProvider="excelDataProvider", dataProviderClass=DataProviders.class)
    public void testWithExcelData(String bookId, String customerName) {
    submitOrder(bookId, customerName);
    
    }
    
    
    @Test(dataProvider="jsonDataProvider", dataProviderClass=DataProviders.class)
    public void testWithJSONData(Map<String,String> data) {
    submitOrder(data.get("BookID"), data.get("CustomerName")); //Must change names as we are retrieving from json
    }
    
    
    @Test(dataProvider="csvDataProvider", dataProviderClass=DataProviders.class)
    public void testWithCSVData(String bookId, String customerName) {
    submitOrder(bookId, customerName);
    }

    public void submitOrder(String bookId, String customerName) {

        JSONObject data = new JSONObject();
        data.put("bookId", 	Integer.parseInt(bookId)); //because we are retrieving from excell and its string
        data.put("customerName", customerName);

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