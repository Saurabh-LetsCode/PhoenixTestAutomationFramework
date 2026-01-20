package com.api.tests;

import com.api.request.model.UserCredentials;

import com.api.utils.SpecUtil;
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
