package com.api.tests.datadriven;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;
import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class LoginAPIJsonDataDrivenTest {



    @Test(description = "Verifying if Login API is working for FD User",
            groups = {"api","regression","datadriven"},
            dataProviderClass = com.dataproviders.DataProviderUtils.class,
            dataProvider = "LoginAPIJsonDataProvider")

    public void loginAPITest ( UserCredentials userCredentials)  {

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
