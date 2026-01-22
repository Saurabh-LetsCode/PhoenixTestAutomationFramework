package com.api.tests;

import com.api.request.model.UserCredentials;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class LoginAPITest {

    private UserCredentials userCredentials;


    @BeforeMethod(description = "Create the payload for Login API")
    public void setUp(){
         userCredentials = new UserCredentials("iamfd" , "password");
    }

    @Test(description = "Verifying if Login API is working for FD User", groups = {"api","regression","smoke"})
    public void loginAPITest ( ) throws IOException {



        given().
        spec(requestSpec(userCredentials))
                .when()
                .post("login")
                .then()
                .spec(responseSpec_OK())
                .body("message",equalTo("Success"))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

    }
}
