package com.study.shop.configuration.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;


public class YamlDatabasePropertiesLoader {
    private static final Logger logger = LoggerFactory.getLogger(YamlDatabasePropertiesLoader.class);

    public static DatabaseProperties load(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        DatabaseProperties databaseProperties = mapper.readValue(new File(path), DatabaseProperties.class);
        logger.debug("YAML DatabaseProperties was loaded: ");
        logger.debug("URL : {} " + databaseProperties.getUrl());
        logger.debug("Username : {} " + databaseProperties.getUsername());
        logger.debug("Password : {} " + databaseProperties.getPassword());
        logger.debug("Driver : {} " + databaseProperties.getDriver());
        return databaseProperties;
    }
}