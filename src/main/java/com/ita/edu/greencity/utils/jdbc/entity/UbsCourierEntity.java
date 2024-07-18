package com.ita.edu.greencity.utils.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

enum UbsCourierEntityFields {
    ID(1), COURIER_STATUS(2), CREATE_DATE(3), CREATED_BY_ID(4);
    private final int number;

    UbsCourierEntityFields(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

public class UbsCourierEntity {
    public static final String GET_RANDOM_COURIER_ID = "SELECT id FROM courier ORDER BY RANDOM() LIMIT 1";
    public static final String CHECK_IF_COURIER_ID_EXISTS = "SELECT COUNT(1) FROM courier WHERE id = '%s'";
    private int id;
    private String courier_status;
    private String date;
    private int created_by_id;

    public UbsCourierEntity(int id, String courier_status, String date, int created_by_id) {
        this.id = id;
        this.courier_status = courier_status;
        this.date = date;
        this.created_by_id = created_by_id;
    }

    public UbsCourierEntity() {
        this.id = 0;
        this.courier_status = null;
        this.date = null;
        this.created_by_id = 0;
    }

    public static UbsCourierEntity getUbsCourierEntity(List<String> row) {
        return new UbsCourierEntity()
                .setId(Integer.parseInt(row.get(UbsCourierEntityFields.ID.getNumber())))
                .setCourier_status(row.get(UbsCourierEntityFields.COURIER_STATUS.getNumber()))
                .setDate(row.get(UbsCourierEntityFields.CREATE_DATE.getNumber()))
                .setCreated_by_id(Integer.parseInt(row.get(UbsCourierEntityFields.CREATED_BY_ID.getNumber())));

    }

    public static List<UbsCourierEntity> getListUbsCourierEntity(List<List<String>> rows) {
        List<UbsCourierEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getUbsCourierEntity(currentRow));
        }
        return result;
    }

    public UbsCourierEntity setId(int id) {
        this.id = id;
        return this;
    }

    public UbsCourierEntity setCourier_status(String courier_status) {
        this.courier_status = courier_status;
        return this;
    }

    public UbsCourierEntity setDate(String date) {
        this.date = date;
        return this;
    }

    public UbsCourierEntity setCreated_by_id(int created_by_id) {
        this.created_by_id = created_by_id;
        return this;
    }

    @Override
    public String toString() {
        return "UbsCourierEntity{" +
                "id=" + id +
                ", courier_status='" + courier_status + '\'' +
                ", date='" + date + '\'' +
                ", created_by_id=" + created_by_id +
                '}';
    }
}
