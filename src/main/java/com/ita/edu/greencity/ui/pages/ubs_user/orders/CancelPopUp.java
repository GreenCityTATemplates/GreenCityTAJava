package com.ita.edu.greencity.ui.pages.ubs_user.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CancelPopUp extends BasePage {

    @FindBy(how = How.XPATH, using = ".//*[@class = 'warning-text']")
    private WebElement cancelOrderWarningLabel;
    @FindBy(how = How.XPATH, using = ".//*[@class = 'question-text']")
    private WebElement cancelOrderQuestionLabel;
    @FindBy(how = How.XPATH, using = ".//*[@class = 'btn btn-cancel']")
    private WebElement noButton;
    @FindBy(how = How.XPATH, using = ".//*[@class = 'btn btn-pay']")
    private WebElement yesButton;

    public CancelPopUp(WebDriver driver) {
        super(driver);
    }

    @Step("get text of warning label")
    public String getEnsuranceOfCancelingLabelText() {
        return cancelOrderWarningLabel.getText() + " " + cancelOrderQuestionLabel.getText();
    }

    @Step("click on 'no' button")
    public UbsUserOrders clickOnNoButton() {
        noButton.click();
        return new UbsUserOrders(driver);
    }

    @Step("get element of 'no' button")
    public WebElement getNoButton() {
        return noButton;
    }

    @Step("click on 'yes' button")
    public UbsUserOrders clickOnYesButton() {
        yesButton.click();
        return new UbsUserOrders(driver);
    }

    @Step("get element of 'yes' button")
    public WebElement getYesButton() {
        return yesButton;
    }

}
