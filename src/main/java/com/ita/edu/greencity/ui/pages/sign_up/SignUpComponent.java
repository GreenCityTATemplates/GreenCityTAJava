package com.ita.edu.greencity.ui.pages.sign_up;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignUpComponent extends BasePage {
    @FindBy(how = How.XPATH, using = "//img[@class='cross-btn']")
    private WebElement exitButton;

    @FindBy(how = How.XPATH, using = "//div[@class='wrapper']//input[@id='email']")
    private WebElement emailField;

    @FindBy(how = How.XPATH, using = "//*[@id='email-err-msg']/app-error/div")
    private WebElement blankEmailFieldAlert;

    @FindBy(how = How.XPATH, using = "//input[@id='firstName']")
    private WebElement userNameField;

    @FindBy(how = How.XPATH, using = "//*[@id='firstname-err-msg']/app-error/div")
    private WebElement blankUserNameFieldAlert;

    @FindBy(how = How.XPATH, using = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//div[@id='password-err-msg']//app-error/div")
    private WebElement blankPasswordFieldAlert;

    @FindBy(how = How.XPATH, using = "//input[@id='password']/..//img[@class='show-password-img']")
    private WebElement showHidePasswordButton;

    @FindBy(how = How.XPATH, using = "//input[@id='repeatPassword']")
    private WebElement confirmPasswordField;

    @FindBy(how = How.XPATH, using = "//div[@id='confirm-err-msg']//app-error/div")
    private WebElement blankConfirmPasswordFieldAlert;

    @FindBy(how = How.XPATH, using = "//input[@id='repeatPassword']/..//img[@class='show-password-img']")
    private WebElement showHideConfirmPasswordButton;

    @FindBy(how = How.XPATH, using = "//div[@class='wrapper']//button[@class='primary-global-button']")
    private WebElement signUpButton;

    @FindBy(how = How.XPATH, using = "//button[@class='google-sign-in']")
    private WebElement signUpWithGoogleButton;

    @FindBy(how = How.XPATH, using = "//a[@class='ubs-exist-sign-in']")
    private WebElement signInLinkButton;

    @FindBy(how = How.XPATH, using = "//app-sign-up/h2")
    private WebElement subTitle;

    @FindBy(how = How.XPATH, using = "//*[contains(@id,'cdk-overlay-')]/snack-bar-container/simple-snack-bar/span")
    private WebElement successRegistrationAlert;

    public SignUpComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Click on cross to close sign-up pop up")
    public UbsHomePage clickOnExitButton() {
        exitButton.click();
        return new UbsHomePage(driver);
    }

    @Step("Input email {email} into email field in sign-up pop up")
    public SignUpComponent inputEmailIntoField(String email) {
        emailField.sendKeys(email);
        return this;
    }

    @Step("Check text of alert near email field in sign-up pop up")
    public String getTextOfBlankEmailFieldAlert() {
        return blankEmailFieldAlert.getText();
    }

    @Step("Input user name {userName} into user name field in sign-up pop up")
    public SignUpComponent inputUserNameIntoField(String userName) {
        userNameField.sendKeys(userName);
        return this;
    }

    @Step("Check text of alert near user name field in sign-up pop up")
    public String getTextOfBlankUserNameFieldAlert() {
        return blankUserNameFieldAlert.getText();
    }

    @Step("Input password {password} into password field in sign-up pop up")
    public SignUpComponent inputPasswordIntoField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Check text of alert near password field in sign-up pop up")
    public String getTextOfBlankPasswordFieldAlert() {
        return blankPasswordFieldAlert.getText();
    }

    @Step("Click on showHidePasswordButton to show or hide password")
    public SignUpComponent clickOnShowHidePasswordButton() {
        showHidePasswordButton.click();
        return this;
    }

    @Step("Input confirmation password {confirmPassword} into confirmation password field in sign-up pop up")
    public SignUpComponent inputConfirmPasswordIntoField(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
        return this;
    }

    @Step("Check text of alert near confirmation password field in sign-up pop up")
    public String getTextOfBlankConfirmPasswordFieldAlert() {
        return blankConfirmPasswordFieldAlert.getText();
    }

    @Step("Click on showHideConfirmPasswordButton to show or hide confirmation password")
    public SignUpComponent clickOnShowHideConfirmPasswordButton() {
        showHideConfirmPasswordButton.click();
        return this;
    }

    @Step("Click on sign-up button")
    public SignUpComponent clickOnSignUpButton() {
        signUpButton.click();
        return this;
    }

    @Step("Click on sign-up with Google button")
    public SignUpComponent clickOnSignUpWithGoogleButton() {
        signUpWithGoogleButton.click();
        sleep(5000);
        switchToNewTabWithoutClosing();
        return this;
    }

    @Step("Click on sign in link")
    public SignInComponent clickOnSignInLinkButton() {
        signInLinkButton.click();
        return new SignInComponent(driver);
    }

    public String getTextOfTitle() {
        return subTitle.getText();
    }

    @Step("Click on title to unfocus")
    public SignUpComponent clickOnTextOfSubTitle() {
        subTitle.click();
        return this;
    }

    @Step("Check the disability of sign-up button in sign-up pop up")
    public boolean checkDisabledSignUpButton() {
        return (signUpButton.getAttribute("disabled") != null);
    }

    @Step("Check the alert which appears after successful registration of user")
    public String getTextOfSuccessRegistrationAlert() {
        sleep(7000);
        return successRegistrationAlert.getText();
    }

    @Step("Check the visibility of password in field")
    public boolean checkPasswordIsVisible() {
        return (passwordField.getAttribute("type").equals("text"));
    }

    @Step("Check the visibility of confirmation password in field")
    public boolean checkConfirmPasswordIsVisible() {
        return (confirmPasswordField.getAttribute("type").equals("text"));
    }
}
