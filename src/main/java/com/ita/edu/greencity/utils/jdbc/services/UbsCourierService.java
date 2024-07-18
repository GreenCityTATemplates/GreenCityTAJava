package com.ita.edu.greencity.utils.jdbc.services;

import com.ita.edu.greencity.utils.jdbc.dao.UbsCourierDao;

public class UbsCourierService {
    private final UbsCourierDao ubsCourierDao;

    public UbsCourierService() {
        ubsCourierDao = new UbsCourierDao();
    }

    public final boolean checkIfCertificateExists(int codeValue) {
        String value = ubsCourierDao.checkIfCourierIdExists(codeValue).get(0);
        return !value.equals("0");
    }

    public final String selectRandomUbsCourier() {
        return ubsCourierDao.getRandomUbsCourier().get(0);
    }
}
