package day2;

//manually import
import static io.restassured.RestAssured.given;
//manually import
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*Different ways to create body
 * 1)HashMap
 * 2)org.json library
 * 3)POJO class
 * 4)External JSON fİle
 */
public class PostRequestBodyExamples {
String studentID; //make accessible
@Test
void createRequestBodyHashMap() {
HashMap<String, Object> data = new HashMap<String, Object>();
data.put("name", "HashMap");
data.put("location", "England");
data.put("phone", "4427732664");
String arrayData[] = {"Unity", "Blender"};
data.put("courses", arrayData); //cant use array values directly

studentID = given()
 .contentType("application/json").body(data)
.when().post("http://localhost:3000/students")
.then()
  .statusCode(201)
  .body("name",equalTo("HashMap"))
  .body("location",equalTo("England"))
  .body("phone",equalTo("4427732664"))
  .body("courses[0]",equalTo("Unity"))
  .body("courses[1]",equalTo("Blender"))
  .header("Content-Type","application/json")
  .log().body() //response body
  .extract().jsonPath().getString("id");

}
@AfterMethod
void deleteStudent() {
given()
.when().delete("http://localhost:3000/students/" + studentID)
.then().log().body();
}







}
	
	
	
	
	
	
	
	
	
	


