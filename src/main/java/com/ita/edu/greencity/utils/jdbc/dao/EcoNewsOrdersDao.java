package com.ita.edu.greencity.utils.jdbc.dao;

import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsOrdersEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EcoNewsOrdersDao {

    private List<EcoNewsOrdersEntity> selectByField(String field, String value) {
        Statement statement = ManagerDao.getUbs().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(EcoNewsOrdersEntity.SELECT_BY_FIELD, field, value));
            rows = ManagerDao.getUbs().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return EcoNewsOrdersEntity.getListEcoNewsOrdersEntity(rows);
    }

    public EcoNewsOrdersEntity selectById(long id) {
        List<EcoNewsOrdersEntity> result = selectByField("id", String.valueOf(id));
        return result.size() > 0 ? result.get(0) : null;
    }
}
