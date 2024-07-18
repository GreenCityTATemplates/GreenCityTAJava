package com.ita.edu.greencity.ui.pages.header;

import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderSignedOutComponent extends HeaderComponent {

    @FindBy(how = How.XPATH, using = "//*[@class='ubs-header-sign-in']")
    private WebElement signIn;

    @FindBy(how = How.XPATH, using = "//*[@class='ubs-header_sign-up-btn']")
    private WebElement signUp;

    public HeaderSignedOutComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Click on Sign in button")
    public SignInComponent clickSignIn() {
        signIn.click();
        return new SignInComponent(driver);
    }

    @Step("Click on Sign up button")
    public SignUpComponent clickSignUp() {
        signUp.click();
        return new SignUpComponent(driver);
    }

    @Step("Check the visibility of Sign In button")
    public boolean checkSignInButtonIsVisible() {
        return signIn.isDisplayed();
    }

    @Step("Check the visibility of Sign Up button")
    public boolean checkSignUpButtonIsVisible() {
        return signUp.isDisplayed();
    }

    @Step("Get text from Sign in button")
    public String getSignInButtonText() {
        return signIn.getText();
    }

    @Step("Get text from Sign up button")
    public String getSignUpButtonText() {
        return signUp.getText();
    }
}