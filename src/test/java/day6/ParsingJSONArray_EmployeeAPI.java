package day6;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;


public class ParsingJSONArray_EmployeeAPI {
@Test
void testJSONResponseBody() {
ResponseBody response = given()

.when()
       .get("http://localhost:3000/employees")
.then()
       .log().body()
       .extract().response().body();
 
JsonPath jsonpath = new JsonPath(response.asString());

//Get the size of the JSON array
int employeeCount=jsonpath.getInt("size()");
//Print all the details of the employee
for(int i=0;i<employeeCount;i++) {
String firstName=jsonpath.getString("["+i+"].first_name");
String lastName=jsonpath.getString("["+i+"].last_name");
String email=jsonpath.getString("["+i+"].email");
String gender=jsonpath.getString("["+i+"].gender");
System.out.println(firstName + " " + lastName + " " + email + " " + gender);

}
//Search for emplooye name "Steve" in the list
boolean status = false;
for(int i=0;i<employeeCount;i++) {
String firstName=jsonpath.getString("["+i+"].first_name");
if(firstName.equals("Steve"))
		{
	status=true;
	break;
		}}
Assert.assertTrue(status);
}
}

