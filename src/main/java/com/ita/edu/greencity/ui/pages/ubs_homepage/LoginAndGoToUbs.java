package com.ita.edu.greencity.ui.pages.ubs_homepage;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginAndGoToUbs extends BasePage {
    @FindBy(how = How.CSS, using = "a.ubs-header-sign-in")
    private WebElement loginButton;

    @FindBy(how = How.CSS, using = "input[id = email]")
    private WebElement email;

    @FindBy(how = How.CSS, using = "input[id = password]")
    private WebElement password;

    @FindBy(how = How.CSS, using = "button.ubs-primary-global-button")
    private WebElement buttonForSingIn;

    @FindBy(how = How.CSS, using = "button.btn")
    private WebElement nextButton;

    public LoginAndGoToUbs(WebDriver driver) {
        super(driver);
    }

//___________________________________________________

    public void pressLoginButton() {
        loginButton.click();
    }

    public void typeEmail(String emailInput) {
        email.sendKeys(emailInput);
    }

    public void typePassword(String passwordInput) {
        password.sendKeys(passwordInput);
    }

    public void pressButtonForSingIn() {
        buttonForSingIn.click();
    }

    public void pressNextButton() {
        nextButton.click();
    }

}
