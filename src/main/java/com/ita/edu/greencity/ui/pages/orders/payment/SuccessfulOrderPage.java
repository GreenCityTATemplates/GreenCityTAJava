package com.ita.edu.greencity.ui.pages.orders.payment;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SuccessfulOrderPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//div/h2[@class = 'title']")
    private WebElement successfulOrderMessage;
    @FindBy(how = How.XPATH, using = "//a[@routerlink]")
    private WebElement makeOtherOrderButton;

    public SuccessfulOrderPage(WebDriver driver) {
        super(driver);
    }

    @Step("Read the message about a successful order")
    public String getTextFromSuccessfulOrderMessage() {
        sleep(5000);
        return successfulOrderMessage.getText();
    }

    @Step("Make other order")
    public SelectRegion clickOnMakeOtherOrderButton() {
        sleep(5000);
        makeOtherOrderButton.click();
        return new SelectRegion(driver);
    }

}
