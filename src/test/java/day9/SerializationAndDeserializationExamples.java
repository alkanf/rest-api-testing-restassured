package day9;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
public class SerializationAndDeserializationExamples {
int stuID;
@Test
public void testSerialization() { //Java object / Pojo -> JSON
String courses[] = {"Java", "C+"};
POJO pj = new POJO("Ahmet", "İstanbul", "5423423234", courses);
	int stuID = given()
	       .contentType("application/json")
	       .body(pj) //Cant send directly pojo/java object, must specify content type. This is serialization
	.when()
	       .post()
	.then()
	       .statusCode(201)
	       .log().body()
	       .extract().response().jsonPath().getInt("id");
}
	
@Test(dependsOnMethods="testSerialization")
public void testDEserialization() { //Java object / Pojo -> JSON

	given()
	       .pathParam("id", stuID)
	.when()
	       .get()
	.then()
	       .statusCode(201)
	       .log().body();
}
	
	
	
}
