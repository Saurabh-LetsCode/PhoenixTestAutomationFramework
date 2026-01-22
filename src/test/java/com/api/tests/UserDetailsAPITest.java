package com.api.tests;

import static com.api.utils.SpecUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

public class UserDetailsAPITest {

    @Test(description = "Verify if the userdetails API response is shown correctly",groups = {"api", "smoke" , "regression"})
    public void userDetailsAPITest() throws IOException {

        given()
                .spec(requestSpecWithAuth(FD))
                .when()
                .get("userdetails")
                .then()
                .spec(responseSpec_OK())
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
    }
}
