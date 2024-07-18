package com.ita.edu.greencity.ui.pages.ubs_user.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.ubs_user.UbsUser;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UbsUserOrderHistory extends BasePage {

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'main_header')]/*[contains(@class, 'btn_pay')]")
    private WebElement newOrderButton;
    @FindBy(how = How.XPATH, using = ".//*[contains(@tabindex, '0')]/*[contains(@class, 'mat-tab-label-content')]")
    private WebElement currentOrdersTabButton;
    @FindBy(how = How.XPATH, using = ".//*[contains(@tabindex, '-1')]/*[contains(@class, 'mat-tab-label-content')]")
    private WebElement orderHistoryTabButton;

    public UbsUserOrderHistory(WebDriver driver) {
        super(driver);
    }

    @Step("click on new order button")
    public OrderDetailsPage clickOnNewOrderButton() {
        newOrderButton.click();
        return new OrderDetailsPage(driver);
    }

    @Step("click on current order button")
    public UbsUserOrders clickOnCurrentOrdersButton() {
        currentOrdersTabButton.click();
        return new UbsUserOrders(driver);
    }

    @Step("get element of current orders button")
    public WebElement getCurrentOrdersButton() {
        return currentOrdersTabButton;
    }

    @Step("get element of order history button")
    public WebElement getOrderHistoryTabButton() {
        return orderHistoryTabButton;
    }

    @Step("get orders page")
    public UbsUser getUbsUserPage() {
        return new UbsUser(driver);
    }
}
