package com.ita.edu.greencity.utils.jdbc.dao;

import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsUsersEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EcoNewsUsersDao {
    public void deleteById(long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(EcoNewsUsersEntity.DELETE_BY_ID, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
    }

    public List<EcoNewsUsersEntity> selectAll() {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(EcoNewsUsersEntity.SELECT_ALL);
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return EcoNewsUsersEntity.getListEcoNewsUsersEntity(rows);
    }

    private List<EcoNewsUsersEntity> selectByField(String field, String value) {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(EcoNewsUsersEntity.SELECT_BY_FIELD, field, value));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return EcoNewsUsersEntity.getListEcoNewsUsersEntity(rows);
    }

    public EcoNewsUsersEntity selectById(long id) {
        List<EcoNewsUsersEntity> result = selectByField("id", String.valueOf(id));
        return result.size() > 0 ? result.get(0) : null;
    }

    public EcoNewsUsersEntity selectByEmail(String email) {
        List<EcoNewsUsersEntity> result = selectByField("email", email);
        return result.size() > 0 ? result.get(0) : null;
    }
}
