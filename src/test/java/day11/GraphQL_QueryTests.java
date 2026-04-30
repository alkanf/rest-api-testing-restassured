package day11;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GraphQL_QueryTests {

    private static final String BASE_URL = "https://hasura.io/learn/graphql";
    private static final String AUTH_TOKEN = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsInR5cCI6IkpXVCIs";

    // Fetch Users and Their Todos
    @Test(priority = 1)
    public void testFetchUsersAndTodos() {

        String graphqlQuery = "{\r\n"
                + "\"query\": \"query { users { name todos { title } } }\"\r\n"
                + "}";

        given()
            .contentType(ContentType.JSON)
            .header("Authorization", AUTH_TOKEN)
            .body(graphqlQuery)

        .when()
            .post(BASE_URL)

        .then()
            .statusCode(200)
            .body("data.users", hasSize(greaterThan(0)))
            .body("data.users[0].name", notNullValue());
    }
}