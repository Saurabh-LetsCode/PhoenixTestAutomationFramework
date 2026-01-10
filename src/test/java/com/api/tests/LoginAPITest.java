package com.api.tests;

import com.api.pojo.UserCredentials;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static  io.restassured.module.jsv.JsonSchemaValidator.*;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class LoginAPITest {

    @Test
    public void loginAPITest ( ){

        UserCredentials userCredentials = new UserCredentials("iamfd" , "password");

        given().baseUri("http://64.227.160.186:9000/v1")
                .and()
                .contentType(ContentType.JSON)
                .and()
                .accept(ContentType.JSON)
                .and()
                .body(userCredentials)
                .log().uri()
                .log().method()
                .log().body()
                .log().headers()
                .when()
                .post("login")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(1000L))
                .and()
                .body("message",equalTo("Success"))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

    }
}
