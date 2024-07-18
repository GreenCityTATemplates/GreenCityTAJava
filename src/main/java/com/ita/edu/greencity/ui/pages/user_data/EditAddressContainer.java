package com.ita.edu.greencity.ui.pages.user_data;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class EditAddressContainer {
    WebDriver driver;
    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'city')]")
    private WebElement city;
    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'region')]")
    private WebElement region;
    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'district')]")
    private WebElement district;
    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'street')]")
    private WebElement street;
    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'houseNumber')]")
    private WebElement houseNumber;
    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'houseCorpus')]")
    private WebElement houseCorpus;
    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'entranceNumber')]")
    private WebElement entranceNumber;
    @FindBy(how = How.CSS, using = "h5")
    private WebElement addressNumber;
    @FindBy(how = How.XPATH, using = "(//*[contains(@type, 'submit')])[2]")
    private WebElement saveChanges;

    public EditAddressContainer(WebElement rootElement) {
        DefaultElementLocatorFactory parentContext = new DefaultElementLocatorFactory(rootElement);
        PageFactory.initElements(parentContext, this);
    }

    @Step("set value {cityInput} in 'city' field on 'Edit User Data' page")
    public EditAddressContainer setCityAddress(final String cityInput) {
        city.clear();
        city.sendKeys(cityInput);
        return this;
    }

    @Step("set value {regionInput} in 'region' field on 'Edit User Data' page")
    public EditAddressContainer setRegionAddress(final String regionInput) {
        region.clear();
        region.sendKeys(regionInput);
        return this;
    }

    @Step("set value {districtInput} in 'district' field on 'Edit User Data' page")
    public EditAddressContainer setDistrictAddress(final String districtInput) {
        district.clear();
        district.sendKeys(districtInput);
        return this;
    }

    @Step("set value {streetInput} in 'street' field on 'Edit User Data' page")
    public EditAddressContainer setStreetAddress(final String streetInput) {
        street.clear();
        street.sendKeys(streetInput);
        return this;
    }

    @Step("set value {houseInput} in 'house' field on 'Edit User Data' page")
    public EditAddressContainer setHouseNumberAddress(final String houseInput) {
        houseNumber.clear();
        houseNumber.sendKeys(houseInput);
        return this;
    }

    @Step("set value {corpusInput} in 'corpus' field on 'Edit User Data' page")
    public EditAddressContainer setHouseCorpusAddress(final String corpusInput) {
        houseCorpus.clear();
        houseCorpus.sendKeys(corpusInput);
        return this;
    }

    @Step("set value {entranceInput} in 'entrance' field on 'Edit User Data' page")
    public EditAddressContainer setEntranceNumberAddress(final String entranceInput) {
        entranceNumber.clear();
        entranceNumber.sendKeys(entranceInput);
        return this;
    }

    @Step("click on 'Save changes' button on 'Edit User Data' page")
    public UserData clickOnSaveChangesButton() {
        saveChanges.click();
        return new UserData(driver);
    }

    @Step("get address number on 'Edit User Data' page")
    public String getAddressNumber() {
        return addressNumber.getText();
    }

}
