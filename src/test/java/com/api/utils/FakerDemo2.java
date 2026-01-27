package com.api.utils;

import com.api.request.model.Customer;
import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerDemo2 {

    public static void main(String[] args) {

        Faker faker = new Faker(new Locale("en-IND"));
        String fname = faker.name().firstName();
        String lname = faker.name().firstName();
        String mobileNumber = faker.numerify("70########");
        String alternateMobileNumber = faker.numerify("70########");
        String customerEmailAddress = faker.internet().emailAddress();

        Customer customer = new Customer(fname,lname,mobileNumber,alternateMobileNumber,customerEmailAddress,null);
        System.out.println(customer);
    }
}
