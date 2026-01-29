package com.dataproviders;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.FakerDataGenerator;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUtils {


    @DataProvider(name = "LoginAPIDataProvider",parallel = true)
    public static Iterator<UserBean> loginAPIDataProvider(){

        return CSVReaderUtil.loadCSV("testData/LoginCreds.csv", UserBean.class);

    }


    @DataProvider(name = "CreateJobAPIDataProvider",parallel = true)
    public static Iterator<CreateJobPayload> createJobDataProvider(){

        Iterator<CreateJobBean> createJobBeanIterator =  CSVReaderUtil.loadCSV("testData/CreateJobData.csv", CreateJobBean.class);
        List<CreateJobPayload>  createJobPayloadList = new ArrayList<>();

        while (createJobBeanIterator.hasNext()){
            createJobPayloadList.add(CreateJobBeanMapper.mapper(createJobBeanIterator.next()));
        }

        return createJobPayloadList.iterator();

    }

    @DataProvider(name = "CreateJobAPIFakerDataProvider", parallel = true)
    public static Iterator<CreateJobPayload> createJobFakeDataProvider() {
        String fakerCount = System.getProperty("fakerCount", "5");
        int fakerCountInt = Integer.parseInt(fakerCount);

        Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
        return payloadIterator;
    }
}
