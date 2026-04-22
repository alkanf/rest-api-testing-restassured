package day7;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
public class ParsingXML {
//@Test
void textXMLResponse() {
given()

.when()
       .get("https://mocktarget.apigee.net/xml")

.then()
       .statusCode(200)
       .header("Content-Type", "application/xml; charset=utf-8")
       .contentType("application/xml")
       .body("root.city", equalTo("San Jose"))
       .body("root.firstName", equalTo("John"))
       .body("root.lastName", equalTo("Doe"))
       .body("root.state", equalTo("CA"))
       .log().body();
}

//@Test
void textXMLResponse2() {
given()

.when()
       .get("https://httpbin.org/xml")

.then()
       .statusCode(200)
       .header("Content-Type", "application/xml")
       .contentType("application/xml")
       .body("slideshow.@title", equalTo("Sample Slide Show"))
       .body("slideshow.slide[1].title", equalTo("Overview"))
       .log().body();
}
	
@Test
void textXMLResponse3() {
Response response =given()

.when()
       .get("https://httpbin.org/xml")

.then()
       .statusCode(200)
       .contentType("application/xml")
       .log().body()
       .extract().response();

XmlPath xmlPath = new XmlPath(response.asString());
//Number of slider in response
List<String>slideTitle = xmlPath.getList("slideshow.slide.title");
//Counting slider
Assert.assertEquals(slideTitle.size(), 2);
//Validate slide titles equalTo() is deleted as value has ""
Assert.assertEquals(slideTitle.get(0), "Wake up to WonderWidgets!");
//specific title
Assert.assertEquals(slideTitle.get(1), "Overview");

//multiple titles (contains)
Assert.assertTrue(slideTitle.contains("Wake up to WonderWidgets!"));
Assert.assertTrue(slideTitle.contains("Overview"));

//number of items
List<String> items = xmlPath.getList("slideshow.slide.item");
System.out.println("Number of Items : " + items.size());
Assert.assertEquals(items.size(), 3);

//validate items data
Assert.assertEquals(items.get(0), "WonderWidgets");
Assert.assertEquals(items.get(2), "buys");

//Specific item
boolean status = false;
for(String item : items) {
	if(item.contains("WonderWidgets")) {
		status = true;
		break;
	}
 Assert.assertTrue(status);
}










}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
