package com.api.tests;

import com.api.pojo.UserCredentials;

import static com.api.utils.ConfigManager.*;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static  io.restassured.module.jsv.JsonSchemaValidator.*;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class LoginAPITest {

    @Test
    public void loginAPITest ( ) throws IOException {



        UserCredentials userCredentials = new UserCredentials("iamfd" , "password");

        given().baseUri(getProperty("BASE_URI"))
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
