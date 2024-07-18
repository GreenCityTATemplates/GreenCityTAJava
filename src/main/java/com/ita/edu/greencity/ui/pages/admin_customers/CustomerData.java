package com.ita.edu.greencity.ui.pages.admin_customers;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CustomerData extends BasePage {
    @FindBy(how = How.XPATH, using = "(.//*[contains(@class, 'form-group col-sm-5')]/p)[1]")
    private WebElement name;
    @FindBy(how = How.XPATH, using = "(.//*[contains(@class, 'form-group col-sm-5')]/p)[2]")
    private WebElement email;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'form-group col-sm-4')]/p")
    private WebElement phone;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'form-group col-sm-3')]/p")
    private List<WebElement> listOfAdditionalInfo;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'ubs_customer-back')]")
    private WebElement backButton;

    public CustomerData(WebDriver driver) {
        super(driver);
    }

    @Step("get customer`s name")
    public String getName() {
        return name.getText();
    }

    @Step("get customer`s email")
    public String getEmail() {
        return email.getText();
    }

    @Step("get customer`s phone")
    public String getPhone() {
        return phone.getText();
    }

    @Step("get customer`s registration date")
    public String getDateRegistration() {
        return listOfAdditionalInfo.get(0).getText();
    }

    @Step("get customer`s number of orders")
    public String getNumberOrders() {
        return listOfAdditionalInfo.get(1).getText();
    }

    @Step("get customer`s last order date")
    public String getLastOrderDate() {
        return listOfAdditionalInfo.get(2).getText();
    }

    @Step("get customer`s violations")
    public String getViolations() {
        return listOfAdditionalInfo.get(3).getText();
    }

    @Step("click on 'Back' button")
    public AdminCustomers clickOnBackButton() {
        backButton.click();
        return new AdminCustomers(driver);
    }

}
