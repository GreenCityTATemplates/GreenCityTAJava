package com.ita.edu.greencity.utils.jdbc.services;

import com.ita.edu.greencity.utils.jdbc.dao.GreenCityOwnSecurityDao;

public class GreenCityOwnSecurityService {

    private final GreenCityOwnSecurityDao greenCityOwnSecurityDao;

    public GreenCityOwnSecurityService() {
        this.greenCityOwnSecurityDao = new GreenCityOwnSecurityDao();
    }

    public void updatePasswordByID(String hash, int id) {
        greenCityOwnSecurityDao.updatePassword(hash, id);
    }
}
