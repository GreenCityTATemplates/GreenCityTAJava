package com.ita.edu.greencity.utils.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

enum AdminUsersEntityFields {
    CODE(0), CURRENT_POINTS(1), DATE_OF_REGISTRATION(2),
    LAST_ORDER_LOCATION(3), RECIPIENT_EMAIL(4),
    RECIPIENT_NAME(5), RECIPIENT_PHONE(6), RECIPIENT_SURNAME(7), UUID(8), VIOLATIONS(9),
    ;
    private final int number;

    AdminUsersEntityFields(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}

public class AdminUsersEntity {
    public static final String COUNT_TOTAL_CUSTOMERS_QUERY = "SELECT count(DISTINCT users.id)\n" +
            " FROM users\n" +
            " INNER JOIN orders\n" +
            " ON orders.users_id = users.id\n" +
            " ";
    public static final String COUNT_TOTAL_CUSTOMERS_BY_VIOLATIONS_QUERY = "select count(DISTINCT users.id) from users\n" +
            "where violations BETWEEN '%s' AND '%s' ";


    public static final String COUNT_TOTAL_CUSTOMERS_BY_BONUSES_QUERY = "select count(DISTINCT users.id) from users\n" +
            "where current_points BETWEEN '%s' AND '%s' ";


    public static final String COUNT_TOTAL_CUSTOMERS_BY_LAST_ORDER_QUERY = "SELECT count(distinct users_id)\n" +
            "    FROM orders\n" +
            "    where order_date BETWEEN '%s' AND '%s ";


    public static final String COUNT_TOTAL_CUSTOMERS_BY_REGISTER_DATE = " SELECT count(distinct users.id)\n" +
            " FROM users\n" +
            " INNER JOIN orders\n" +
            " ON orders.users_id = users.id\n" +
            " where date_of_registration between '%s' and '%s'";


    public static final String COUNT_TOTAL_CUSTOMERS_BY_NUMBER_ORDER = "SELECT COUNT(*) from (\n" +
            "SELECT COUNT(*)\n" +
            "FROM orders\n" +
            "GROUP BY users_id\n" +
            "HAVING COUNT(*) between '%s' AND '%s') as x;";

    private String code;
    private int current_points;
    private String date_of_registration;
    private String last_order_location;
    private String recipient_email;
    private String recipient_name;
    private String recipient_surname;
    private int uuid;
    private int volations;


    public AdminUsersEntity(String code, int current_points, String date_of_registration, String last_order_location,
                            String recipient_email, String recipient_name, String recipient_surname, int uuid
            , int volations) {
        this.code = code;
        this.current_points = current_points;
        this.date_of_registration = date_of_registration;
        this.last_order_location = last_order_location;
        this.recipient_email = recipient_email;
        this.recipient_name = recipient_name;
        this.recipient_surname = recipient_surname;
        this.uuid = uuid;
        this.volations = volations;
    }

    public AdminUsersEntity() {
        this.code = null;
        this.current_points = 0;
        this.date_of_registration = null;
        this.last_order_location = null;
        this.recipient_email = null;
        this.recipient_name = null;
        this.recipient_surname = null;
        this.uuid = 0;
        this.volations = 0;
    }

    public static AdminUsersEntity getAdminUsersEntity(List<String> row) {
        return new AdminUsersEntity()
                .setCode(row.get(AdminUsersEntityFields.CODE.getNumber()))
                .setСurrent_points(Integer.parseInt(row.get(AdminUsersEntityFields.CURRENT_POINTS.getNumber())))
                .setDate_of_registration(row.get(AdminUsersEntityFields.DATE_OF_REGISTRATION.getNumber()))
                .setLast_order_location(row.get(AdminUsersEntityFields.LAST_ORDER_LOCATION.getNumber()))
                .setRecipient_email(row.get(AdminUsersEntityFields.RECIPIENT_EMAIL.getNumber()))
                .setRecipient_name(row.get(AdminUsersEntityFields.RECIPIENT_NAME.getNumber()))
                .setRecipient_surname(row.get(AdminUsersEntityFields.RECIPIENT_SURNAME.getNumber()))
                .setUuid(Integer.parseInt(row.get(AdminUsersEntityFields.UUID.getNumber())))
                .setVolations((Integer.parseInt(row.get(AdminUsersEntityFields.VIOLATIONS.getNumber()))));
    }

    public static List<AdminUsersEntity> getListAdminUsersEntity(List<List<String>> rows) {
        List<AdminUsersEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getAdminUsersEntity(currentRow));
        }
        return result;
    }

    public String getCode() {
        return code;
    }

    public AdminUsersEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public int getСurrent_points() {
        return current_points;
    }

    public AdminUsersEntity setСurrent_points(int current_points) {
        this.current_points = current_points;
        return this;
    }

    public String getDate_of_registration() {
        return date_of_registration;
    }

    public AdminUsersEntity setDate_of_registration(String date_of_registration) {
        this.date_of_registration = date_of_registration;
        return this;
    }

    public String getLast_order_location() {
        return last_order_location;
    }

    public AdminUsersEntity setLast_order_location(String last_order_location) {
        this.last_order_location = last_order_location;
        return this;
    }

    public String getRecipient_email() {
        return recipient_email;
    }

    public AdminUsersEntity setRecipient_email(String recipient_email) {
        this.recipient_email = recipient_email;
        return this;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public AdminUsersEntity setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
        return this;
    }

    public String getRecipient_surname() {
        return recipient_surname;
    }

    public AdminUsersEntity setRecipient_surname(String recipient_surname) {
        this.recipient_surname = recipient_surname;
        return this;
    }

    public int getUuid() {
        return uuid;
    }

    public AdminUsersEntity setUuid(int uuid) {
        this.uuid = uuid;
        return this;
    }

    public int getVolations() {
        return volations;
    }

    public AdminUsersEntity setVolations(int volations) {
        this.volations = volations;
        return this;
    }

    @Override
    public String toString() {
        return "AdminUsersEntity{" +
                "code='" + code + '\'' +
                ", current_points='" + current_points + '\'' +
                ", date_of_registration='" + date_of_registration + '\'' +
                ", last_order_location='" + last_order_location + '\'' +
                ", recipient_email=" + recipient_email +
                ", recipient_name=" + recipient_name +
                ", recipient_surname='" + recipient_surname + '\'' +
                ", uuid='" + uuid + '\'' +
                ", volations='" + volations + '\'' +
                '}';
    }


}
