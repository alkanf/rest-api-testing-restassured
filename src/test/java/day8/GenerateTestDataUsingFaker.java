package day8;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;
import com.github.javafaker.*;
public class GenerateTestDataUsingFaker {
   @Test
	void fakeDataGenerator() {
	 Faker faker = new Faker();
	 String Firstname = faker.name().firstName();
	 String Lastname = faker.name().lastName();
	 String email = faker.internet().emailAddress();
	 String password = faker.internet().password();
	 String phoneNumber = faker.phoneNumber().cellPhone();
	 
   }
	


}
