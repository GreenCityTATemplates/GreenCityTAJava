package com.ita.edu.greencity.ui.pages.google_account;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GoogleSignInInputPasswordPopUp extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[@id='password']//input")
    private WebElement passwordInputField;

    @FindBy(how = How.XPATH, using = "//*[@id='passwordNext']//button/span")
    private WebElement continueButton;

    public GoogleSignInInputPasswordPopUp(WebDriver driver) {
        super(driver);
    }

    @Step("Input password {password} into Google form to sign in Google account")
    public GoogleSignInInputPasswordPopUp inputPasswordIntoField(String password) {
        passwordInputField.sendKeys(password);
        return this;
    }

    @Step("Click on continue button")
    public SignUpComponent clickOnContinueButton() {
        continueButton.click();
        sleep(10000);
        switchBackToDefaultTab();
        return new SignUpComponent(driver);
    }
}
