package com.ita.edu.greencity.ui.pages.header;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChangeLanguageAlert extends BasePage {

    protected WebDriverWait wait;

    public ChangeLanguageAlert(WebDriver driver) {
        super(driver);
    }

    private void acceptAlertMessage() {
        driver.switchTo().alert().accept();
    }

    private void dismissAlertMessage() {
        driver.switchTo().alert().dismiss();
    }

    public OrderDetailsPage acceptAlert() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.alertIsPresent());
        acceptAlertMessage();
        return new OrderDetailsPage(driver);
    }

    public void dismissAlert() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.alertIsPresent());
        dismissAlertMessage();
    }
}
