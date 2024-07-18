package com.ita.edu.greencity.ui.pages.admin_customers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;


public class AdminCustomerContainer {
    WebDriver driver;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-column-clientName')]")
    private WebElement clientName;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'recipientPhone ng-star-inserted')]")
    private WebElement clientPhone;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-column-recipientEmail')]")
    private WebElement clientEmail;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-column-dateOfRegistration')]")
    private WebElement clientRegisterData;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-column-orderDate')]")
    private WebElement clientLastOrder;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-column-number_of_orders')]")
    private WebElement clientOrdersNumber;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-column-violations')]")
    private WebElement clientViolations;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-column-currentPoints')]")
    private WebElement clientBonuses;

    public AdminCustomerContainer(WebElement rootElement) {
        DefaultElementLocatorFactory parentContext = new DefaultElementLocatorFactory(rootElement);
        PageFactory.initElements(parentContext, this);
    }

    @Step("get data from 'Client Name' field")
    public String getСlientName() {
        return clientName.getText();
    }

    @Step("click on 'Client Name'")
    public CustomerData clickOnСlientName() {
        clientName.click();
        return new CustomerData(driver);
    }

    @Step("get data from 'Phone' field")
    public String getСlientPhone() {
        return clientPhone.getText();
    }

    @Step("get data from 'Email' field")
    public String getСlientEmail() {
        return clientEmail.getText();
    }

    @Step("get data from 'Registration Date' field")
    public String getСlientRegisterData() {
        return clientRegisterData.getText();
    }

    @Step("get data from 'Last order' field")
    public String getСlientLastOrder() {
        return clientLastOrder.getText();
    }

    @Step("get data from 'Orders' field")
    public String getСlientOrdersNumber() {
        return clientOrdersNumber.getText();
    }

    @Step("get data from 'Violations' field")
    public String getСlientViolations() {
        return clientViolations.getText();
    }

    @Step("get data from 'Bonuses' field")
    public String getСlientBonuses() {
        return clientBonuses.getText();
    }


}
