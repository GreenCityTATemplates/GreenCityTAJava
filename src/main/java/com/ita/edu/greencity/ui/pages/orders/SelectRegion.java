package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SelectRegion extends BasePage {

    @FindBy(xpath = "//div[@class='title']/h5")
    private WebElement title;
    @FindBy(xpath = "//*[@class = 'close-btn']")
    private WebElement closeButton;
    @FindBy(xpath = "//button[@class='btn secondaryButton secondary-global-button']")
    private WebElement backButton;
    @FindBy(xpath = "//button[@class='btn primaryButton primary-global-button']")
    private WebElement continueButton;
    @FindBy(xpath = "//select[@name='region']")
    private WebElement regionDropdown;
    @FindBy(xpath = "//select[@name='region']/option[@class ='ng-star-inserted']")
    private List<WebElement> listOfRegions;
    @FindBy(xpath = "//p[@class='text ng-star-inserted']")
    private WebElement bottomText;

    public SelectRegion(WebDriver driver) {
        super(driver);
    }

    @Step("get title text from the  pop-up after logging in")
    public String getTitleText() {
        this.sleep(7000);
        return title.getText();
    }

    @Step("get dropdown options list from pop-up after logging in")
    public List<WebElement> getListOfRegions() {
        return listOfRegions;
    }

    @Step("get bottom text from the  pop-up after logging in")
    public String getBottomText() {
        return bottomText.getText();
    }

    @Step("get Continue button text from the  pop-up after logging in")
    public String getContinueButtonText() {
        return continueButton.getText();
    }

    @Step("get Back button text from the  pop-up after logging in")
    public String getBackButtonText() {
        return backButton.getText();
    }

    @Step("click on Close button at pop-up after logging in")
    public UbsHomePage clickOnCloseButton() {
        closeButton.click();
        return new UbsHomePage(driver);
    }

    @Step("click on Back button at pop-up after logging in")
    public UbsHomePage clickOnBackButton() {
        backButton.click();
        return new UbsHomePage(driver);
    }

    @Step("click on Continue button at pop-up after logging in")
    public OrderDetailsPage clickOnContinueButton() {
        waitUntilElementToBeClickable(By.xpath("//button[@class='btn primaryButton primary-global-button']"), 60);
        continueButton.click();
        return new OrderDetailsPage(driver);
    }

    @Step("click on Select Region dropdown at pop-up after logging in")
    public void clickOnRegionDropdown() {
        regionDropdown.click();
    }

    @Step("choose region by index {index}")
    public SelectRegion chooseRegionByIndex(int index) {
        sleep(5000);
        clickOnRegionDropdown();
        listOfRegions.get(index).click();
        return this;
    }

    @Step("choose region by String value {value}")
    public SelectRegion chooseRegionByValue(String value) {
        sleep(7000);
        clickOnRegionDropdown();
        for (WebElement option : listOfRegions) {
            if (option.getText().equals(value.trim())) {
                option.click();
                break;
            }
        }
        return this;
    }

}

