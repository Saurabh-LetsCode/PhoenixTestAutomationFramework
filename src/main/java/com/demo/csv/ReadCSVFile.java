package com.demo.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ReadCSVFile {

    public static void main(String[] args) throws IOException, CsvException {

       InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");


        InputStreamReader isr = new InputStreamReader(is);
        CSVReader csvReader = new CSVReader(isr);

        List<String[]> dataList = csvReader.readAll();

       for (String[] dataArray : dataList){

           System.out.println(dataArray[0]);
           System.out.println(dataArray[1]);
       }
    }
}
