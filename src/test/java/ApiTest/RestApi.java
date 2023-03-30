package ApiTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestApi {
    // Get Tests
    @Test(testName = "Get List Users")
    public void GetListUsersApi(){
        given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200)
                .log().body();
    }

    @Test(testName = "Get Single User")
    public void GetSingleUser() {
        given().when().get("https://reqres.in/api/users/2").then().statusCode(200)
                .log().body();
    }

    @Test(testName= "Get Single User Not Found")
    public void GetSingleUserNotFound(){
        given().when().get("https://reqres.in/api/users/23").then().statusCode(404)
                .log().body();
    }

    @Test(testName = "Get List <RESOURCE>")
    public void GetListResource(){
        given().when().get("https://reqres.in/api/unknown").then().statusCode(200)
                .log().body();
    }

    @Test(testName = "Get Single <RESOURCE>")
    public void GetSingleResource(){
        given().when().get("https://reqres.in/api/unknown/2").then().statusCode(200)
                .log().body();
    }

    @Test(testName = "Get List <RESOURCE> NOT FOUND")
    public void GetListResourceNotFound(){
        given().when().get("https://reqres.in/api/unknown/23").then().statusCode(404)
                .log().body();
    }

    @Test(testName = "Get Delayed Response")
    public void GetDelayedResponse(){
        given().when().get("https://reqres.in/api/users?delay=3").then().statusCode(200)
                .log().body();
    }

    // Post Tests >>
    @Test(testName = "Post Create")
    public void PostCreate(){
        HashMap payload = new HashMap();
        payload.put("name","Said");
        payload.put("job","IT");

        Response res = given().contentType("application/json").body(payload)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().body().extract().response();

        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("name"),true);

    }
    @Test(testName = "Post Register Successful")
    public void PostRegisterSuccessful(){
        HashMap payload = new HashMap();
        payload.put("email","eve.holt@reqres.in");
        payload.put("password", "pistol");

        Response res = given().contentType("application/json").body(payload)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .log().body().extract().response();
        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("token"), true);
    }

    @Test(testName = "Post Register UnSuccessful")
    public void PostRegisterUnSuccessful(){
        HashMap payload = new HashMap();
        payload.put("email","sydney@fife");

        Response res = given().contentType("application/json").body(payload)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .log().body().extract().response();
        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("error"), true);
    }

    @Test(testName = "Post Login Successful")
    public void PostLoginSuccessful(){
        HashMap payload = new HashMap();
        payload.put("email","eve.holt@reqres.in");
        payload.put("password","cityslicka");

        Response res = given().contentType("application/json").body(payload)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .log().body().extract().response();
        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("token"), true);
    }

    @Test(testName = "Post Login UnSuccessful")
    public void PostLoginUnSuccessful(){
        HashMap payload = new HashMap();
        payload.put("email","peter@klaven");

        Response res = given().contentType("application/json").body(payload)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .log().body().extract().response();
        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("error"), true);
    }

    @Test(testName = "Put Update")
    public void PutUpdate(){
        HashMap payload = new HashMap();
        payload.put("name", "morpheus");
        payload.put("job", "zion resident");

        given().contentType("application/json").body(payload)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().body().body("name", equalTo("morpheus"));

    }

    @Test(testName = "Patch Update")
    public void PatchUpdate(){
        HashMap payload = new HashMap();
        payload.put("name", "morpheus");
        payload.put("job", "zion resident");

        given().contentType("application/json").body(payload)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().body().body("name", equalTo("morpheus"));

    }

    @Test(testName = "Delete")
    public void Delete(){
        given()
                .when()
                .delete("https://reqres.in/api/users/2").then().statusCode(204)
                .log().body();
    }
}
