package com.ita.edu.greencity.ui.pages.sign_in;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInComponent extends BasePage {

    @FindBy(how = How.XPATH, using = "//app-sign-in[@class = 'ng-star-inserted']/h1")
    private WebElement title;
    @FindBy(how = How.XPATH, using = "//a[@class = 'close-modal-window']/img")
    private WebElement close;
    @FindBy(how = How.XPATH, using = "//div[@class = 'wrapper']//*/input[@formcontrolname='email']")
    private WebElement email;
    @FindBy(how = How.XPATH, using = "//input[@id = 'password']")
    private WebElement password;
    @FindBy(how = How.XPATH, using = "//span[@class = 'show-hide-btn']")
    private WebElement showHidePassword;
    @FindBy(how = How.XPATH, using = "//a[@class = 'forgot-password']")
    private WebElement forgotPassword;
    @FindBy(how = How.XPATH, using = "//button[@class = 'primary-global-button']")
    private WebElement signIn;
    @FindBy(how = How.XPATH, using = "//app-google-btn/button[@class = 'google-sign-in']")
    private WebElement signInGoogle;
    @FindBy(how = How.XPATH, using = "//a[@class = 'sign-up-link']")
    private WebElement signUp;
    @FindBy(how = How.XPATH, using = "//div[@id = 'email-err-msg']/app-error/div")
    private WebElement errorEmail;
    @FindBy(how = How.XPATH, using = "//div[@class = 'alert-general-error ng-star-inserted']")
    private WebElement errorPassword;
    @FindBy(how = How.XPATH, using = "//div[@class = 'alert-general-error ng-star-inserted']")
    private WebElement errorGeneral;

    public SignInComponent(WebDriver driver) {
        super(driver);
        this.sleep(250);
    }

    @Step("click on close button")
    public UbsHomePage clickCloseBtn() {
        close.click();

        return new UbsHomePage(driver);
    }

    @Step("input email | value = {emailInput}")
    public SignInComponent inputEmail(String emailInput) {
        email.clear();
        email.sendKeys(emailInput, Keys.ENTER);
        return this;
    }

    @Step("input password | value = {passwordInput}")
    public SignInComponent inputPassword(String passwordInput) {
        password.clear();
        password.sendKeys(passwordInput, Keys.ENTER);

        return this;
    }

    @Step("click on 'show-hide password' button")
    public SignInComponent clickShowHidePassword() {
        showHidePassword.click();

        return this;
    }

    @Step("click on 'forgot password' link")
    public ForgotPasswordComponent clickForgotPassword() {
        forgotPassword.click();

        return new ForgotPasswordComponent(driver);
    }

    @Step("click on 'sign in' button")
    public UbsHomePage clickSignIn() {
        signIn.click();

        return new UbsHomePage(driver);
    }

    @Step("click on 'sign in' button after calling-up courier")
    public UbsHomePage clickSignInAfterCourierCallUp() {
        signIn.click();

        return new UbsHomePage(driver);
    }

    @Step("click on 'sign in' button after calling-up courier")
    public SelectRegion clickSignInAfterCallUpCourier() {
        signIn.click();

        return new SelectRegion(driver);
    }

    @Step("click on 'sign in' button")
    public HeaderSignedInComponent clickSignInHeaderComponent() {
        signIn.click();

        return new HeaderSignedInComponent(driver);
    }


    @Step("click on 'sign in with google' button")
    public SelectRegion clickSignInGoogle() {
        signInGoogle.click();

        return new SelectRegion(driver);
    }

    @Step("click on 'sign up' link")
    public SignUpComponent clickSignUp() {
        signUp.click();

        return new SignUpComponent(driver);
    }

    @Step("get text of email error message")
    public String getErrorEmailMessage() {
        return errorEmail.getText();
    }

    @Step("get text of password error message")
    public String getErrorPasswordMessage() {
        return errorPassword.getText();
    }

    @Step("get text of title")
    public String getTitle() {
        return title.getText();
    }

    @Step("click on title to unfocus")
    public SignInComponent unfocus() {
        title.click();

        return this;
    }

    @Step("get text of general error message")
    public String getErrorGeneralMessage() {
        return errorGeneral.getText();
    }

    @Step("get attribute 'type' of password field")
    public String getPasswordType() {
        return password.getAttribute("type");
    }

    public SignInComponent loadData() {
        while (true) {
            try {
                driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-auth-modal/div/div/div[2]/div/app-sign-in/form/button/span"));
            } catch (Exception e) {
                return this;
            }
            sleep(500);
        }
    }
}
