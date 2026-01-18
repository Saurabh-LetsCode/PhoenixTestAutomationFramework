package com.api.tests;

import com.api.pojo.UserCredentials;

import static com.api.utils.ConfigManager.*;

import com.api.utils.SpecUtil;
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

        given().
        spec(SpecUtil.requestSpec(userCredentials))
                .when()
                .post("login")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body("message",equalTo("Success"))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

    }
}
