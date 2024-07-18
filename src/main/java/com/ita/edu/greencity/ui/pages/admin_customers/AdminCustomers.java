package com.ita.edu.greencity.ui.pages.admin_customers;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class AdminCustomers extends BasePage {

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-row cdk-row ng-star-inserted')]")
    private List<WebElement> allUsersData;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'table-buttons filter-btn')]")
    private WebElement filterButton;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'table-buttons')][2]")
    private WebElement exportToExcelButton;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'countInfo')]")
    private WebElement totalCustomers;

    public AdminCustomers(WebDriver driver) {
        super(driver);
        this.sleep(5000);
    }

    private List<AdminCustomerContainer> getCustomers() {
        List<AdminCustomerContainer> adminCustomerList = new ArrayList<>();
        for (WebElement element : allUsersData) {
            adminCustomerList.add(new AdminCustomerContainer(element));
        }
        return adminCustomerList;
    }

    @Step("choose customer by name, what you want to see Customer Data")
    public AdminCustomerContainer chooseCustomer(String clientName) {
        this.sleep(3000);
        return getCustomers()
                .stream()
                .filter(element -> element.getСlientName().equals(clientName))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    @Step("click on 'Filter' Button")
    public CustomersFilter clickOnFilterButton() {
        filterButton.click();
        return new CustomersFilter(driver);
    }

    @Step("click on 'Export To Excel' Button")
    public ExportToExcel clickOnExportToExcelButton() {
        this.sleep(7000);
        exportToExcelButton.click();
        return new ExportToExcel(driver);
    }

    @Step("get value of current customers")
    public String getTotalCustomers() {
        this.sleep(7000);
        return totalCustomers.getText();
    }


}
