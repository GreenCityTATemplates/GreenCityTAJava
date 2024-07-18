package com.ita.edu.greencity.utils.jdbc.dao;

import com.ita.edu.greencity.utils.jdbc.entity.AdminUsersEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AdminCustomersDao {
    public String checkTotalCustomers() {
        Statement statement = ManagerDao.getUbs().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(AdminUsersEntity.COUNT_TOTAL_CUSTOMERS_QUERY);
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return rows.get(0).get(0);

    }

    public String checkTotalCustomersByViolations(int numberViolationFrom, int numberViolationTo) {
        Statement statement = ManagerDao.getUbs().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(AdminUsersEntity.COUNT_TOTAL_CUSTOMERS_BY_VIOLATIONS_QUERY, numberViolationFrom, numberViolationTo));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return rows.get(0).get(0);

    }

    public String checkTotalCustomersByBonuses(int numberBonusesFrom, int numberBonusesTo) {
        Statement statement = ManagerDao.getUbs().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(AdminUsersEntity.COUNT_TOTAL_CUSTOMERS_BY_BONUSES_QUERY, numberBonusesFrom, numberBonusesTo));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return rows.get(0).get(0);

    }

    public String checkTotalCustomersByLastOrder(String lastOrderFrom, String lastOrderTo) {
        Statement statement = ManagerDao.getUbs().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(AdminUsersEntity.COUNT_TOTAL_CUSTOMERS_BY_LAST_ORDER_QUERY, lastOrderFrom, lastOrderTo));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return rows.get(0).get(0);

    }

    public String checkTotalCustomersByRegisterDate(String dateFrom, String dateTo) {
        Statement statement = ManagerDao.getUbs().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(AdminUsersEntity.COUNT_TOTAL_CUSTOMERS_BY_REGISTER_DATE, dateFrom, dateTo));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return rows.get(0).get(0);

    }

    public String checkTotalCustomersByOrdersNumber(int numberFrom, int numberTo) {
        Statement statement = ManagerDao.getUbs().getStatement();
        List<List<String>> rows = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format(AdminUsersEntity.COUNT_TOTAL_CUSTOMERS_BY_NUMBER_ORDER, numberFrom, numberTo));
            rows = ManagerDao.get().parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return rows.get(0).get(0);

    }


}
