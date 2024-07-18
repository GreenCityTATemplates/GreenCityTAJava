package com.ita.edu.greencity.utils.jdbc.services;

import com.ita.edu.greencity.utils.jdbc.dao.AdminCustomersDao;

public class AdminCustomersService {
    private final AdminCustomersDao adminCustomersDao;

    public AdminCustomersService() {
        this.adminCustomersDao = new AdminCustomersDao();
    }

    public String checkAllTotalCustomers() {
        return adminCustomersDao.checkTotalCustomers();
    }

    public String checkAllTotalCustomersByViolations(int violationsFrom, int numberViolationTo) {
        return adminCustomersDao.checkTotalCustomersByViolations(violationsFrom, numberViolationTo);
    }

    public String checkAllTotalCustomersByBonuses(int bonusesFrom, int bonusesTo) {
        return adminCustomersDao.checkTotalCustomersByBonuses(bonusesFrom, bonusesTo);
    }

    public String checkAllTotalCustomersByLastOrder(String dateFrom, String dateTo) {
        return adminCustomersDao.checkTotalCustomersByLastOrder(dateFrom, dateTo);
    }

    public String checkAllTotalCustomersByRegisterDate(String dateFrom, String dateTo) {
        return adminCustomersDao.checkTotalCustomersByRegisterDate(dateFrom, dateTo);
    }

    public String checkAllTotalCustomersByOrderNumber(int numberFrom, int numberTo) {
        return adminCustomersDao.checkTotalCustomersByOrdersNumber(numberFrom, numberTo);
    }
}
