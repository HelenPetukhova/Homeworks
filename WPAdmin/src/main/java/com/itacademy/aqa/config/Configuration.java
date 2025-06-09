package com.itacademy.aqa.config;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static Properties properties;
    private static Properties localProperties;
    private static final Logger logger = Logger.getLogger(Configuration.class);

    private Configuration(){}

    public static Properties getProperties(){
        if(properties == null){
            initProperties();
        }
        return properties;
    }

    private static void initProperties() {
        logger.info("Initializing properties");
        properties = new Properties();
        localProperties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/project.properties"));
            if (new File("local.properties").exists()) {
                localProperties.load(new FileInputStream("local.properties"));
                properties.putAll(localProperties);   // иерархия зависит от указанного порядка (сначала берутся properties из файла)
            }
            properties.putAll(System.getenv());  // переменные окружения  // в IDEA Run > Edit Configurations > Environment Variables или в Jenkins В настройках job-а > Build Environment > Environment variables
            properties.putAll(System.getProperties());  // системные Java-properties устанавливаются при запуске в кансоли: java -Denv=test или в коде System.setProperty("env", "test")
            logger.info("Properties were loaded from file src/main/resources/project.properties");

        } catch (IOException e) {
            System.out.println("Properties cannot be read");
            logger.error("Properties were not loaded from file src/main/resources/project.properties", e);
        }
    }


    public static BrowserEnum getBrowserEnum() {
        logger.info("Getting browser enum for browser: " + getProperties().get("browser").toString());
        try {
            return BrowserEnum.valueOf(getProperties().get("browser").toString());

        } catch (IllegalArgumentException e) {
            logger.error("Invalid browser value in properties", e);
            throw e;
        }

    }

    public static String getBaseUrl() {
        logger.info("Getting base URL: " + getProperties().get("baseUrl").toString());
        return getProperties().get("baseUrl").toString();
    }

    public static String getScreenShotFolder() {
        return properties.getProperty("screenshotFolder");
    }

    public static String getRemoteDriverUrl() {
        return properties.getProperty("remoteWebDriverUrl");
    }
}

