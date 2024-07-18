package com.ita.edu.greencity.ui.pages.user_data;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class DisplayAddressContainer {

    @FindBy(how = How.CSS, using = "p.ng-star-inserted")
    private List<WebElement> dataAddressTable;
    @FindBy(how = How.CSS, using = "h5")
    private WebElement addressNumberShow;
    @FindBy(how = How.CSS, using = "label.form-label")
    private List<WebElement> addressLabels;

    public DisplayAddressContainer(WebElement rootElement) {
        DefaultElementLocatorFactory parentContext = new DefaultElementLocatorFactory(rootElement);
        PageFactory.initElements(parentContext, this);
    }

    @Step("get address number data")
    public String getAddressNumberShow() {
        return addressNumberShow.getText();
    }

    @Step("get data from 'city' field on UserData page")
    public String getCity() {
        return dataAddressTable.get(0).getText();
    }

    @Step("get data from 'region' field on UserData page")
    public String getRegion() {
        return dataAddressTable.get(1).getText();
    }

    @Step("get data from 'district' field on UserData page")
    public String getDistrict() {
        return dataAddressTable.get(2).getText();
    }

    @Step("get data from 'street' field on UserData page")
    public String getStreet() {
        return dataAddressTable.get(3).getText();
    }

    @Step("get data from 'house' field on UserData page")
    public String getHouse() {
        return dataAddressTable.get(4).getText();
    }

    @Step("get data from 'corpus' field on UserData page")
    public String getCorpus() {
        return dataAddressTable.get(5).getText();
    }

    @Step("get data from 'entrance' field on UserData page")
    public String getEntrance() {
        return dataAddressTable.get(6).getText();
    }

    @Step("get data from 'city' label on UserData page")
    public String getCityLabel() {
        return addressLabels.get(0).getText();
    }

    @Step("get data from 'region' label on UserData page")
    public String getRegionLabel() {
        return addressLabels.get(1).getText();
    }

    @Step("get data from 'district' label on UserData page")
    public String getDistrictLabel() {
        return addressLabels.get(2).getText();
    }

    @Step("get data from 'street' label on UserData page")
    public String getStreetLabel() {
        return addressLabels.get(3).getText();
    }

    @Step("get data from 'house' label on UserData page")
    public String getHouseLabel() {
        return addressLabels.get(4).getText();
    }

    @Step("get data from 'corpus' label on UserData page")
    public String getCorpusLabel() {
        return addressLabels.get(5).getText();
    }

    @Step("get data from 'entrance' label on UserData page")
    public String getEntranceLabel() {
        return addressLabels.get(6).getText();
    }


}
