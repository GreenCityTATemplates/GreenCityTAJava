package com.ita.edu.greencity.ui.pages.ubs_user.orders;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class OrdersContainer {

    private final WebDriver driver;
    private final DefaultElementLocatorFactory parentContext;
    private final WebElement rootElement;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-expansion-indicator')]")
    protected WebElement orderDetailsArrowUp;
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'mat-expansion-panel-content')]")
    private WebElement orderDetailsElement;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'order_list-num')]")
    private WebElement orderId;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'order_list-date')]")
    private WebElement orderDate;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'order_list-status')]")
    private WebElement orderStatus;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'order_list-paymentStatus')]")
    private WebElement paymentStatus;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'order_list-paymentAmount')]")
    private WebElement paymentAmount;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'btn_cancel')]")
    private WebElement cancelButton;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'btn_pay')]")
    private WebElement payButton;
    @FindBy(how = How.XPATH, using = ".//*[@class = 'header_details']")
    private WebElement orderDetailsLabel;
    @FindBy(how = How.XPATH, using = ".//*[@class = 'sum_of_order']/td[2]")
    private WebElement orderAmount;
    @FindBy(how = How.XPATH, using = ".//*[@class = 'sum_to_pay']/td[2]")
    private WebElement amountDue;

    public OrdersContainer(WebDriver driver, WebElement rootElement) {
        this.rootElement = rootElement;
        this.driver = driver;
        parentContext = new DefaultElementLocatorFactory(rootElement);
        PageFactory.initElements(parentContext, this);
    }

    @Step("get order details element")
    public WebElement getOrderDetailsElement() {
        return orderDetailsElement;
    }

    @Step("get order id")
    public String getOrderId() {
        return orderId.getText();
    }

    @Step("get order date")
    public String getOrderDate() {
        return orderDate.getText();
    }

    @Step("get order status")
    public String getOrderStatus() {
        return orderStatus.getText();
    }

    @Step("get order payment status")
    public String getPaymentStatus() {
        return paymentStatus.getText();
    }

    @Step("get order payment amount")
    public String getPaymentAmount() {
        return paymentAmount.getText();
    }

    @Step("get label of order details page")
    public String getOrderDetailsLabel() {
        return orderDetailsLabel.getText();
    }

    @Step("get amount of order")
    public String getOrderAmount() {
        return orderAmount.getText();
    }

    @Step("get order amount due")
    public String getAmountDue() {
        return amountDue.getText();
    }

    @Step("click on cancel order button")
    public CancelPopUp clickOnCancelButton() {
        cancelButton.click();
        return new CancelPopUp(driver);
    }

    @Step("get cancel button")
    public WebElement getCancelButton() {
        return cancelButton;
    }

    @Step("click on pay for order button")
    public PaymentPopUp clickOnPayButton() {
        payButton.click();
        return new PaymentPopUp(driver);
    }

    @Step("get order details")
    public OrdersContainer clickOnOrderDetailsArrowDown() {
        orderDetailsArrowUp.click();
        sleep(1000);
        return this;
    }

    @Step("get orders page")
    public UbsUserOrders getUbsUserOrdersPage() {
        return new UbsUserOrders(driver);
    }

    public void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isCancelButtonDisplayed() {
        try {
            return cancelButton.isDisplayed();
        } catch (Exception exception) {
            return false;
        }
    }
}
