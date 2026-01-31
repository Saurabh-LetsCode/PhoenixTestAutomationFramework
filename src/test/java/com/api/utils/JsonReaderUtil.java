package com.api.utils;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JsonReaderUtil {



	public static <T> Iterator<T> loadJSON(String fileName,Class<T[]> classType) {
		

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		ObjectMapper objectMapper = new ObjectMapper();
		T[] objectArray;
		List<T> objectList = null;

		try {

		objectArray = objectMapper.readValue(is, classType);
			objectList = Arrays.asList(objectArray);


		} catch (IOException e) {

			e.printStackTrace();
		}

	return objectList.iterator();
	}
}
