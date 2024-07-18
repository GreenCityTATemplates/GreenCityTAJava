package com.ita.edu.greencity.utils.jdbc.services;

import com.ita.edu.greencity.utils.jdbc.dao.EcoNewsOrdersDao;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsOrdersEntity;

public class EcoNewsOrdersService {

    private final EcoNewsOrdersDao ecoNewsOrdersDao;

    public EcoNewsOrdersService() {
        this.ecoNewsOrdersDao = new EcoNewsOrdersDao();
    }

    public EcoNewsOrdersEntity getById(long id) {
        return ecoNewsOrdersDao.selectById(id);
    }
}
