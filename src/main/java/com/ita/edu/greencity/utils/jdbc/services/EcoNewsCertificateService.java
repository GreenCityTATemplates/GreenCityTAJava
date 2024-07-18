package com.ita.edu.greencity.utils.jdbc.services;

import com.ita.edu.greencity.utils.jdbc.dao.EcoNewsCertificateDao;

public class EcoNewsCertificateService {
    private final EcoNewsCertificateDao ecoNewsCertificateDao;

    public EcoNewsCertificateService() {
        this.ecoNewsCertificateDao = new EcoNewsCertificateDao();

    }

    public void addCertificate(String codeValue, String statusValue, String expiration_dateValue, int pointsValue) {
        ecoNewsCertificateDao.insertCertificate(codeValue, statusValue, expiration_dateValue, pointsValue);
    }

    public void deleteCertificateByCode(String code) {
        ecoNewsCertificateDao.deleteCertificateByCode(code);
    }

    public String selectRandomUsedCertificate() {
        return ecoNewsCertificateDao.getRandomUsedCertificate().get(0);
    }

    public boolean checkIfCertificateExists(String codeValue) {
        String value = ecoNewsCertificateDao.checkIfCertificateExists(codeValue).get(0);
        return !value.equals("0");
    }

    public String getCertificatePointsByCode(String codeValue) {
        return ecoNewsCertificateDao.getCertificatePoints(codeValue).get(0);
    }

}

