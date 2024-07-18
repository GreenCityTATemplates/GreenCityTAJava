package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrderDetailsPage extends BasePage {

    @FindBy(xpath = "//div[@class='user-location']//h5")
    private WebElement locationTitle;
    @FindBy(xpath = "//button[@class='change-location']")
    private WebElement changeRegionButton;
    @FindBy(xpath = ".//div[@class='user-location']//select[@name='region']")
    private WebElement changeRegionDropdown;
    @FindBy(xpath = ".//select[@name ='region']//option")
    private List<WebElement> regionButtons;
    @FindBy(xpath = "//input[@id='quantity2']")
    private WebElement NumberOfTextileWaste120lInput;
    @FindBy(xpath = "//input[@id='quantity1']")
    private WebElement NumberOfSafeWasteInput;
    @FindBy(xpath = "//input[@id='quantity3']")
    private WebElement NumberOfTextileWaste20lInput;
    @FindBy(xpath = "//div[@class='main']//span[@class='col-3 bag-name text-right']")
    private List<WebElement> totalPrice;
    @FindBy(xpath = "//div[@class='totalInfo']//strong")
    private List<WebElement> totalAmount;
    @FindBy(xpath = ".//div[@formarrayname='formArrayCertificates']/input")
    private WebElement certificateInput;
    @FindBy(xpath = "//button[@class='primary-global-button btn ng-star-inserted']")
    private WebElement activateOrCancelCertificateButton;
    @FindBy(xpath = "//div[@class='messages-container']/small")
    private WebElement certificateAlert;
    @FindBy(xpath = "//div[@class='totalInfo']//small[contains(text(),'500')]")
    private WebElement minimumOrderAmountAlert;
    @FindBy(xpath = "//div[@class='totalInfo']//small[contains(text(),'120')]")
    private WebElement minimumOrderContainsAlert;
    @FindBy(xpath = "//div[@class='points']//span[@class='checkmark']")
    private List<WebElement> UseBonusesCheckmarks;
    @FindBy(xpath = "//div[@class='points']/p")
    private WebElement bonusesText;
    @FindBy(xpath = "//a[@class='bonus-how-to-link']")
    private WebElement howToGetBonusesButton;
    @FindBy(xpath = "//div[@class='bottom']//span[@class='checkmark']")
    private List<WebElement> WaitingStoreOrderCheckmarks;
    @FindBy(xpath = "//input[contains(@id,'index')]")
    private List<WebElement> orderNumberInputs;
    @FindBy(xpath = "//div[@class='bottom-text']//p")
    private WebElement tipTextHint;
    @FindBy(xpath = "//button[@class='addOrderBtn']")
    private WebElement addAnotherNumberButton;
    @FindBy(xpath = "//div[@class='order-notification ng-star-inserted']/small")
    private WebElement ecoStoreNumberAlert;
    @FindBy(xpath = "//p[@class='link']")
    private WebElement learnAboutPackagesLink;
    @FindBy(xpath = "//textarea[@formcontrolname='orderComment']")
    private WebElement commentInput;
    @FindBy(css = "div.bottom_comment small")
    private WebElement commentErrorMessage;
    @FindBy(xpath = "//button[@class='secondary-global-button btn cancel-button btn-main']")
    private WebElement cancelOrderButton;
    @FindBy(xpath = "//button[@class='primary-global-button btn btn-main']")
    private WebElement nextButton;

    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getLocationFromTitle() {
        return locationTitle.getText();
    }

    public String getTipTextHint() {
        return tipTextHint.getText();
    }

    public WebElement getCommentErrorMessage() {
        return commentErrorMessage;
    }

    public WebElement getNextButton() {
        return nextButton;
    }


    public OrderDetailsPage clickOnChangeRegionButton() {
        changeRegionButton.click();
        return this;
    }

    public OrderDetailsPage clickOnchangeRegionDropdown() {
        changeRegionDropdown.click();
        return this;
    }

    public OrderDetailsPage clickOnRegionByIndex(int index) {
        regionButtons.get(index).click();

        return this;
    }

    @Step("Region dropdown chose  {value}")
    public OrderDetailsPage chooseRegionByValue(String value) {
        clickOnChangeRegionButton();
        sleep(1000);
        clickOnchangeRegionDropdown();
        sleep(1000);
        for (WebElement option : regionButtons) {
            if (option.getText().equals(value.trim())) {
                option.click();
                break;
            }
        }
        return this;
    }

    @Step("Number of 'Textile waste 120l' service input value {value}")
    public OrderDetailsPage EnterNumberOfTextileWaste120lInput(String value) {
        sleep(3000);
        NumberOfTextileWaste120lInput.sendKeys(value, Keys.ENTER);
        return this;
    }

    public OrderDetailsPage EnterNumberOfTextileWaste120lArrowsInput(int value) {

        for (int x = 0; x < value; x++) {
            NumberOfTextileWaste120lInput.sendKeys(Keys.ARROW_UP);
        }
        return this;
    }

    @Step("Number of 'Safe waste' service input value {value}")
    public OrderDetailsPage EnterNumberOfSafeWasteInput(String value) {
        NumberOfSafeWasteInput.sendKeys(value, Keys.ENTER);
        return this;
    }

    @Step("Number of 'Textile waste 20l' service input value {value}")
    public OrderDetailsPage EnterNumberOfTextileWaste20lInput(String value) {
        NumberOfTextileWaste20lInput.sendKeys(value, Keys.ENTER);
        return this;
    }

    public String getTextileWaste120lSum() {
        String price = totalPrice.get(0).getText();
        return price;
    }

    public String getSaveWasteSum() {
        String price = totalPrice.get(1).getText();
        return price;
    }

    public String getTextileWaste20lSum() {
        String price = totalPrice.get(2).getText();
        return price;
    }


    public String getOrderAmount() {
        String price = totalAmount.get(0).getText();
        return price;
    }

    public String getAmountDue() {
        String price = totalAmount.get(2).getText();
        return price;
    }

    public String getMinimumOrderAmountAlertText() {
        String text = minimumOrderAmountAlert.getText();
        return text;
    }

    public String getMinimumOrderContainsAlertText() {
        String text = minimumOrderContainsAlert.getText();
        return text;
    }

    @Step("Certificate input set value {value}")
    public OrderDetailsPage EnterCertificateInput(String value) {
        certificateInput.click();
        certificateInput.sendKeys(value, Keys.ENTER);
        return this;
    }

    public OrderDetailsPage clickOnActivateCertificateButton() {
        activateOrCancelCertificateButton.click();
        sleep(10000);
        return this;
    }

    public OrderDetailsPage clickOnCancelCertificateButton() {
        activateOrCancelCertificateButton.click();
        return this;
    }

    public boolean getCertificateButtonStatus() {
        return activateOrCancelCertificateButton.isEnabled();
    }

    public String getCertificateAlertMessage() {
        return certificateAlert.getText().trim();
    }

    public String getCertificateInput() {
        sleep(20000);
        return certificateInput.getAttribute("value");
    }

    public OrderDetailsPage ClickOnNoUseBonusesCheckmark() {
        UseBonusesCheckmarks.get(0).click();
        return this;
    }

    public OrderDetailsPage ClickOnYesUseBonusesCheckmark() {
        UseBonusesCheckmarks.get(1).click();
        return this;
    }

    public String getBonusesNumber() {
        String value = bonusesText.getText().replaceAll("[^\\d.]", "");
        return value;
    }

    public OrderDetailsPage ClickOnNoWaitingStoreOrderCheckmark() {
        WaitingStoreOrderCheckmarks.get(0).click();
        return this;
    }


    public OrderDetailsPage clickOnYesWaitingStoreOrderCheckmark() {
        WaitingStoreOrderCheckmarks.get(1).click();
        return this;
    }


    public OrderDetailsPage clickOnHowToGetBonusesButton() {
        howToGetBonusesButton.click();
        return this;
    }

    @Step("Input eco store order {order} value {orderNumber}")
    public OrderDetailsPage EnterOrderNumberInputs(String orderNumber, int order) {
        orderNumberInputs.get(order).sendKeys(orderNumber);
        return this;
    }

    public OrderDetailsPage clickOnAddAnotherNumberButton() {
        addAnotherNumberButton.click();
        return this;
    }

    public String getOrderNumberInputs(int order) {
        return orderNumberInputs.get(order).getAttribute("value");
    }

    public String getEcoStoreOrderAlert() {
        return ecoStoreNumberAlert.getText();
    }

    public OrderDetailsPage clickOnLearnAboutPackagesLink() {
        learnAboutPackagesLink.click();
        return this;
    }

    @Step("Comment input set value {value}")
    public OrderDetailsPage EnterCommentInput(String value) {
        commentInput.sendKeys(value, Keys.ENTER);
        return this;
    }

    public String getCommentInput() {
        return commentInput.getAttribute("value");
    }

    public OrderDetailsPage clickOnCancelOrderButton() {
        cancelOrderButton.click();
        return this;
    }

    public OrderPagePersonalData clickOnNextButton() {
        nextButton.click();
        sleep(3000);
        return new OrderPagePersonalData(driver);
    }

    public boolean checkNextButtonStatus() {
        return nextButton.isEnabled();
    }


}