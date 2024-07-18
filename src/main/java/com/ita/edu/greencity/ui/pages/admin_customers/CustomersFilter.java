package com.ita.edu.greencity.ui.pages.admin_customers;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;


public class CustomersFilter extends BasePage {
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-input-element mat-form-field-autofill-control')]")
    private List<WebElement> allFilter;
    @FindBy(how = How.CSS, using = "filterReset ng-star-inserted")
    private WebElement resetFiltersButton;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'table-buttons filter-btn')]")
    private WebElement filterButton;

    public CustomersFilter(WebDriver driver) {
        super(driver);
    }

    @Step("input data in 'Registration date (from)' field")
    public CustomersFilter inputRegisterDateFrom(final String data) {
        allFilter.get(0).clear();
        allFilter.get(0).sendKeys(data);
        return this;
    }

    @Step("input data in 'Registration date (to)' field")
    public CustomersFilter inputRegisterDateTo(final String data) {
        allFilter.get(1).clear();
        allFilter.get(1).sendKeys(data);
        return this;
    }

    @Step("input data in 'Date of last order (from)' field")
    public CustomersFilter inputDateLastOrderFrom(final String data) {
        allFilter.get(2).clear();
        allFilter.get(2).sendKeys(data);
        return this;
    }

    @Step("input data in 'Date of last order (to)' field")
    public CustomersFilter inputDateLastOrderTo(final String data) {
        allFilter.get(3).clear();
        allFilter.get(3).sendKeys(data);
        return this;
    }

    @Step("input data in 'Number of orders (from)' field")
    public CustomersFilter inputNumberOrdersFrom(final String data) {
        allFilter.get(4).clear();
        allFilter.get(4).sendKeys(data);
        return this;
    }

    @Step("input data in 'Number of orders (to)' field")
    public CustomersFilter inputNumberOrdersTo(final String data) {
        allFilter.get(5).clear();
        allFilter.get(5).sendKeys(data);
        return this;
    }

    @Step("input data in 'Violations (from)' field")
    public CustomersFilter inputViolationsFrom(final String data) {
        this.sleep(4000);
        allFilter.get(6).clear();
        allFilter.get(6).sendKeys(data);
        return this;
    }

    @Step("input data in 'Violations (to)' field")
    public CustomersFilter inputViolationsTo(final String data) {
        allFilter.get(7).clear();
        allFilter.get(7).sendKeys(data);
        return this;
    }

    @Step("input data in 'Bonuses (from)' field")
    public CustomersFilter inputBonusesFrom(final String data) {
        allFilter.get(8).clear();
        allFilter.get(8).sendKeys(data);
        return this;
    }

    @Step("input data in 'Bonuses (to)' field")
    public CustomersFilter inputBonusesTo(final String data) {
        allFilter.get(9).clear();
        allFilter.get(9).sendKeys(data);
        return this;
    }

    @Step("click on 'Reset' button")
    public AdminCustomers clickOnResetFiltersButton() {
        resetFiltersButton.click();
        return new AdminCustomers(driver);
    }

    @Step("click on 'Filter' button")
    public AdminCustomers clickOnFilterButtonOnFilterPage() {
        filterButton.click();
        return new AdminCustomers(driver);
    }

}
