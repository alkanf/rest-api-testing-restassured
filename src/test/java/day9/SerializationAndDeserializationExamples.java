package day9;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
public class SerializationAndDeserializationExamples {
int stuID;
@Test
public void testSerialization() { //Java object / Pojo -> JSON
String courses[] = {"Java", "C+"};
POJO pj = new POJO("Ahmet", "İstanbul", "5423423234", courses);
	 stuID = given()
	       .contentType("application/json")
	       .body(pj) //Cant send directly pojo/java object, must specify content type. This is serialization
	.when()
	       .post("http://localhost:3000/students")
	.then()
	       .statusCode(201)
	       .log().body()
	       .extract().response().jsonPath().getInt("id");
}
	
@Test(dependsOnMethods="testSerialization")
public void testDeserialization() { //Java object / Pojo -> JSON

	Response response = given()
	       .pathParam("id", stuID)
	.when()
	       .get("http://localhost:3000/students/{id}")
	.then()
	       .statusCode(200)
	       .extract().response();
	//Extract "id" separetly as its additional 
	String extractedID = response.jsonPath().getString("id");
	//Deserialization - convert JSON to Student Object
	POJO pojo = response.as(POJO.class); //Must have same value, but response has additional id variable
	//but its impossible so we should ignore in pojo class
	//you can do validation on java object
	Assert.assertEquals(pojo.getName(), "Ahmet");
	System.out.println("POJO details " +pojo + extractedID);
	
	
	
}
}
