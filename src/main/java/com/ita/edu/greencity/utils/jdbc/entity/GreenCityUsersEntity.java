package com.ita.edu.greencity.utils.jdbc.entity;


import java.util.ArrayList;
import java.util.List;

enum GreenCityUsersEntityFields {
    CODE(0), FIRST_NAME(1), EMAIL(2);
    private final int number;

    GreenCityUsersEntityFields(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}

public class GreenCityUsersEntity {

    public static final String SELECT_ID_BY_EMAIL = "select id from users where email = '%s'";

    private int code;
    private String firstName;
    private String email;

    public GreenCityUsersEntity(int code, String firstName, String email) {
        this.code = code;
        this.firstName = firstName;
        this.email = email;
    }

    public GreenCityUsersEntity() {
        this.code = 0;
        this.firstName = null;
        this.email = null;
    }

    public static GreenCityUsersEntity getGreenCityUsersEntity(List<String> row) {
        return new GreenCityUsersEntity()
                .setCode(Integer.parseInt(row.get(GreenCityUsersEntityFields.CODE.getNumber())))
                .setEmail(row.get(GreenCityUsersEntityFields.CODE.getNumber()))
                .setFirstName(row.get(GreenCityUsersEntityFields.CODE.getNumber()));
    }

    public static List<GreenCityUsersEntity> getListGreenCityUsersEntity(List<List<String>> rows) {
        List<GreenCityUsersEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getGreenCityUsersEntity(currentRow));
        }
        return result;
    }

    public int getCode() {
        return code;
    }

    public GreenCityUsersEntity setCode(int code) {
        this.code = code;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public GreenCityUsersEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public GreenCityUsersEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "GreenCityUsersEntity{" +
                "code='" + code + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }


}
