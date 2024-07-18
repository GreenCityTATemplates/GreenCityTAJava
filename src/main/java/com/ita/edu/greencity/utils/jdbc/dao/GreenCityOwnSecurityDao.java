package com.ita.edu.greencity.utils.jdbc.dao;

import com.ita.edu.greencity.utils.jdbc.entity.GreenCityOwnSecurityEntity;

import java.sql.SQLException;
import java.sql.Statement;

public class GreenCityOwnSecurityDao {
    public void updatePassword(String hash, int id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            System.out.println("Id - " + id);
            System.out.println("Hash - " + hash);
            statement.executeUpdate(String.format(GreenCityOwnSecurityEntity.UPDATE_PASSWORD_BY_EMAIL, hash, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
    }
}

