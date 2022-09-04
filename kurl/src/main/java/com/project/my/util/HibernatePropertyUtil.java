package com.project.my.util;

import lombok.experimental.UtilityClass;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class HibernatePropertyUtil {

    public static Map<String, Object> createHibernateProperties(Environment env) {
        final Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.jdbc.batch_size", env.getProperty("spring.jpa.properties.hibernate.jdbc.batch_size"));
        properties.put("hibernate.order_inserts", env.getProperty("spring.jpa.properties.hibernate.order_inserts"));
        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.properties.hibernate.show_sql"));
        return properties;
    }
}
