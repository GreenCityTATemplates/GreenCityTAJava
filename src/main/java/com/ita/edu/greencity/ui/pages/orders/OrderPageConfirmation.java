package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.locators.UtilsLocators;
import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.orders.payment.PaymentByFondyPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Arrays;
import java.util.List;


public class OrderPageConfirmation extends BasePage {

    @FindBy(how = How.XPATH, using = "//mat-step-header[@aria-posinset=1]/div[@class = 'mat-ripple mat-step-header-ripple']")
    private WebElement orderDetailsButton;
    @FindBy(how = How.XPATH, using = "//mat-step-header[@aria-posinset=2]/div[@class = 'mat-ripple mat-step-header-ripple']")
    private WebElement personalDataButton;

    @FindBy(how = How.XPATH, using = "//div[@class = 'container ng-star-inserted']/div/h3")
    private WebElement yourOrderTitle;
    @FindBy(how = How.XPATH, using = "//div[@class = 'w-100 d-flex justify-content-between mb-2']/label[contains(@class,'p-0')]")
    private List<WebElement> headersList;
    @FindBy(how = How.XPATH, using = "//app-ubs-submit-order//form/div[3]//p/span[2]/strong")
    private List<WebElement> totalSumList;

    @FindBy(how = How.XPATH, using = "//select[contains(@class, 'payment-select shadow-none')]")
    private WebElement paymentChooser;
    @FindBy(how = How.XPATH, using = "//button[contains(@class,'btn-back ng-star-inserted')]")
    private WebElement backButton;
    @FindBy(how = How.XPATH, using = "//button[contains(@class,'cansel-button btn-main')]")
    private WebElement cancelButton;
    @FindBy(how = How.XPATH, using = "//button[@class = 'primary-global-button btn']")
    private WebElement orderButton;

    @FindBy(how = How.XPATH, using = "//ul[@class = 'order-list ng-star-inserted']/li")
    private List<WebElement> recipientCredentialsList;
    @FindBy(how = How.XPATH, using = "//ul[@class = 'order-list']/li")
    private List<WebElement> infoAboutExportAddressList;
    @FindBy(how = How.XPATH, using = "//ul[@class = 'order-list d-flex']/li/strong")
    private List<WebElement> ecoStoreOrderNumbersList;

    public OrderPageConfirmation(WebDriver driver) {
        super(driver);
    }

    @Step("Navigate to Order details stage")
    public OrderDetailsPage clickOnOrderDetailsButton() {
        orderDetailsButton.click();
        return new OrderDetailsPage(driver);
    }

    @Step("Navigate to Personal data stage")
    public OrderPagePersonalData clickOnPersonalDataButton() {
        personalDataButton.click();
        return new OrderPagePersonalData(driver);
    }

    @Step("Get title from table with orders")
    public String getTitle() {
        return yourOrderTitle.getText();
    }

    @Step("Get text from appropriate table header accordingly to its index")
    public String getNecessaryHeader(int index) {
        return headersList.get(index).getText();
    }

    @Step("Get text from any table element accordingly to the row and the column where it is located")
    public String chooseOneElementFromYourOrderTable(int row, int column) {
        sleep(5000);
        return driver.findElement(By.xpath("//ul[@class = 'services-list w-100 p-0 m-0']/li[" + row + "]/span[" + column + "]"))
                .getText();
    }

    @Step("Get an appropriate total sum of the order accordingly to the index")
    public String getTotalSumWithCurrency(int index) {
        sleep(5000);
        return totalSumList.get(index).getText();
    }

    private void clickOnPaymentChooser() {
        paymentChooser.click();
    }

    public WebElement paymentOption(String value) {
        return driver.findElement(By.xpath("//select[contains(@class, 'payment-select shadow-none')]/option[@value = '" + value + "']"));
    }

    @Step("Choose Fondy as a payment method")
    public OrderPageConfirmation choosePaymentMethod() {
        clickOnPaymentChooser();
        paymentOption("Fondy").click();
        return this;
    }

    public String getRecipientName() {
        return Arrays.stream(recipientCredentialsList.get(0).getText().split("\s")).toList().get(0);
    }

    public String getRecipientSurname() {
        return Arrays.stream(recipientCredentialsList.get(0).getText().split("\s")).toList().get(1);
    }

    public String getRecipientPhoneNumber() {
        return recipientCredentialsList.get(1).getText();
    }

    public String getRecipientEmailAddress() {
        return recipientCredentialsList.get(2).getText();
    }

    public String getCity() {
        return infoAboutExportAddressList.get(0).getText();
    }

    public String getDistrict() {
        return infoAboutExportAddressList.get(1).getText();
    }

    public String getStreet() {
        return Arrays.stream(infoAboutExportAddressList.get(2).getText().split(", ")).toList().get(0);
    }

    public String getHouseNumber() {
        return Arrays.stream(infoAboutExportAddressList.get(2).getText().split(", ")).toList().get(1);
    }

    public String getCorpus() {
        return Arrays.stream(infoAboutExportAddressList.get(2).getText().split(", ")).toList().get(2);
    }

    public String getEntrance() {
        return Arrays.stream(infoAboutExportAddressList.get(2).getText().split(", ")).toList().get(3);
    }

    public String getRegion() {
        return infoAboutExportAddressList.get(3).getText();
    }


    @Step("Get number of order from Eco store by its index")
    public String getEcoStoreNumber(int index) {
        return ecoStoreOrderNumbersList.get(index).getText().replaceAll("[^0-9]", "");
    }

    @Step("Return to the previous stage with personal data")
    public OrderPagePersonalData clickOnBackButton() {
        backButton.click();
        return new OrderPagePersonalData(driver);
    }

    @Step("Cancel the order")
    public OrderSavingPopUp clickOnCancelButton() {
        sleep(3000);
        cancelButton.click();
        return new OrderSavingPopUp(driver);
    }

    @Step("Make the order")
    public PaymentByFondyPage clickOnOrderButton() {
        sleep(5000);
        orderButton.click();
        loadData();
        return new PaymentByFondyPage(driver);
    }

    private PaymentByFondyPage loadData() {
        while (true) {
            try {
                driver.findElement(UtilsLocators.SPINNER.getPath());
            } catch (Exception e) {
                return new PaymentByFondyPage(driver);
            }
            sleep(500);
        }
    }

}
