package com.ita.edu.greencity.ui.pages.google_account;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PersonalGoogleAccountPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//span[@email = 'mailgreencity1@gmail.com']")
    private List<WebElement> letterList;

    @FindBy(how = How.XPATH, using = "//td[contains(@style,'border-radius')]//a[contains(@href,'https://ita-social-projects.github.io/GreenCityClient/#/')]")
    private WebElement verifyEmailButton;

    public PersonalGoogleAccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on letter to verify email")
    public PersonalGoogleAccountPage clickOnLetter() {
        sleep(100);
        letterList.get(1).click();
        return this;
    }

    @Step("Click on verify email button")
    public HeaderSignedOutComponent clickOnVerifyEmailButton() {
        sleep(700);
        verifyEmailButton.click();
        switchToNewTab();
        sleep(500);
        return new HeaderSignedOutComponent(driver);
    }
}
