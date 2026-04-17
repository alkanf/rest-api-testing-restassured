package day4;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUploadAndDownload {

@Test
void uploadSingleFile() {
File file = new File("C:\\Users\\alkan\\Desktop\\Proje 2\\Day5_file upload API\\single file upload.txt");
	

given()
       .multiPart(file)
       .contentType("multipart/form-data")
.when()
       .post("https://httpbin.org/post")
.then()
       .statusCode(200)
       .body("files.file", equalTo("single file upload"))
       .log().body();	
	
}
@Test
void downloadSingleFile() {
	

given() //this path param is dummy, wont work.
       .pathParam("fileName", "single file upload")
.when()
       .get("https://httpbin.org/{{fileName}}")
.then()
       .statusCode(200)
       .log().body();

	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
}
