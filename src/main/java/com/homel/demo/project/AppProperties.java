package com.homel.demo.project;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppProperties {
    private final Environment environment;

    public String getProperties(String name) {
        return environment.getProperty(name);
    }
}
