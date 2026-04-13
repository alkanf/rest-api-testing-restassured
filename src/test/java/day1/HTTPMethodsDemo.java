package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*; //manually import
import static org.hamcrest.Matchers.*; //manually import

import java.util.HashMap;

import static io.restassured.matcher.RestAssuredMatchers.*; //manually import

public class HTTPMethodsDemo {
int userId; //make it global for made accessible for any request
	
@Test(priority=1)
void getAllProducts() {
given() //no pre condition
.when()
  .get("https://fakestoreapi.com/products")
.then()
  .statusCode(200)
  .time(lessThan(10000L))
  .body(containsString("id"))
  .header("Content-Type",equalTo("application/json; charset=utf-8"))
  .log().all();
}

@Test(priority=2)
void addProduct() {
	HashMap<String, Object> data = new HashMap<String, Object>();
    data.put("id", 1); //Request body and different data types so we use object
    data.put("title", "added product");
    data.put("price", 0.1);
    data.put("description", "Description");
    data.put("category", "electronic");
    data.put("image", "http://example.com");
userId=given() //need to define before given
  .contentType("application/json")
  .body(data)
.when()
  .post("https://fakestoreapi.com/products")
.then()
  .statusCode(201)
  .time(lessThan(10000L))
  .body(containsString("id"))
  .header("Content-Type",equalTo("application/json; charset=utf-8"))
  .body("description", equalTo("Description")) //response body validation
  .body("title", equalTo("added product"))
  .log().all()
  .extract().jsonPath().getInt("id"); //dynamic chain request
}

@Test(priority=3, dependsOnMethods={"addProduct"})
void updateProduct() {
	HashMap<String, Object> data = new HashMap<String, Object>();
    data.put("id", 1); //Request body and different data types so we use object
    data.put("title", "update");
    data.put("price", 0.1);
    data.put("description", "update");
    data.put("category", "electronic");
    data.put("image", "http://example.com");
given()
  .contentType("application/json")
  .body(data)
.when()
  .put("https://fakestoreapi.com/products/"+userId )
.then()
  .statusCode(200)
  .time(lessThan(10000L))
  .body(containsString("id"))
  .header("Content-Type",equalTo("application/json; charset=utf-8"))
  .body("title", equalTo("update")) //response body validation
  .body("description", equalTo("update")) //response body validation


  .log().all();
}

@Test(priority=4, dependsOnMethods={"addProduct","updateProduct"})
void deleteProduct() {
given()
.when()
  .delete("https://fakestoreapi.com/products/"+userId )
.then()
  .statusCode(200)
  .time(lessThan(10000L))
  .body(blankOrNullString())
  .header("Content-Type",equalTo("application/json; charset=utf-8"))
  .log().all();
}



















}
