package com.ita.edu.greencity.utils.jdbc.dao;

import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsUserActionsEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EcoNewsUserActionsDao {

    public void deleteById(long id) {
        Statement statement = ManagerDao.get().getStatement();
        try {
            statement.execute(String.format(EcoNewsUserActionsEntity.DELETE_BY_ID, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
    }

    public List<EcoNewsUserActionsEntity> selectAll() {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(EcoNewsUserActionsEntity.SELECT_ALL);
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return EcoNewsUserActionsEntity.getListEcoNewsUserActionsEntity(rows);
    }

    private List<EcoNewsUserActionsEntity> selectByField(String field, String value) {
        Statement statement = ManagerDao.get().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(EcoNewsUserActionsEntity.SELECT_BY_FIELD, field, value));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return EcoNewsUserActionsEntity.getListEcoNewsUserActionsEntity(rows);
    }

    public EcoNewsUserActionsEntity selectById(long id) {
        List<EcoNewsUserActionsEntity> result = selectByField("id", String.valueOf(id));
        return result.size() > 0 ? result.get(0) : null;
    }

    public List<EcoNewsUserActionsEntity> selectByUserId(long userId) {
        List<EcoNewsUserActionsEntity> result = selectByField("user_id", String.valueOf(userId));
        return result;
    }
}
