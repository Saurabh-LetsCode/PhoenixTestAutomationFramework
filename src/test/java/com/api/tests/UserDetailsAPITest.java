package com.api.tests;

import static io.restassured.RestAssured.*;



import static  com.api.utils.ConfigManager.*;

import static  com.api.utils.AuthTokenProvider.*;

import static com.api.constant.Role.*;

import com.api.constant.Role;
import com.api.utils.SpecUtil;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import static org.hamcrest.Matchers.*;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserDetailsAPITest {

    @Test
    public void userDetailsAPITest() throws IOException {

        given()
                .spec(SpecUtil.requestSpecWithAuth(FD))
                .when()
                .get("userdetails")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
    }
}
