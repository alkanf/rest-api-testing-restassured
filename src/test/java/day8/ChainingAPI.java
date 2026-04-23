package day8;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;
import com.github.javafaker.*;
public class ChainingAPI {
static final String BaseURL = "https://gorest.co.in/public/v2/users";
static final String token = "1aaafe25fb21a47cf51c1a7f6cd43060bbbd6713f2b62ee6301b24108f0e66ae";
int userID;
Faker faker = new Faker();


@Test
void createNewUser() {
JSONObject jsonData = new JSONObject();
Faker faker = new Faker();
jsonData.put("name", faker.name().fullName());
jsonData.put("gender", "male");
jsonData.put("email", faker.internet().emailAddress());
jsonData.put("status", "inactive");
userID = given()
       .auth().oauth2(token)
       .contentType("application/json")
       .body(jsonData.toString())
.when()
       .post(BaseURL)
.then()
       .statusCode(201)
       .log().body()
       .extract().response().jsonPath().getInt("id");
}
@Test(dependsOnMethods = "createNewUser")
void getUser() {

       given()
       .auth().oauth2(token)
       .pathParam("id", userID)
       
.when()
       .get(BaseURL + "/{id}")
.then()
       .statusCode(200)
       .log().body();       
}
@Test(dependsOnMethods = "getUser")
void updateUser() {
JSONObject jsonData = new JSONObject();
jsonData.put("name", faker.name().fullName());
jsonData.put("gender", "female");
jsonData.put("email", faker.internet().emailAddress());
jsonData.put("status", "active");
given()
       .auth().oauth2(token)
       .contentType("application/json")
       .body(jsonData.toString())
       .pathParam("id", userID)
.when()
       .put(BaseURL + "/{id}")
.then()
       .statusCode(200)
       .log().body();
}
@Test(dependsOnMethods = "updateUser")
void deleteUser() {

given()
       .auth().oauth2(token)
       .contentType("application/json")
       .pathParam("id", userID)
.when()
       .delete(BaseURL + "/{id}")
.then()
       .statusCode(204)
       .log().body();
}








}
