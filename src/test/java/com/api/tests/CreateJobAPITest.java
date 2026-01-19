package com.api.tests;

import  static io.restassured.RestAssured.*;

import com.api.constant.Role;
import com.api.pojo.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class CreateJobAPITest {


    @Test
    public void createJobAPITest(){

        Customer customer = new Customer("Jatin","Jasani","7507331188","","saurabh.jasani@gmail.com","");
        CustomerAddress customerAddress = new CustomerAddress("801","Vasant  Galaxy","Balewadi Street","Near orchid school","Balewadi","411006","Indian","MH");
        CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z","33508828001405","33508828001405","33508828001405","2025-04-06T18:30:00.000Z",1,1);
        Problems problems = new Problems(1,"Battery Issue");
        Problems[] problemsArray = new Problems[1];
        problemsArray[0] = problems;

        CreateJobPayload createJobPayload = new CreateJobPayload(0,2,1,1,customer,customerAddress,customerProduct,problemsArray);

        given().spec(SpecUtil.requestSpecWithAuth(Role.FD,createJobPayload))
                .when()
                .post("/job/create")
                .then()
                .spec(SpecUtil.responseSpec_OK());

    }
}
