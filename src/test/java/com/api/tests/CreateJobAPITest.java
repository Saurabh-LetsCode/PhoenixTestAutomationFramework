package com.api.tests;

import com.api.constant.*;
import com.api.request.model.*;
import static com.api.utils.SpecUtil.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class CreateJobAPITest {

    private CreateJobPayload createJobPayload;

    @BeforeMethod(description = "Creating createJob api request payload")
    public void setUp(){
        Customer customer = new Customer("Jatin","Jasani","7507331188","","saurabh.jasani@gmail.com","");
        CustomerAddress customerAddress = new CustomerAddress("801","Vasant  Galaxy","Balewadi Street","Near orchid school","Balewadi","411006","Indian","MH");
        CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10),"12308877001005","12308877001005","12308877001005",getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
        Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(),"Battery Issue");
        List<Problems> problemsList = new ArrayList<>();
        problemsList.add(problems);

         createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),Platform.FRONT_DESK.getCode(),Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(),customer,customerAddress,customerProduct,problemsList);

    }


    @Test(description = "Verifying if create job API is able to create Inwarranty Jobs", groups = {"api","regression","smoke"})
    public void createJobAPITest(){


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

