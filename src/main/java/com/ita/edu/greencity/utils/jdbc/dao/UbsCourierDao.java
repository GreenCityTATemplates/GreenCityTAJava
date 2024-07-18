package com.ita.edu.greencity.utils.jdbc.dao;

import com.ita.edu.greencity.utils.jdbc.entity.UbsCourierEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UbsCourierDao {
    public List<String> getRandomUbsCourier() {
        Statement statement = ManagerDao.getUbs().getStatement();
        List<String> rows = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(UbsCourierEntity.GET_RANDOM_COURIER_ID);
            while (resultSet.next()) {
                rows.add(resultSet.getString("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return rows;
    }

    public List<String> checkIfCourierIdExists(int courierID) {
        Statement statement = ManagerDao.getUbs().getStatement();
        List<String> rows = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(String.format(UbsCourierEntity.CHECK_IF_COURIER_ID_EXISTS, courierID));
            while (resultSet.next()) {
                rows.add(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return rows;
    }
}
