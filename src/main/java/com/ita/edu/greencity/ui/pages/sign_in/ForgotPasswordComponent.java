package com.ita.edu.greencity.ui.pages.sign_in;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class ForgotPasswordComponent extends BasePage {

    @FindBy(how = How.XPATH, using = "//div[@class = 'restore-password-container']/h1")
    private WebElement title;
    @FindBy(how = How.XPATH, using = "//a[@class = 'close-modal-window']/img")
    private WebElement close;
    @FindBy(how = How.XPATH, using = "//form/input[@id = 'email']")
    private WebElement email;
    @FindBy(how = How.XPATH, using = "//button[@type = 'submit']")
    private WebElement submit;
    @FindBy(how = How.XPATH, using = "//button[@class = 'google-sign-in']")
    private WebElement signInGoogle;
    @FindBy(how = How.XPATH, using = "//a[@class = 'sign-in-link']")
    private WebElement backToSignIn;

    public ForgotPasswordComponent(WebDriver driver) {
        super(driver);
        this.sleep(250);
    }

    public UbsHomePage clickClose() {
        close.click();

        return new UbsHomePage(driver);
    }

    public ForgotPasswordComponent inputEmail(String emailInput) {
        email.sendKeys(emailInput);

        return this;
    }

    public UbsHomePage clickSubmit() {
        submit.click();

        return new UbsHomePage(driver);
    }

    // ???
    public void clickSignInGoogle() {
        signInGoogle.click();
    }

    public SignInComponent clickBackToSignIn() {
        backToSignIn.click();

        return new SignInComponent(driver);
    }

    public String getTitle() {
        return title.getText();
    }

    public ForgotPasswordComponent unfocus() {
        title.click();

        return this;
    }
}
