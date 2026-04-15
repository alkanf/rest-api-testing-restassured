package day2;

//manually import
import static io.restassured.RestAssured.given;
//manually import
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
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
//@Test
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

//@Test
void createRequestBodyJSONLibrary() {
JSONObject data = new JSONObject();
data.put("name", "HashMap");
data.put("location", "England");
data.put("phone", "4427732664");
String arrayData[] = {"Unity", "Blender"};
data.put("courses", arrayData); //cant use array values directly

studentID = given()
 .contentType("application/json").body(data.toString())
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

//@Test
void createRequestBodyPOJOClass() {
StudentPOJO data = new StudentPOJO();
data.setName("Ethan");
data.setPhone("1918237231");
data.setLocation("Irland");
String arrayValue[] = {"Unity", "Blender"}; data.setCourses(arrayValue); //Cant pass array directly

studentID = given()
.contentType("application/json").body(data)
.when().post("http://localhost:3000/students")
.then()
.statusCode(201)
.body("name",equalTo(data.getName()))
.body("location",equalTo(data.getLocation()))
.body("phone",equalTo(data.getPhone()))
.body("courses[0]",equalTo(data.courses[0]))
.body("courses[1]",equalTo(data.courses[1]))
.header("Content-Type","application/json")
.log().body() //response body
.extract().jsonPath().getString("id");

}

@Test
void createRequestBodyExternalJSON() throws FileNotFoundException {
File data = new File(".\\src\\test\\java\\day2\\externalBody.json");
FileReader responseData = new FileReader(data);
JSONTokener jsonToken = new JSONTokener(responseData); //as we cant read directly, use JSONTokener class
JSONObject jsonObject = new JSONObject(jsonToken); //convert it to json
	
studentID = given()
.contentType("application/json").body(jsonObject.toString()) //dont forget to convert to string
.when().post("http://localhost:3000/students")
.then()
.statusCode(201)
.body("name",equalTo("Scott"))
.body("location",equalTo("France"))
.body("phone",equalTo("123456"))
.body("courses[0]",equalTo("C"))
.body("courses[1]",equalTo("C++"))
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
	
	
	
	
	
	
	
	
	
	


