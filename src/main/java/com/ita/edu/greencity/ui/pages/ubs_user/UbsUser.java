package com.ita.edu.greencity.ui.pages.ubs_user;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.UbsUserOrders;
import com.ita.edu.greencity.ui.pages.user_data.UserData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UbsUser extends BasePage {

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'main_header')]/*[contains(@class, 'btn_pay')]")
    private WebElement newOrderButton;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'sidebar-list-item-link')][contains(@href, 'profile')]")
    private WebElement userDataButton;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'sidebar-list-item-link')][contains(@href, 'orders')]")
    private WebElement ordersButton;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'sidebar-list-item-link')][contains(@href, 'bonuses')]")
    private WebElement invoiceButton;
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'sidebar-list-item-link')][contains(@href, 'messages')]")
    private WebElement messagesButton;

    public UbsUser(WebDriver driver) {
        super(driver);
    }

    public OrderDetailsPage clickOnNewOrderButton() {
        newOrderButton.click();
        return new OrderDetailsPage(driver);
    }

    public UserData clickOnUserDataButton() {
        this.sleep(7000);
        userDataButton.click();
        return new UserData(driver);

    }

    public UbsUserOrders clickOnOrdersButton() {
        ordersButton.click();
        return new UbsUserOrders(driver);
    }

    public Invoice clickOnInvoiceButton() {
        invoiceButton.click();
        return new Invoice(driver);
    }

    public Messages clickOnMessagesButton() {
        messagesButton.click();
        return new Messages(driver);
    }

    public HeaderSignedInComponent getHeader() {
        return new HeaderSignedInComponent(driver);
    }
}
