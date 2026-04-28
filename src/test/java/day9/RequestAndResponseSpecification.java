package day9;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
/*
* `http://localhost:3000/employees`
* `http://localhost:3000/employees/1`
* `http://localhost:3000/employees?last_name=King`
* `http://localhost:3000/employees?first_name=Steven`
* `http://localhost:3000/employees?gender=Female`
* `http://localhost:3000/employees?gender=Male`
 */
public class RequestAndResponseSpecification {
RequestSpecification httpRequest;
ResponseSpecification httpResponse;

@BeforeClass
public void setup() {
//Create request Specification
RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
reqBuilder.setBaseUri("http://localhost:3000");
reqBuilder.setBasePath("/employees");
httpRequest = reqBuilder.build();

//Create response Specification
ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
resBuilder.expectStatusCode(200);
resBuilder.expectHeader("Content-Type", equalTo("applicaton/json"));
resBuilder.log(io.restassured.filter.log.LogDetail.BODY);
httpResponse = resBuilder.build();
}
@Test
void getAllEmployees() {
given()

.when()
       .spec(httpRequest)
.then()
       .body("size()", greaterThan(0))
       .spec(httpResponse);

}
@Test
void getMaleEmployees() {
given()
       .queryParam("gender", "Male")
.when()
       .spec(httpRequest)
.then()
       .body("gender", everyItem(equalTo("Male")))
       .spec(httpResponse);
}
@Test
void getFemaleEmployees() {
given()
       .queryParam("gender", "Female")
.when()
       .spec(httpRequest)
.then()
       .body("gender", everyItem(equalTo("Female")))
       .spec(httpResponse);

}
}
