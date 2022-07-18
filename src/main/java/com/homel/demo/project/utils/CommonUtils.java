package com.homel.demo.project.utils;

import com.homel.demo.project.SpringApplicationContext;
import com.homel.demo.project.AppProperties;
import org.springframework.data.util.Pair;

import java.util.List;

public class CommonUtils {
    /**
     * Get property by given name
     * @param name name
     * @return property
     */
    public static String getProperty(String name) {
        AppProperties appProperties = SpringApplicationContext.getBean("appProperties", AppProperties.class);
        return appProperties.getProperties(name);
    }

    /**
     * Make error message by given message and arguments
     * @param message message
     * @param args arguments
     * @return full exception message
     */
    public static String getExceptionMessage(String message, List<Pair<String, String>> args) {
        if (message == null) return "";
        if (args == null || args.isEmpty()) return message;

        String delimiter = ", ";
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        sb.append(", arguments: ");

        args.forEach(it -> {
            sb.append(it.getFirst());
            sb.append(" : ");
            sb.append(it.getSecond());
            sb.append(delimiter);
        });

        sb.setLength(sb.length() - delimiter.length());

        return sb.toString();
    }
}
