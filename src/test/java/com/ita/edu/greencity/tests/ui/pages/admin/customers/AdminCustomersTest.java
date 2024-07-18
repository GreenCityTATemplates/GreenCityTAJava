package com.ita.edu.greencity.tests.ui.pages.admin.customers;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.admin_customers.AdminCustomers;
import com.ita.edu.greencity.ui.pages.admin_customers.CustomerData;
import com.ita.edu.greencity.ui.pages.admin_customers.ExcelRowCountExample;
import com.ita.edu.greencity.ui.pages.admin_menu.AdminMenu;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.utils.jdbc.services.AdminCustomersService;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class AdminCustomersTest extends TestRun {

    @BeforeMethod
    public void loginToUBS() {
        new HeaderSignedOutComponent(driver).clickSignIn()
                .inputEmail(provider.getAdminEmail())
                .inputPassword(provider.getAdminPassword())
                .clickSignIn();
        AdminMenu adminMenu = new AdminMenu(driver);
        adminMenu.clickOnUBSAdminMenu().clickOnUBSAdmin().clickOnCustomers();
    }

    @Test
    @Description("check if a customer`s date in table and when you click on name are the same")
    @Severity(SeverityLevel.NORMAL)
    public void verifyCustomersDataInTableAndDuringClicking() {
        ArrayList<String> dataFromTable = new ArrayList<>();
        AdminCustomers adminCustomers = new AdminCustomers(driver);
        dataFromTable.add(adminCustomers.chooseCustomer("Bohdan Melnyk").getСlientName());
        dataFromTable.add(adminCustomers.chooseCustomer("Bohdan Melnyk").getСlientPhone());
        dataFromTable.add(adminCustomers.chooseCustomer("Bohdan Melnyk").getСlientLastOrder());
        dataFromTable.add(adminCustomers.chooseCustomer("Bohdan Melnyk").getСlientEmail());
        dataFromTable.add(adminCustomers.chooseCustomer("Bohdan Melnyk").getСlientRegisterData());
        dataFromTable.add(adminCustomers.chooseCustomer("Bohdan Melnyk").getСlientOrdersNumber());
        dataFromTable.add(adminCustomers.chooseCustomer("Bohdan Melnyk").getСlientViolations());

        adminCustomers.chooseCustomer("Bohdan Melnyk")
                .clickOnСlientName();
        CustomerData customerData = new CustomerData(driver);
        ArrayList<String> data = new ArrayList<>();

        data.add(customerData.getName());
        data.add(customerData.getPhone());
        data.add(customerData.getLastOrderDate());
        data.add(customerData.getEmail());
        data.add(customerData.getDateRegistration());
        data.add(customerData.getNumberOrders());
        data.add(customerData.getViolations());

        customerData.clickOnBackButton();
        SoftAssert asert = new SoftAssert();
        asert.assertEquals(data.get(0), dataFromTable.get(0), "СlientName dont match");
        asert.assertEquals(data.get(1), dataFromTable.get(1), "СlientPhone dont match");
        asert.assertEquals(data.get(2), dataFromTable.get(2), "СlientLastOrder dont match");
        asert.assertEquals(data.get(3), dataFromTable.get(3), "СlientEmail dont match");
        asert.assertEquals(data.get(4), dataFromTable.get(4), "СlientRegisterData dont match");
        asert.assertEquals(data.get(5), dataFromTable.get(5), "СlientOrdersNumber dont match");
        asert.assertEquals(data.get(6), dataFromTable.get(6), "СlientViolations dont match");
    }

    @Test
    @Description("check if 'Total customers:' shows correct value")
    @Severity(SeverityLevel.NORMAL)
    public void verifyCorrectTotalCustomers() {
        String currentCustomers = new AdminCustomers(driver).getTotalCustomers();
        currentCustomers = currentCustomers.substring(currentCustomers.indexOf(":") + 1).replaceAll(" ", "");
        String exceptedCustomers = new AdminCustomersService().checkAllTotalCustomers();
        Assert.assertEquals(currentCustomers, exceptedCustomers);
    }

    @Test
    @Description("check if admin can opportunity save current view of table to excel document ")
    @Severity(SeverityLevel.CRITICAL)
    public void exportToExcelCurrentView() {
        new AdminCustomers(driver).clickOnExportToExcelButton()
                .selectSaveCurrentView()
                .nextButton();
        int rowsInxlsxFile = ExcelRowCountExample.readFromExcelFile();
        String currentCustomers = new AdminCustomers(driver).getTotalCustomers();
        currentCustomers = currentCustomers.substring(currentCustomers.indexOf(":") + 1).replaceAll(" ", "");
        Assert.assertEquals(rowsInxlsxFile, Integer.parseInt(currentCustomers));
        ExcelRowCountExample.deleteExcelFile();
    }

    @Test
    @Description("check if admin can opportunity to filter all customers by violations")
    @Severity(SeverityLevel.NORMAL)
    public void filterByViolations() {
        String currentCustomers =
                new AdminCustomers(driver)
                        .clickOnFilterButton()
                        .inputViolationsFrom("1")
                        .inputViolationsTo("5")
                        .clickOnFilterButtonOnFilterPage()
                        .getTotalCustomers();
        currentCustomers = currentCustomers.substring(currentCustomers.indexOf(":") + 1).replaceAll(" ", "");
        String exceptedCustomers = new AdminCustomersService().checkAllTotalCustomersByViolations(1, 5);
        Assert.assertEquals(currentCustomers, exceptedCustomers);
    }

    @Test
    @Description("check if admin can opportunity to filter all customers by bonuses")
    @Severity(SeverityLevel.NORMAL)
    public void filterByBonuses() {
        String currentCustomers =
                new AdminCustomers(driver)
                        .clickOnFilterButton()
                        .inputBonusesFrom("10")
                        .inputBonusesTo("1200")
                        .clickOnFilterButtonOnFilterPage()
                        .getTotalCustomers();
        currentCustomers = currentCustomers.substring(currentCustomers.indexOf(":") + 1).replaceAll(" ", "");
        String exceptedCustomers = new AdminCustomersService().checkAllTotalCustomersByBonuses(10, 1200);
        Assert.assertEquals(currentCustomers, exceptedCustomers);
    }

    @Test
    @Description("check if admin can opportunity to filter all customers by last order date")
    @Severity(SeverityLevel.NORMAL)
    public void filterByLastOrderDate() {
        String currentCustomers =
                new AdminCustomers(driver)
                        .clickOnFilterButton()
                        .inputDateLastOrderFrom("2022-02-16")
                        .inputDateLastOrderTo("2022-02-17")
                        .clickOnFilterButtonOnFilterPage()
                        .getTotalCustomers();
        currentCustomers = currentCustomers.substring(currentCustomers.indexOf(":") + 1).replaceAll(" ", "");
        String exceptedCustomers = new AdminCustomersService().checkAllTotalCustomersByLastOrder("2022-02-16", "2022-02-17");
        Assert.assertEquals(currentCustomers, exceptedCustomers);
    }

    @Test
    @Description("check if admin can opportunity to filter all customers by registration date")
    @Severity(SeverityLevel.NORMAL)
    public void filterByRegisterDate() {
        String currentCustomers =
                new AdminCustomers(driver)
                        .clickOnFilterButton()
                        .inputDateLastOrderFrom("2022-02-16")
                        .clickOnFilterButtonOnFilterPage()
                        .getTotalCustomers();
        currentCustomers = currentCustomers.substring(currentCustomers.indexOf(":") + 1).replaceAll(" ", "");
        String exceptedCustomers = new AdminCustomersService().checkAllTotalCustomersByRegisterDate("", "");
        Assert.assertEquals(currentCustomers, exceptedCustomers);
    }

    @Test
    @Description("check if admin can opportunity to filter all customers by number od orders")
    @Severity(SeverityLevel.NORMAL)
    public void filterByNumberOrder() {
        String currentCustomers =
                new AdminCustomers(driver)
                        .clickOnFilterButton()
                        .inputNumberOrdersFrom("10")
                        .inputNumberOrdersTo("34")
                        .clickOnFilterButtonOnFilterPage()
                        .getTotalCustomers();
        currentCustomers = currentCustomers.substring(currentCustomers.indexOf(":") + 1).replaceAll(" ", "");
        String exceptedCustomers = new AdminCustomersService().checkAllTotalCustomersByOrderNumber(10, 34);
        Assert.assertEquals(currentCustomers, exceptedCustomers);
    }

}
