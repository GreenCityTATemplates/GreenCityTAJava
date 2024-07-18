package com.ita.edu.greencity.utils.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

enum EcoNewsVerifyEmailsEntityFields {
    ID(0),
    EXPIRY_DATE(1),
    TOKEN(2),
    USER_ID(3);
    private final int number;

    EcoNewsVerifyEmailsEntityFields(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

public class EcoNewsVerifyEmailsEntity {
    public static final String SELECT_USER_ID_BY_FIELD = "SELECT * FROM verify_emails WHERE %s = '%s';";

    private long id;
    private String expiryDate;
    private String token;
    private Long userId;

    public EcoNewsVerifyEmailsEntity(Long id, String expiryDate, String token, Long userId) {
        this.id = id;
        this.expiryDate = expiryDate;
        this.token = token;
        this.userId = userId;
    }

    public EcoNewsVerifyEmailsEntity() {
        this.id = 0;
        this.expiryDate = null;
        this.token = null;
        this.userId = null;
    }

    public static EcoNewsVerifyEmailsEntity getEcoNewsVerifyEmailsEntity(List<String> row) {
        return new EcoNewsVerifyEmailsEntity()
                .setId(Long.parseLong(row.get(EcoNewsVerifyEmailsEntityFields.ID.getNumber())))
                .setExpiryDate(row.get(EcoNewsVerifyEmailsEntityFields.EXPIRY_DATE.getNumber()))
                .setToken(row.get(EcoNewsVerifyEmailsEntityFields.TOKEN.getNumber()))
                .setUserId(Long.parseLong(row.get(EcoNewsVerifyEmailsEntityFields.USER_ID.getNumber())));
    }

    public static List<EcoNewsVerifyEmailsEntity> getListEcoNewsVerifyEmailsEntity(List<List<String>> rows) {
        List<EcoNewsVerifyEmailsEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getEcoNewsVerifyEmailsEntity(currentRow));
        }
        return result;
    }

    public Long getId() {
        return id;
    }

    public EcoNewsVerifyEmailsEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public EcoNewsVerifyEmailsEntity setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public String getToken() {
        return token;
    }

    public EcoNewsVerifyEmailsEntity setToken(String token) {
        this.token = token;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public EcoNewsVerifyEmailsEntity setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        return "EcoNewsVerifyEmailsEntity{" +
                "id=" + id +
                ", expiryDate='" + expiryDate + '\'' +
                ", token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }
}
