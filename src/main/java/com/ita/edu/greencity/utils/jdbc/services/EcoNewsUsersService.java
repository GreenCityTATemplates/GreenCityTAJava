package com.ita.edu.greencity.utils.jdbc.services;

import com.ita.edu.greencity.utils.jdbc.dao.EcoNewsUserActionsDao;
import com.ita.edu.greencity.utils.jdbc.dao.EcoNewsUsersDao;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsUserActionsEntity;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsUsersEntity;

import java.util.List;

public class EcoNewsUsersService {

    private final EcoNewsUsersDao ecoNewsUsersDao;
    private final EcoNewsUserActionsDao ecoNewsUserActionsDao;

    public EcoNewsUsersService() {
        this.ecoNewsUsersDao = new EcoNewsUsersDao();
        this.ecoNewsUserActionsDao = new EcoNewsUserActionsDao();
    }

    public List<EcoNewsUsersEntity> getAllUsers() {
        return ecoNewsUsersDao.selectAll();
    }

    public List<EcoNewsUserActionsEntity> getAllUserActions(int userId) {
        EcoNewsUsersEntity user = ecoNewsUsersDao.selectById(userId);
        return ecoNewsUserActionsDao.selectByUserId(user.getId());
    }

    public void deleteById(long userId) {
        EcoNewsUsersEntity user = ecoNewsUsersDao.selectById(userId);
        if (user != null) {
            List<EcoNewsUserActionsEntity> actions = ecoNewsUserActionsDao.selectByUserId(user.getId());
            for (EcoNewsUserActionsEntity action : actions) {
                ecoNewsUserActionsDao.deleteById(action.getId());
            }
            ecoNewsUsersDao.deleteById(user.getId());
        }
    }

    public EcoNewsUsersEntity getByEmail(String email) {
        return ecoNewsUsersDao.selectByEmail(email);
    }
}
