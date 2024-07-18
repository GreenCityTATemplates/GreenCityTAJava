package com.ita.edu.greencity.ui.pages.ubs_user.orders;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class OrderDetails {

    private final DefaultElementLocatorFactory parentContext;
    protected WebDriver driver;
    protected WebElement rootElement;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-expansion-indicator')]")
    protected WebElement orderDetailsArrowUp;
    @FindBy(how = How.XPATH, using = ".//*[@class = 'header_details']")
    private WebElement orderDetailsLabel;

    @FindBy(how = How.XPATH, using = ".//*[@class = 'header_table']/th[1]")
    private WebElement tableHeaderServicesColumn;

    @FindBy(how = How.XPATH, using = ".//*[@class = 'header_table']/th[2]")
    private WebElement tableHeaderVolumeColumn;

    @FindBy(how = How.XPATH, using = ".//*[@class = 'header_table']/th[3]")
    private WebElement tableHeaderCostColumn;

    @FindBy(how = How.XPATH, using = ".//*[@class = 'header_table']/th[4]")
    private WebElement tableHeaderQuantityOfBagsColumn;

    @FindBy(how = How.XPATH, using = ".//*[@class = 'header_table']/th[5]")
    private WebElement tableHeaderSumColumn;

    @FindBy(how = How.XPATH, using = ".//*[@class = 'sum_of_order']/td[2]")
    private WebElement orderAmount;

    @FindBy(how = How.XPATH, using = ".//*[@class = 'sum_to_pay']/td[2]")
    private WebElement amountDue;

    public OrderDetails(WebDriver driver, WebElement rootElement) {
        this.rootElement = rootElement;
        this.driver = driver;
        parentContext = new DefaultElementLocatorFactory(rootElement);
        PageFactory.initElements(parentContext, this);
    }

    @Step("get label of order details page")
    public WebElement getOrderDetailsLabel() {
        return orderDetailsLabel;
    }

    @Step("get first column of table text")
    public String getTableHeaderServicesColumn() {
        return tableHeaderServicesColumn.getText();
    }

    @Step("get second column of table text")
    public String getTableHeaderVolumeColumn() {
        return tableHeaderVolumeColumn.getText();
    }

    @Step("get third column of table text")
    public String getTableHeaderCostColumn() {
        return tableHeaderCostColumn.getText();
    }

    @Step("get fourth column of table text")
    public String getTableHeaderQuantityOfBagsColumn() {
        return tableHeaderQuantityOfBagsColumn.getText();
    }

    @Step("get fifth column of table text")
    public String getTableHeaderSumColumn() {
        return tableHeaderSumColumn.getText();
    }

    @Step("get amount of order")
    public String getOrderAmount() {
        return orderAmount.getText();
    }

    @Step("get order amount due")
    public String getAmountDue() {
        return amountDue.getText();
    }

    public OrdersContainer clickOnOrderDetailsArrowUp() {
        orderDetailsArrowUp.click();
        return new OrdersContainer(driver, rootElement);
    }

    public OrdersContainer getOrderContainer() {
        return new OrdersContainer(driver, rootElement);
    }
}
