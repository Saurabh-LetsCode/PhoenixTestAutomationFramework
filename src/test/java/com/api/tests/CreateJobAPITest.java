package com.api.tests;

import  static io.restassured.RestAssured.*;

import com.api.constant.Role;

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
        CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10),"12308899001005","12308899001005","12308899001005",getTimeWithDaysAgo(10),1,1);
        Problems problems = new Problems(1,"Battery Issue");
        List<Problems> problemsList = new ArrayList<>();
        problemsList.add(problems);

        CreateJobPayload createJobPayload = new CreateJobPayload(0,2,1,1,customer,customerAddress,customerProduct,problemsList);

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

