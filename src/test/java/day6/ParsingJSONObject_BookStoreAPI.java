package day6;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;


public class ParsingJSONObject_BookStoreAPI {
@Test
void testJSONResponseBody() {
ResponseBody response = given()

.when()
       .get("http://localhost:3000/employees")
.then()
       .log().body()
       .extract().response().body();
 
JsonPath jsonpath = new JsonPath(response.asString());

//Get the size of the JSON array
int bookCount=jsonpath.getInt("book.size()");
//validate at least one book should be in the store
assertEquals(bookCount, greaterThan(0));
//Print all the titles of books in a store
for(int i=0; i<bookCount;i++) {
String bookTitle = jsonpath.getString("book[i].title");
System.out.println(bookTitle);
}
//Search for a book
boolean status = false;
for(int i=0; i<bookCount;i++) {
String bookName = jsonpath.getString("book["+i+"].name");
if(bookName.equals("The Lord of the Rings")) {
	status = true;
    break;
}
}
Assert.assertTrue(status);
//Calculate and validate Total price of books
double totalPrice=0;
for(int i=0; i<bookCount;i++) {
double bookPrice = jsonpath.getDouble("book["+i+"].price");
totalPrice=totalPrice+bookPrice; }
System.out.println("Total Price of books" + totalPrice);
Assert.assertEquals(totalPrice, 53.92);
}












}


