package day3;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class OAuthSample {

    @Test
    void verifyOAuth2Authentication() {

        String clientId = "dummyclientID2323";
        String clientSecret = "dummyclientSecret34234";
        String redirectUri = "https://www.getpostman.com/oauth2/callback";
        String grantType = "authorization_code";
        String authorizationCode = "insertAuthCodeFromPostman";

        //1) Take Token
        String token = given()
                .header("Accept", "application/json")
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret)
                .formParam("grant_type", grantType)
                .formParam("code", authorizationCode)
                .formParam("redirect_uri", redirectUri)
        .when()
                .post("https://api.imgur.com/oauth2/token")
        .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("access_token");

        System.out.println("Generated token: " + token);

        // Part 2) Use token in request
        given()
                .auth().oauth2(token)
        .when()
                .get("https://api.imgur.com/3/account/me")
        .then()
                .statusCode(200)
                .log().body();
    }
}