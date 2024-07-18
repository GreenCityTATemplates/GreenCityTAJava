package com.ita.edu.greencity.utils.jdbc.entity;


import java.util.ArrayList;
import java.util.List;

enum GreenCityOwnSecurityEntityFields {
    CODE(0), PASSWORD(1), USER_ID(2);
    private final int number;

    GreenCityOwnSecurityEntityFields(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}

public class GreenCityOwnSecurityEntity {
    public static final String UPDATE_PASSWORD_BY_EMAIL = "UPDATE own_security\n" +
            "SET password = '%s' \n" +
            "WHERE user_id = '%s';";

    private int code;
    private String password;
    private String userID;

    public GreenCityOwnSecurityEntity(int code, String password, String userID) {
        this.code = code;
        this.password = password;
        this.userID = userID;
    }

    public GreenCityOwnSecurityEntity() {
        this.code = 0;
        this.password = null;
        this.userID = null;
    }

    public static GreenCityOwnSecurityEntity getGreenCityOwnSecurityEntity(List<String> row) {
        return new GreenCityOwnSecurityEntity()
                .setCode(Integer.parseInt(row.get(GreenCityOwnSecurityEntityFields.CODE.getNumber())))
                .setPassword(row.get(GreenCityOwnSecurityEntityFields.PASSWORD.getNumber()))
                .setUserID(row.get(GreenCityOwnSecurityEntityFields.USER_ID.getNumber()));

    }

    public static List<GreenCityOwnSecurityEntity> getListGreenCityOwnSecurityEntity(List<List<String>> rows) {
        List<GreenCityOwnSecurityEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getGreenCityOwnSecurityEntity(currentRow));
        }
        return result;
    }

    public int getCode() {
        return code;
    }

    public GreenCityOwnSecurityEntity setCode(int code) {
        this.code = code;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public GreenCityOwnSecurityEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUserID() {
        return userID;
    }

    public GreenCityOwnSecurityEntity setUserID(String id) {
        this.userID = id;
        return this;
    }

    @Override
    public String toString() {
        return "GreenCityOwnSecurityEntity{" +
                "code='" + code + '\'' +
                ", password='" + password + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }


}
