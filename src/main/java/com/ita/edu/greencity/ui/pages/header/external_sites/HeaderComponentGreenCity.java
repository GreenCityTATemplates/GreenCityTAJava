package com.ita.edu.greencity.ui.pages.header.external_sites;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderComponentGreenCity extends BasePage {

    @FindBy(how = How.XPATH, using = "//a[contains(@class,'header_sign-in-link tertiary-global-button')]")
    private WebElement signInButton;

    public HeaderComponentGreenCity(WebDriver driver) {
        super(driver);
    }

    @Step("Click on Sign in button")
    public SignInComponent clickOnSignInButton() {
        signInButton.click();
        return new SignInComponent(driver);
    }
}
