package com.study.shop.configuration.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseProperties {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseProperties.class);
    private String url;
    private String username;
    private String password;
    private String driver;


    public DatabaseProperties(@JsonProperty("jdbc.url") String url, @JsonProperty("jdbc.username") String username, @JsonProperty("jdbc.password") String password, @JsonProperty("jdbc.driver") String driver) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}

