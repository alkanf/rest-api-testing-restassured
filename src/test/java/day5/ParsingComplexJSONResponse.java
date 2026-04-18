package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class ParsingComplexJSONResponse { // We use response in multiple test.
	JSONObject getJSONResponse() throws FileNotFoundException {
		File file = new File(".\\src\\test\\resources\\complex.json");
		FileReader fileReader = null;
		try {
		fileReader = new FileReader(file);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONTokener jsonTokener = new JSONTokener(fileReader);
		JSONObject jsonResponse = new JSONObject(jsonTokener);
		 
		return jsonResponse; //You can either return string or jsonObject
	}

//User Details Validation
@Test
void userDetailsValidation() throws FileNotFoundException {
	//1)Long Version
	//JSONObject obj = getJSONResponse();        // 1
	//String jsonString = obj.toString();        // 2
	//JsonPath jsonPath = new JsonPath(jsonString); // 3}
	JsonPath jsonPath = new JsonPath(getJSONResponse().toString());
	
	//a) Verify the status field in the JSON response is "success".
	String status = jsonPath.getString("status");
	Assert.assertEquals(status, "success");
	
	//b) Validate the id, name, and email fields of the user.
	int id = jsonPath.getInt("data.userDetails.id");
	Assert.assertEquals(id, 12345);
	String name = jsonPath.getString("data.userDetails.name");
	Assert.assertEquals(name, "John Doe");
	String email = jsonPath.getString("data.userDetails.email");
	Assert.assertEquals(email, "john.doe@example.com");
	
	//c) Confirm the first phone number is of type home with the value 123-456-7890.
	String type = jsonPath.getString("data.userDetails.phoneNumbers[0].type");
    Assert.assertEquals(type, "home");
	String number = jsonPath.getString("data.userDetails.phoneNumbers[0].number");
	Assert.assertEquals(number, "123-456-7890");
	
	//d) Verify the geo coordinates of the user's address are latitude 39.7817 and longitude -89.6501.
	double latitude = jsonPath.getDouble("data.userDetails.address.geo.latitude");
	Assert.assertEquals(latitude, 39.7817);
	double longitude = jsonPath.getDouble("data.userDetails.address.geo.longitude");
	Assert.assertEquals(longitude, -89.6501);
	
	//e) Validate that the user has enabled notifications and is using the "dark" theme.
	boolean notifications = jsonPath.getBoolean("data.userDetails.preferences.notifications");
    Assert.assertTrue(notifications);
	String theme = jsonPath.getString("data.userDetails.preferences.theme");
    Assert.assertEquals(theme, "dark");
}
}