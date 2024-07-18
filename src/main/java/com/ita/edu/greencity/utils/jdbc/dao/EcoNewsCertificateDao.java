package com.ita.edu.greencity.utils.jdbc.dao;

import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsCertificateEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EcoNewsCertificateDao {

    public void deleteCertificateByCode(String code) {
        Statement statement = ManagerDao.getUbs().getStatement();
        try {
            statement.executeUpdate(String.format(EcoNewsCertificateEntity.DELETE_CERTIFICATE, code));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
    }

    public void insertCertificate(String codeValue, String statusValue, String expiration_dateValue, int pointsValue) {
        Statement statement = ManagerDao.getUbs().getStatement();
        try {
            statement.executeUpdate(String.format(EcoNewsCertificateEntity.INSERT_NEW_CERTIFICATE, codeValue, statusValue, expiration_dateValue, pointsValue));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
    }

    public List<String> getRandomUsedCertificate() {
        Statement statement = ManagerDao.getUbs().getStatement();
        List<String> rows = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(EcoNewsCertificateEntity.SELECT_RANDOM_USED_CERTIFICATE_CODE);
            while (resultSet.next()) {
                rows.add(resultSet.getString("CODE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return rows;
    }

    public List<String> checkIfCertificateExists(String codeValue) {
        Statement statement = ManagerDao.getUbs().getStatement();
        List<String> rows = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(String.format(EcoNewsCertificateEntity.CHECK_IF_CERTIFICATE_EXISTS, codeValue));
            while (resultSet.next()) {
                rows.add(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return rows;
    }

    public List<String> getCertificatePoints(String codeValue) {
        Statement statement = ManagerDao.getUbs().getStatement();
        List<String> rows = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(String.format(EcoNewsCertificateEntity.GET_CERTIFICATE_POINTS, codeValue));
            while (resultSet.next()) {
                rows.add(resultSet.getString("points"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return rows;
    }
}
