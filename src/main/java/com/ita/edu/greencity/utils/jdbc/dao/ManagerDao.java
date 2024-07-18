package com.ita.edu.greencity.utils.jdbc.dao;

import com.ita.edu.greencity.utils.ValueProvider;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ManagerDao {
    private static volatile ManagerDao instance = null;
    private final Map<Long, Connection> connections;
    private String userName;
    private String password;
    private String url;
    private ValueProvider property;

    private ManagerDao() {
        connections = new HashMap<>();
        try {
            property = new ValueProvider();
        } catch (IOException e) {
            e.printStackTrace();
        }
        registerDriver();
        readProperties();
    }

    private ManagerDao(String type) {
        connections = new HashMap<>();
        try {
            property = new ValueProvider();
        } catch (IOException e) {
            e.printStackTrace();
        }
        registerDriver();
        if (type.equals("ubs")) {
            readUbsProperties();
        } else {
            readProperties();
        }
    }

    public static ManagerDao get() {
        if (instance == null) {
            synchronized (ManagerDao.class) {
                if (instance == null) {
                    instance = new ManagerDao();
                }
            }
        }
        return instance;
    }

    public static ManagerDao getUbs() {
        if (instance == null) {
            synchronized (ManagerDao.class) {
                if (instance == null) {
                    instance = new ManagerDao("ubs");
                }
            }
        }
        return instance;
    }

    public static void closeAllConnection() {
        if (instance != null) {
            for (Map.Entry<Long, Connection> entry : instance.connections.entrySet()) {
                try {
                    entry.getValue().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerDriver() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readProperties() {
        userName = property.getJDBCGreenCityUsername();
        password = property.getJDBCGreenCityPassword();
        url = property.getJDBCGreenCityURL();
    }

    private void readUbsProperties() {
        userName = property.getJDBCGreenCityUbsUsername();
        password = property.getJDBCGreenCityUbsPassword();
        url = property.getJDBCGreenCityUbsURL();
    }

    private Connection createConnections() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection getConnection() {
        long idThread = Thread.currentThread().getId();
        Connection connection = connections.get(idThread);
        if (connection == null) {
            connection = createConnections();
            connections.put(idThread, connection);
        }
        return connection;
    }

    public Statement getStatement() {
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public List<List<String>> parseResultSet(ResultSet resultSet) throws SQLException {
        List<List<String>> result = new ArrayList<>();
        int columnCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(resultSet.getString(i));
            }
            result.add(row);
        }
        return result;
    }
}
