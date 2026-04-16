package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthenticationTest {
      //1. Basic Authentication
	//@Test
	void verifyBasicAuth() {
    given()
           .auth().basic("postman", "password")
    .when()
           .get("https://postman-echo.com/basic-auth")
    
    .then()
            .statusCode(200).body("authenticated", equalTo(true)).log().body();
}
      //2. Basic Preemptive Authentication
		//@Test
		void verifyPreemptiveBasicAuth() {
	    given()
	           .auth().preemptive().basic("postman", "password")
	    .when()
	           .get("https://postman-echo.com/basic-auth")
	    
	    .then()
	            .statusCode(200).body("authenticated", equalTo(true)).log().body();
	}
		//3. Digestive Authentication
				//@Test
				void verifyDigestiveAuth() {
			    given()
			           .auth().digest("postman", "password")
			    .when()
			           .get("https://postman-echo.com/basic-auth")
			    
			    .then()
			            .statusCode(200).body("authenticated", equalTo(true)).log().body();
			}
				// 4. Bearer Authentication
			    //@Test
			    void verifyBearerAuth() {
			        String sampleToken = "bearer_sample_token_eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkFsa2FuIn0";

			        given()
			            .auth().oauth2(sampleToken) 
			        .when()
			            .get("https://httpbin.org/bearer")
			        .then()
			            .statusCode(200)
			            .body("authenticated", equalTo(true))
			            .log().body();
			    }
			    
				// 4. API Key Authentication
			    @Test
			    void verifyAPIKeyAuth() {
			        String sampleToken = "bearer_sample_token_eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkFsa2FuIn0";

			        given()
			             .queryParam("q", "İstanbul")
			             .queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
			        .when()
			            .get("https://api.openweathermap.org/data/2.5/weather")
			        .then()
			            .statusCode(200)
			            .body("city", equalTo("İstanbul"))
			            .log().body();
			    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

