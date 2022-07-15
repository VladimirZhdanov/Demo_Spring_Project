package com.homel.demo.project.utils;

import com.homel.demo.project.SpringApplicationContext;
import com.homel.demo.project.security.AppProperties;

public class CommonUtils {
    public static String getProperty(String name) {
        AppProperties appProperties = SpringApplicationContext.getBean("appProperties", AppProperties.class);
        return appProperties.getProperties(name);
    }
}
