package day2;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class ParametersDemo {

//@Test
void pathParams() {
	
	given()
	       .pathParam("country", "Turkey") //path parameter
	.when()
	       .get("https://restcountries.com/v2/name/{country}")
	.then()
	   .statusCode(200).log().body();
}
@Test
void queryParams() {
	given()
	.queryParam("page", 2)
	.queryParam("id", 5)
	.when()
	       .get("https://regres.in/api/users")
	.then()
	     .statusCode(200).log().body();
}
	
	
	
	
	
	
	
}
