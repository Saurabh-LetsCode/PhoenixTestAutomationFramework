package com.api.tests.datadriven;

import com.api.constant.*;
import com.api.request.model.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class CreateJobAPIDataDrivenTest {

    private CreateJobPayload createJobPayload;


    @Test(description = "Verifying if create job API is able to create Inwarranty Jobs",
            groups = {"api","regression","datadriven"},
            dataProviderClass = com.dataproviders.DataProviderUtils.class,
            dataProvider = "CreateJobAPIDataProvider")

    public void createJobAPITest(CreateJobPayload createJobPayload){


        given().spec(requestSpecWithAuth(Role.FD,createJobPayload))
                .when()
                .post("/job/create")
                .then()
                .spec(responseSpec_OK())
                .body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
                .body("message", equalTo("Job created successfully. "))
                .body("data.mst_service_location_id", equalTo(1))
                .body("data.job_number",startsWith("JOB_"));


    }
}

