package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;


public class OrderPagePersonalData extends BasePage {

    @FindBy(how = How.XPATH, using = "//h2[@class ='personal-data-title']")
    private WebElement personalDataTitle;

    @FindBy(how = How.XPATH, using = "//h1[@class ='h1 text-center form-title']")
    private WebElement ubsTitle;


    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'firstName']")
    private WebElement firstNameField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'lastName']")
    private WebElement surnameField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'phoneNumber']")
    private WebElement phoneNumberField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'email']")
    private WebElement emailField;

    @FindBy(how = How.XPATH, using = "//input[@id= 'flexCheckDefault']")
    private WebElement anotherClientButton;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'anotherClientFirstName']")
    private WebElement anotherClientFirstNameField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'anotherClientLastName']")
    private WebElement anotherClientSurNameField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'anotherClientPhoneNumber']")
    private WebElement anotherClientPhoneNumberField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'anotherClientEmail']")
    private WebElement anotherClientEmailField;

    @FindBy(how = How.XPATH, using = "//h2[@class ='address-title']")
    private WebElement addressTitle;

    @FindBy(how = How.XPATH, using = "//button[@class ='btn add-address']")
    private WebElement addAddressButton;

    @FindBy(how = How.XPATH, using = "//h5[@class ='comment-title']")
    private WebElement commentTitle;

    @FindBy(how = How.XPATH, using = "//textarea[@formcontrolname= 'addressComment']")
    private WebElement addressCommentField;

    @FindBy(how = How.XPATH, using = "//button[@class = 'secondary-global-button btn back-btn m-0']")
    private WebElement backButton;

    @FindBy(how = How.XPATH, using = "//button[@class = 'secondary-global-button btn cansel-button']")
    private WebElement cancelButton;

    @FindBy(how = How.XPATH, using = "//button[@class = 'primary-global-button btn m-0']")
    private WebElement nextButton;

    @FindBy(how = How.XPATH, using = "//img[@alt = 'edit image']")
    private List<WebElement> editSavedAddress;

    @FindBy(how = How.XPATH, using = "//img[@alt = 'delete image']")
    private List<WebElement> deleteSavedAddress;

    @FindBy(how = How.XPATH, using = "//input[@name ='address']")
    private List<WebElement> chooseSavedAddress;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[2]/div/app-ubs-input-error/div")
    private WebElement nameErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[4]/div/app-ubs-input-error/div")
    private WebElement surnameErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[3]/div/app-ubs-input-error/div")
    private WebElement phoneNumberErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[5]/div/app-ubs-input-error/div")
    private WebElement emailErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[2]/div/app-ubs-input-error/div")
    private WebElement anotherClientNameErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[3]/div/app-ubs-input-error/div")
    private WebElement anotherClientSurnameErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[4]/div/app-ubs-input-error/div")
    private WebElement anotherClientPhoneNumberErrorMessage;


    @FindBy(how = How.XPATH, using = "//div[@class ='no-addresses ng-star-inserted']")
    private WebElement absenceAnyAddresses;

    @FindBy(how = How.XPATH, using = "//button[@class = 'secondary-global-button']")
    private WebElement continueButtonOnWarningBanner;

    @FindBy(how = How.XPATH, using = "//button[@class = 'primary-global-button']")
    private WebElement exitButtonOnWarningBanner;

    @FindBy(how = How.XPATH, using = "//div[@class = 'warning-title ng-star-inserted']")
    private WebElement warningTitle;

    @FindBy(how = How.XPATH, using = "//div[@class = 'warning-subtitle ng-star-inserted']")
    private WebElement warningSubtitle;

    public OrderPagePersonalData(WebDriver driver) {
        super(driver);
    }

    @Step("get webElement 'anotherClientFirstNameField'")
    public WebElement getAnotherClientFirstNameField() {
        return anotherClientFirstNameField;
    }

    @Step("get webElement 'anotherClientSurNameField'")
    public WebElement getAnotherClientSurNameField() {
        return anotherClientSurNameField;
    }

    @Step("get webElement 'anotherClientPhoneNumberField'")
    public WebElement getAnotherClientPhoneNumberField() {
        return anotherClientPhoneNumberField;
    }

    @Step("get webElement 'anotherClientEmailField'")
    public WebElement getAnotherClientEmailField() {
        return anotherClientEmailField;
    }

    @Step("get text from warning Subtitle")
    public String getTextFromWarningSubtitle() {
        return warningSubtitle.getText();
    }

    @Step("get text from warning title")
    public String getTextFromWarningTitle() {
        return warningTitle.getText();
    }


    @Step("click on continue button on warning banner")
    public OrderPagePersonalData clickOnContinueButtonOnWarningBanner() {
        continueButtonOnWarningBanner.click();
        return this;
    }

    @Step("click on exit button on warning banner")
    public UbsHomePage clickOnExitButtonOnWarningBanner() {
        exitButtonOnWarningBanner.click();
        return new UbsHomePage(driver);
    }


    @Step("get comment")
    public String getComment() {
        return addressCommentField.getAttribute("value");
    }

    @Step("get text from absence any addresses ")
    public String getTextFromAbsenceAnyAddresses() {
        return absenceAnyAddresses.getText();
    }


    @Step("get text from error message (Another client phone number field)")
    public String getTextFromAnotherClientPhoneNumberErrorMessage() {
        return anotherClientPhoneNumberErrorMessage.getText();
    }

    @Step("get text from error message (Another client surname field)")
    public String getTextFromAnotherClientSurnameErrorMessage() {
        return anotherClientSurnameErrorMessage.getText();
    }

    @Step("get text from error message (Another client name field)")
    public String getTextFromAnotherClientNameErrorMessage() {
        return anotherClientNameErrorMessage.getText();
    }

    @Step("get text from error message (Email field)")
    public String getTextFromEmailErrorMessage() {
        return emailErrorMessage.getText();
    }

    @Step("get text from error message (Phone number field)")
    public String getTextFromPhoneNumberErrorMessage() {
        return phoneNumberErrorMessage.getText();
    }

    @Step("get text from error message (Surname field)")
    public String getTextFromSurnameErrorMessage() {
        return surnameErrorMessage.getText();
    }

    @Step("get text from error message (Name field)")
    public String getTextFromNameErrorMessage() {
        return nameErrorMessage.getText();
    }

    @Step("click on delete collection button")
    public OrderPagePersonalData clickOnDeleteCollectionAddressButton(int indexOfSavedAddress) {
        sleep(3000);
        deleteSavedAddress.get(indexOfSavedAddress).click();
        return this;
    }

    @Step("click on edit address button")
    public OrderPagePersonalData clickOnEditAddressButton(int indexOfSavedAddress) {
        editSavedAddress.get(indexOfSavedAddress).click();
        return this;
    }

    @Step("click on choose saved address button")
    public OrderPagePersonalData clickOnChooseAddressButton(int indexOfSavedAddress) {
        sleep(5000);
        chooseSavedAddress.get(indexOfSavedAddress).click();
        return this;
    }

    @Step("check count of saved address")
    public boolean verifyCountOfAddress() {
        for (int address = 0; address < chooseSavedAddress.size(); address++) {
            if (address > 4)
                return false;
        }
        return true;
    }

    @Step("click on next button")
    public OrderPageConfirmation clickOnNextButton() {
        sleep(5000);
        nextButton.click();
        //sleep(5000);
        return new OrderPageConfirmation(driver);
    }

    @Step("click on cancel button")
    public OrderPagePersonalData clickOnCancelButton() {
        cancelButton.click();
        return this;
    }

    @Step("click on 'back' button")
    public OrderDetailsPage clickOnBackButton() {
        sleep(2000);
        backButton.click();
        return new OrderDetailsPage(driver);
    }

    @Step("enter comment into 'Address comment' field")
    public OrderPagePersonalData enterAddressComment(final String addressComment) {
        addressCommentField.clear();
        addressCommentField.sendKeys(addressComment, Keys.ENTER);
        return this;
    }

    @Step("get text title (Comment field)")
    public String getTextFromCommentTitle() {
        return commentTitle.getText();
    }

    @Step("click on add address button")
    public AddNewAddress clickOnAddAddressButton() {
        this.sleep(2000);
        addAddressButton.click();
        return new AddNewAddress(driver);
    }

    @Step("get text title (Address field)")
    public String getTextFromAddressTitle() {
        return addressTitle.getText();
    }

    @Step("enter email (Another client email field)")
    public OrderPagePersonalData enterAnotherClientEmail(final String email) {
        anotherClientEmailField.clear();
        anotherClientEmailField.sendKeys(email, Keys.ENTER);
        return this;
    }

    @Step("enter phone number (Another client phone number field)")
    public OrderPagePersonalData enterAnotherClientPhoneNumber(final String phoneNumber) {
        anotherClientPhoneNumberField.clear();
        anotherClientPhoneNumberField.sendKeys(phoneNumber, Keys.ENTER);
        return this;
    }

    @Step("enter surname (Another client surname field)")
    public OrderPagePersonalData enterAnotherClientLastName(final String lastName) {
        anotherClientSurNameField.clear();
        anotherClientSurNameField.sendKeys(lastName, Keys.ENTER);
        return this;
    }

    @Step("enter first name (Another client first name field)")
    public OrderPagePersonalData enterAnotherClientFirstName(final String firstName) {
        anotherClientFirstNameField.clear();
        anotherClientFirstNameField.sendKeys(firstName, Keys.ENTER);
        return this;
    }

    @Step("click on another client checkbox")
    public OrderPagePersonalData clickOnAnotherClientButton() {
        anotherClientButton.click();
        return this;
    }

    @Step("enter email (Email field)")
    public OrderPagePersonalData enterEmail(final String email) {
        emailField.clear();
        emailField.sendKeys(email, Keys.ENTER);
        return this;
    }

    @Step("enter phone number (Phone number field)")
    public OrderPagePersonalData enterPhoneNumber(final String phoneNumber) {
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber, Keys.ENTER);
        return this;
    }

    @Step("enter surname (Surname field)")
    public OrderPagePersonalData entersurname(final String lastName) {
        surnameField.clear();
        surnameField.sendKeys(lastName, Keys.ENTER);
        return this;
    }

    @Step("enter first name (First name field)")
    public OrderPagePersonalData enterFirstName(final String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName, Keys.ENTER);
        return this;
    }

    @Step("click on ubsTitle")
    public OrderPagePersonalData clickForGetMessage() {
        ubsTitle.click();
        return this;
    }

    @Step("get text from title")
    public String getTextFromPersonalDataTitle() {
        return personalDataTitle.getText();
    }

    @Step("Refresh page")
    public OrderDetailsPage refreshPage() {
        sleep(3000);
        driver.navigate().refresh();
        return new OrderDetailsPage(driver);
    }

    @Step("Verify that the address is deleted")
    public boolean checkAddressIsDeleted() {
        sleep(3000);
        if (deleteSavedAddress.size() == 1) {
            return true;
        }
        return false;
    }
}