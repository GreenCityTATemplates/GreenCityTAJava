package com.ita.edu.greencity.utils.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

enum EcoNewsCertificateEntityFields {
    CODE(0), CREATION_DATE(1), DATE_OF_USE(2), EXPIRATION_DATE(3), ORDER_ID(4), POINTS(5), STATUS(6);
    private final int number;

    EcoNewsCertificateEntityFields(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}


public class EcoNewsCertificateEntity {
    public static final String INSERT_NEW_CERTIFICATE = " INSERT INTO certificate (code,status, expiration_date, points) VALUES ('%s', '%s', '%s', %s)";
    public static final String DELETE_CERTIFICATE = "DELETE  from certificate where code = '%s'";
    public static final String SELECT_RANDOM_USED_CERTIFICATE_CODE = "SELECT code FROM certificate WHERE status = 'USED' ORDER BY RANDOM() LIMIT 1";
    public static final String CHECK_IF_CERTIFICATE_EXISTS = "SELECT COUNT(1) FROM certificate WHERE code = '%s'";
    public static final String GET_CERTIFICATE_POINTS = "SELECT points FROM certificate WHERE code = '%s'";
    private String code;
    private String creation_date;
    private String date_of_use;
    private String expiration_date;
    private long order_id;
    private int points;
    private String status;

    public EcoNewsCertificateEntity(String code, String creation_date, String date_of_use, String expiration_date, long order_id, int points, String status) {
        this.code = code;
        this.creation_date = creation_date;
        this.date_of_use = date_of_use;
        this.expiration_date = expiration_date;
        this.order_id = order_id;
        this.points = points;
        this.status = status;
    }

    public EcoNewsCertificateEntity() {
        this.code = null;
        this.creation_date = null;
        this.date_of_use = null;
        this.expiration_date = null;
        this.order_id = 0;
        this.points = 0;
        this.status = null;
    }

    public static EcoNewsCertificateEntity getEcoNewsCertificateEntity(List<String> row) {
        return new EcoNewsCertificateEntity()
                .setCode(row.get(EcoNewsCertificateEntityFields.CODE.getNumber()))
                .setCreation_date(row.get(EcoNewsCertificateEntityFields.CREATION_DATE.getNumber()))
                .setDate_of_use(row.get(EcoNewsCertificateEntityFields.DATE_OF_USE.getNumber()))
                .setExpiration_date(row.get(EcoNewsCertificateEntityFields.EXPIRATION_DATE.getNumber()))
                .setOrder_id(Long.parseLong(row.get(EcoNewsCertificateEntityFields.ORDER_ID.getNumber())))
                .setPoints(Integer.parseInt(row.get(EcoNewsCertificateEntityFields.POINTS.getNumber())))
                .setStatus(row.get(EcoNewsCertificateEntityFields.STATUS.getNumber()));
    }

    public static List<EcoNewsCertificateEntity> getListEcoNewsCertificateEntity(List<List<String>> rows) {
        List<EcoNewsCertificateEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getEcoNewsCertificateEntity(currentRow));
        }
        return result;
    }

    public String getCode() {
        return code;
    }

    public EcoNewsCertificateEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public EcoNewsCertificateEntity setCreation_date(String creation_date) {
        this.creation_date = creation_date;
        return this;
    }

    public String getDate_of_use() {
        return date_of_use;
    }

    public EcoNewsCertificateEntity setDate_of_use(String date_of_use) {
        this.date_of_use = date_of_use;
        return this;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public EcoNewsCertificateEntity setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
        return this;
    }

    public long getOrder_id() {
        return order_id;
    }

    public EcoNewsCertificateEntity setOrder_id(long order_id) {
        this.order_id = order_id;
        return this;
    }

    public int getPoints() {
        return points;
    }

    public EcoNewsCertificateEntity setPoints(int points) {
        this.points = points;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public EcoNewsCertificateEntity setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "EcoNewsCertificateEntity{" +
                "code='" + code + '\'' +
                ", creation_date='" + creation_date + '\'' +
                ", date_of_use='" + date_of_use + '\'' +
                ", expiration_date='" + expiration_date + '\'' +
                ", order_id=" + order_id +
                ", points=" + points +
                ", status='" + status + '\'' +
                '}';
    }
}
