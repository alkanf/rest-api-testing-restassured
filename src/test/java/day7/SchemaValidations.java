package day7;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;
public class SchemaValidations {
@Test
void testJSONSchema() {
given()

.when()
       .get("https://mocktarget.apigee.net/json")
.then()
       .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema.json"));
}

}
