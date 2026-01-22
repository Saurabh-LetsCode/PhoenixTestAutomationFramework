package com.api.tests;

import  static io.restassured.RestAssured.*;

import com.api.constant.*;

import com.api.request.model.*;
import static com.api.utils.DateTimeUtil.*;
import com.api.utils.SpecUtil;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {


    @Test
    public void createJobAPITest(){

        Customer customer = new Customer("Jatin","Jasani","7507331188","","saurabh.jasani@gmail.com","");
        CustomerAddress customerAddress = new CustomerAddress("801","Vasant  Galaxy","Balewadi Street","Near orchid school","Balewadi","411006","Indian","MH");
        CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10),"12308877001005","12308877001005","12308877001005",getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
        Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(),"Battery Issue");
        List<Problems> problemsList = new ArrayList<>();
        problemsList.add(problems);

        CreateJobPayload createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),Platform.FRONT_DESK.getCode(),Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(),customer,customerAddress,customerProduct,problemsList);

        given().spec(SpecUtil.requestSpecWithAuth(Role.FD,createJobPayload))
                .when()
                .post("/job/create")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
                .body("message", equalTo("Job created successfully. "))
                .body("data.mst_service_location_id", equalTo(1))
                .body("data.job_number",startsWith("JOB_"));


    }
}

