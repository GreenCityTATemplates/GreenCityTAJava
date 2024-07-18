package com.ita.edu.greencity.utils.jdbc.services;

import com.ita.edu.greencity.utils.jdbc.dao.EcoNewsUsersDao;
import com.ita.edu.greencity.utils.jdbc.dao.EcoNewsVerifyEmailsDao;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsUsersEntity;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsVerifyEmailsEntity;

public class EcoNewsVerifyEmailsService {

    private final EcoNewsVerifyEmailsDao ecoNewsVerifyEmailsDao;
    private final EcoNewsUsersDao ecoNewsUsersDao;

    public EcoNewsVerifyEmailsService() {
        this.ecoNewsUsersDao = new EcoNewsUsersDao();
        this.ecoNewsVerifyEmailsDao = new EcoNewsVerifyEmailsDao();
    }

    public EcoNewsVerifyEmailsEntity selectByUserId(String email) {
        EcoNewsUsersEntity user = ecoNewsUsersDao.selectByEmail(email);
        return ecoNewsVerifyEmailsDao.selectByUserId(user.getId());
    }
}
