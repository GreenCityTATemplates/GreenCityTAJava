package com.ita.edu.greencity.utils;

import java.io.*;
import java.util.Properties;

public class ValueProvider {
    private final Properties properties;

    public ValueProvider() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("src/main/resources/properties.properties");
        properties = new Properties();
        properties.load(fileInputStream);
    }


    public String getBaseURL() {
        return properties.getProperty("baseURL");
    }

    public String getUbsHomePageURL() {
        return properties.getProperty("UbsHomePageURL");
    }

    public String getLocalUbsHomePageURL() {
        return properties.getProperty("localUbsHomePageURL");
    }

    public String getEmail() {
        return properties.getProperty("email");
    }

    public String getEmailForUserData() {
        return properties.getProperty("emailForUserData");
    }

    public String getAdminEmail() {
        return properties.getProperty("adminEmail");
    }

    public String getAdminPassword() {
        return properties.getProperty("adminPassword");
    }

    public String getEmailForChangePassw() {
        return properties.getProperty("emailForChangePassw");
    }

    public String getPasswordForChangePassw() {
        return properties.getProperty("passwordForChangepassw");
    }

    public String getPasswordHash() {
        return properties.getProperty("passwordHash");
    }


    public String getPasswordForUserData() {
        return properties.getProperty("passwordForUserData");
    }

    public String getEmailAdmin() {
        return properties.getProperty("emailAdmin");
    }

    public String getUserName() {
        return properties.getProperty("userName");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public void setPassword(String newPassword) {
        FileReader reader = null;
        try {
            reader = new FileReader("src/main/resources/properties.properties");
            properties.load(reader);
            properties.getProperty("password");
            properties.setProperty("password", newPassword);
            OutputStream os = new FileOutputStream("src/main/resources/properties.properties");
            properties.store(os, "new password for rollback");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUserWithoutOrdersEmail() {
        return properties.getProperty("userWithoutOrdersEmail");
    }

    public String getUserWithOrdersEmail() {
        return properties.getProperty("userWithOrdersEmail");
    }

    public String getPasswordAdmin() {
        return properties.getProperty("passwordAdmin");
    }

    public String getCardNumber() {
        return properties.getProperty("cardNumber");
    }

    public String getInvalidCardNumber() {
        return properties.getProperty("invalidCardNumber");
    }

    public String getExpiryDate() {
        return properties.getProperty("expiryDate");
    }

    public String getCVV2() {
        return properties.getProperty("CVV2");
    }

    public String getJDBCGreenCityUsername() {
        return properties.getProperty("JDBCGreenCityUsername");
    }

    public String getJDBCGreenCityPassword() {
        return properties.getProperty("JDBCGreenCityPassword");
    }

    public String getJDBCGreenCityURL() {
        return properties.getProperty("JDBCGreenCityURL");
    }

    public String getJDBCGreenCityUbsUsername() {
        return properties.getProperty("JDBCGreenCityUbsUsername");
    }

    public String getJDBCGreenCityUbsPassword() {
        return properties.getProperty("JDBCGreenCityUbsPassword");
    }

    public String getJDBCGreenCityUbsURL() {
        return properties.getProperty("JDBCGreenCityUbsURL");
    }

    public String getUbsApiURL() {
        return properties.getProperty("baseUbsApiURL");
    }

    public String getUserApiURL() {
        return properties.getProperty("baseUserApiURL");
    }

    public String getCorrectData() {return properties.getProperty("correctData");}
    public String getCorrectSignature() {
        return properties.getProperty("correctSignature");
    }

    public String getIncorrectData() {
        return properties.getProperty("incorrectData");
    }
    public String getIncorrectSignature() {
        return properties.getProperty("incorrectSignature");
    }
}
