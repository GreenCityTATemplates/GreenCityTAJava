package com.ita.edu.greencity.ui.pages.ubs_user.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.orders.payment.PaymentByFondyPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class PaymentPopUp extends BasePage {

    @FindBy(how = How.XPATH, using = ".//*[contains(@id, 'mat-dialog')]")
    private WebElement popUp;
    @FindBy(how = How.XPATH, using = ".//*[@class = 'text-order-sum']")
    private WebElement orderSumLabel;
    @FindBy(how = How.XPATH, using = ".//*[@formcontrolname = 'certificateCode']")
    private WebElement certificateCodeTextBox;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'btn btn-activate')]")
    private WebElement activeButton;
    @FindBy(how = How.XPATH, using = ".//*[@formcontrolname = 'paymentSystem']")
    private WebElement paymentMethodDropdown;
    private final Select paymentMethodsList = new Select(paymentMethodDropdown);
    @FindBy(how = How.XPATH, using = ".//*[@class = 'btn btn-cancel']")
    private WebElement cancelButton;
    @FindBy(how = How.XPATH, using = ".//*[@class = 'btn btn-pay']")
    private WebElement payButton;

    public PaymentPopUp(WebDriver driver) {
        super(driver);
    }

    @Step("get sum of order label text")
    public String getOrderSumLabel() {
        return orderSumLabel.getText();
    }

    @Step("input code of certificate")
    public PaymentPopUp inputCertificateCode(String code) {
        certificateCodeTextBox.sendKeys(code);
        return this;
    }

    @Step("active certificate")
    public PaymentPopUp clickOnActiveButton() {
        activeButton.click();
        return this;
    }

    @Step("choose payment method by index")
    public PaymentPopUp choosePaymentMethodByIndex(int index) {
        paymentMethodDropdown.click();
        paymentMethodsList.getOptions().get(index).click();
        return this;
    }

    @Step("choose payment method by value")
    public PaymentPopUp choosePaymentMethodByValue(String value) {
        paymentMethodDropdown.click();
        paymentMethodsList.getOptions()
                .stream()
                .filter(element -> element.getText().contains(value))
                .findFirst()
                .orElseThrow(NullPointerException::new)
                .click();
        return this;
    }

    @Step("click on cancel payment button")
    public UbsUserOrders clickOnCancelButton() {
        cancelButton.click();
        return new UbsUserOrders(driver);
    }

    @Step("click on pay for order button")
    public PaymentByFondyPage clickOnPayButton() {
        payButton.click();
        return new PaymentByFondyPage(driver);
    }

    public boolean isPaymentPopUpOpened() {
        try {
            return popUp.isDisplayed();
        } catch (Exception exception) {
            return false;
        }
    }

}
