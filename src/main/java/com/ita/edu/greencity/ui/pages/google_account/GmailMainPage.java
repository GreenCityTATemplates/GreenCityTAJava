package com.ita.edu.greencity.ui.pages.google_account;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GmailMainPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//a[@data-action='sign in']")
    private WebElement signInButton;

    public GmailMainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on Sign in button on gmail.com")
    public GoogleSignInInputEmailPopUp clickOnSignInButton() {
        signInButton.click();
        return new GoogleSignInInputEmailPopUp(driver);
    }
}
