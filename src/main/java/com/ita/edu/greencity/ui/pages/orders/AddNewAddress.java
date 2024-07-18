package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class AddNewAddress extends BasePage {

    @FindBy(how = How.XPATH, using = "//h2[@class ='personal-info-pop-up-title']")
    private WebElement newAddressTitle;

    @FindBy(how = How.XPATH, using = "//select[contains(@formcontrolname, 'region')]")
    private WebElement regionField;

    @FindBy(how = How.XPATH, using = "//select[contains(@formcontrolname, 'city')]")
    private WebElement cityField;

    @FindBy(how = How.XPATH, using = "//select[contains(@formcontrolname, 'district')]")
    private WebElement districtField;

    @FindBy(how = How.XPATH, using = "//select[contains(@formcontrolname, 'district')]//option")
    private List<WebElement> listOfDistricts;

    @FindBy(how = How.XPATH, using = "//select[contains(@formcontrolname, 'city')]//option[@class= 'ng-star-inserted']")
    private List<WebElement> listOfCities;

    @FindBy(how = How.XPATH, using = "//input[contains(@formcontrolname, 'street')]")
    private WebElement streetField;

    @FindBy(how = How.XPATH, using = "//input[contains(@formcontrolname, 'houseNumber')]")
    private WebElement houseNumberField;

    @FindBy(how = How.XPATH, using = "//input[contains(@formcontrolname, 'houseCorpus')]")
    private WebElement houseCorpusField;

    @FindBy(how = How.XPATH, using = "//input[contains(@formcontrolname, 'entranceNumber')]")
    private WebElement entranceNumberField;

    @FindBy(how = How.XPATH, using = "//button[@class = 'secondary-global-button btn m-0 mr-2']")
    private WebElement cancelButton;

    @FindBy(how = How.XPATH, using = "//div[@class ='mat-dialog-actions d-flex justify-content-end buttons']/button[@class = 'primary-global-button btn m-0']")
    private WebElement addAddressButton;

    @FindBy(how = How.XPATH, using = "//span[@class='pac-matched']")
    private List<WebElement> listOfStreet;

    @FindBy(how = How.XPATH, using = "//form/div[6]/div[1]/div/app-ubs-input-error/div")
    private WebElement houseErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[6]/div[2]/div/app-ubs-input-error/div")
    private WebElement corpusErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[6]/div[3]/div/app-ubs-input-error/div")
    private WebElement entranceErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[7]/div/app-ubs-input-error/div")
    private WebElement commentToTheAddressErrorMessage;


    @FindBy(how = How.XPATH, using = "//input[@class ='shadow-none form-control ng-star-inserted pac-target-input ng-touched ng-dirty ng-invalid']")
    private WebElement streetFieldAIsEmpty;

    @FindBy(how = How.XPATH, using = "//input[@class ='shadow-none form-control ng-star-inserted pac-target-input ng-touched ng-dirty ng-valid']")
    private WebElement streetFieldAIsFiled;

    @FindBy(how = How.XPATH, using = "//div[@class = 'form-group comment-section']/textarea[@formcontrolname='addressComment']")
    private WebElement commentToTheAddress;


    public AddNewAddress(WebDriver driver) {
        super(driver);
    }

    @Step("get comment")
    public String getCommentToTheAddress() {
        return commentToTheAddress.getAttribute("value");
    }

    @Step("get webElement 'streetFieldAreFiled'")
    public WebElement getStreetFieldAIsFiled() {
        return streetFieldAIsFiled;
    }

    @Step("get webElement 'streetFieldAreEmpty'")
    public WebElement getStreetFieldAIsEmpty() {
        return streetFieldAIsEmpty;
    }

    @Step("get text from entrance error message")
    public String getTextFromEntranceErrorMessage() {
        return entranceErrorMessage.getText();
    }

    @Step("get text from corpus error message")
    public String getTextFromCorpusErrorMessage() {
        return corpusErrorMessage.getText();
    }

    @Step("get text from house error message")
    public String getTextFromHouseErrorMessage() {
        return houseErrorMessage.getText();
    }

    @Step("choose street by index of list")
    public AddNewAddress chooseStreet(int index) {
//        waitUntilElementToBeClickable(By.xpath("//span[@class='pac-matched']"),10);
        this.sleep(2000);
        listOfStreet.get(index).click();
        this.sleep(2000);
        return this;
    }

    @Step("get webElement 'addAddressButton'")
    public WebElement getAddAddressButton() {
        return addAddressButton;
    }

    @Step("click on add address button")
    public OrderPagePersonalData clickOnAddAddressButton() {
        sleep(5000);
        addAddressButton.click();
        return new OrderPagePersonalData(driver);
    }

    @Step("click on cancel button")
    public OrderPagePersonalData clickOnCancelButton() {
        cancelButton.click();
        return new OrderPagePersonalData(driver);
    }

    @Step("enter comment to the address")
    public AddNewAddress enterAddressComment(final String addressComment) {
        commentToTheAddress.clear();
        commentToTheAddress.sendKeys(addressComment, Keys.ENTER);
        return this;
    }

    @Step("enter entrance")
    public AddNewAddress enterEntranceNumber(final String houseCorpus) {
        entranceNumberField.clear();
        entranceNumberField.sendKeys(houseCorpus, Keys.ENTER);
        return this;
    }

    @Step("enter house corpus")
    public AddNewAddress enterHouseCorpus(final String houseCorpus) {
        houseCorpusField.clear();
        houseCorpusField.sendKeys(houseCorpus, Keys.ENTER);
        return this;
    }

    @Step("enter house number")
    public AddNewAddress enterHouseNumber(final String houseNumber) {
        houseNumberField.clear();
        houseNumberField.sendKeys(houseNumber, Keys.ENTER);
        return this;
    }

    @Step("enter street")
    public AddNewAddress enterStreet(final String street) {
        streetField.clear();
        streetField.sendKeys(street);
        return this;
    }

    @Step("choose city by index of list")
    public AddNewAddress chooseCity(int index) {
        clickOnCityField();
        listOfCities.get(index).click();
        return this;
    }

    @Step("choose district by index of list")
    public AddNewAddress chooseDistrict(int index) {
        clickOnDistrictField();
        listOfDistricts.get(index).click();
        return this;
    }

    @Step("click on district field")
    public AddNewAddress clickOnDistrictField() {
        districtField.click();
        return this;
    }

    @Step("click on city field")
    public AddNewAddress clickOnCityField() {
        cityField.click();
        return this;
    }

    @Step("get text from region field")
    public String getTextFromRegionField() {
        return regionField.getText();
    }

    @Step("get text from new address title")
    public String getTextFromNewAddressTitle() {
        return newAddressTitle.getText();
    }

    public AddNewAddress addAddress(int indexCity, int indexDistrict, String street, int indexStreet, String numberOfHouse) {
        AddNewAddress addNewAddress = new AddNewAddress(driver).clickOnCityField()
                .chooseCity(indexCity)
                .chooseDistrict(indexDistrict)
                .enterStreet(street)
                .chooseStreet(indexStreet)
                .enterHouseNumber(numberOfHouse);
        return this;
    }

    public OrderPagePersonalData addFullAddress(int indexCity, int indexDistrict, String street, int indexStreet, String numberOfHouse, String corpusNumber, String entranceNumber) {
        OrderPagePersonalData orderPagePersonalData = new AddNewAddress(driver)
                .clickOnCityField()
                .chooseCity(indexCity)
                .chooseDistrict(indexDistrict)
                .enterStreet(street)
                .chooseStreet(indexStreet)
                .enterHouseNumber(numberOfHouse)
                .enterHouseCorpus(corpusNumber)
                .enterEntranceNumber(entranceNumber)
                .enterStreet(street)
                .chooseStreet(indexStreet)
                .clickOnAddAddressButton();
        sleep(20000);
        return new OrderPagePersonalData(driver);
    }

}