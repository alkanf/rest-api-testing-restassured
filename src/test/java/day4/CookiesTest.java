package day4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;
import io.restassured.response.Response;


public class CookiesTest {

@Test
void testCookiesInResponse() {
	
Response response = given()

.when()
       .get("https://www.google.com")

.then()
       .log().cookies()
       .statusCode(200)
       .cookie("AEC", notNullValue())
       .extract().response(); // Extract Response


// Printing & Getting specific cookie value
String specificCookie1 = response.getCookie("AEC");
String specificCookie2 = response.getCookie("NID");
Map<String,String> allCookies = response.getCookies(); //map = key + value

System.out.println("AEC Value is: " + specificCookie1);
System.out.println("NID Value is: " + specificCookie2);
System.out.println(allCookies);

for(String cookie : allCookies.keySet()) {
	System.out.println(cookie + ": " +allCookies.get(cookie)); //we need value also not only key
}


//Detailed Information
Cookie cookie_info = response.getDetailedCookie("AEC");
System.out.println(cookie_info.hasExpiryDate());
System.out.println(cookie_info.getExpiryDate());
System.out.println(cookie_info.hasValue());
System.out.println(cookie_info.getValue());
System.out.println(cookie_info.isSecured());



	
}

}
