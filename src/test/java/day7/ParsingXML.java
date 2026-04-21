package day7;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;
import org.testng.annotations.Test;
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

@Test
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
