package com.api.tests;

import  static io.restassured.RestAssured.*;

import static com.api.constant.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.net.Authenticator;

public class MasterAPITest {

    @Test
    public void verifyMasterAPIResponse(){

        given().baseUri(getProperty("BASE_URI"))
                .and()
                .headers("Authorization", getToken(FD))
                .and()
                .contentType(" ")
                .log().all()
                .when()
                .post("master")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("message",equalTo("Success"))
                .body("data",notNullValue())
                .body("data",hasKey("mst_oem"))
                .body("data",hasKey("mst_model"))
                .body("$",hasKey("message"))
                .body("$",hasKey("data"))
                .body("data.mst_oem.size()",equalTo(2))
                .body("data.mst_model.size()",greaterThan(0))
                .body("data.mst_oem.id",everyItem(notNullValue()))
                .body("data.mst_oem.name",everyItem(notNullValue()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));

    }

   @Test
    public void invalidTokenMasterAPITest(){

        given().baseUri(getProperty("BASE_URI"))
                .and()
                .headers("Authorization", " ")
                .and()
                .contentType(" ")
                .log().all()
                .when()
                .post("master")
                .then()
                .log().all()
                .statusCode(401);

    }
}
