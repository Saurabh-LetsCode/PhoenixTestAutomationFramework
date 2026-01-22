package com.api.tests;

import static com.api.utils.SpecUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MasterAPITest {

    @Test(description = "Verifying if Master API is giving correct response", groups = {"api","regression","smoke"})
    public void verifyMasterAPIResponse(){

        given()
                .spec(requestSpecWithAuth(FD))
                .when()
                .post("master")
                .then()
                .spec(responseSpec_OK())
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
                .body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));

    }

   @Test(description = "Verifying if Master API is giving correct status code for invalid token", groups = {"api","negative","regression","smoke"})
    public void invalidTokenMasterAPITest(){

        given()
                .spec(requestSpec())
                .when()
                .post("master")
                .then()
                .spec(responseSpec_TEXT(401));

    }
}
