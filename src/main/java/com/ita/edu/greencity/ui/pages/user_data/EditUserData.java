package com.ita.edu.greencity.ui.pages.user_data;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class EditUserData extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[@id='recipientName']")
    private WebElement editName;

    @FindBy(how = How.XPATH, using = "//*[@id='recipientSurname']")
    private WebElement editSurname;

    @FindBy(how = How.XPATH, using = "//*[@id='recipientPhone']")
    private WebElement editPhone;

    @FindBy(how = How.CSS, using = "button.btn.btn-outline-success.cancel")
    private WebElement discardChanges;

    @FindBy(how = How.CSS, using = "button.btn.btn-success")
    private WebElement saveChanges;

    @FindBy(how = How.XPATH, using = ".//*[contains(@formarrayname, 'address')]")
    private List<WebElement> adressTable;

    public EditUserData(WebDriver driver) {
        super(driver);
    }

    @Step("set value {newName} in 'Name' field on 'Edit User Data' page")
    public EditUserData enterEditedName(final String newName) {
        editName.clear();
        editName.sendKeys(newName);
        return this;
    }

    @Step("set value {newSurname} in 'Surname' field on 'Edit User Data' page")
    public EditUserData enterEditedSurname(final String newSurname) {
        editSurname.clear();
        editSurname.sendKeys(newSurname);
        return this;
    }

    @Step("set value {newPhone} in 'Phone' field on 'Edit User Data' page")
    public EditUserData enterEditedPhone(final String newPhone) {
        editPhone.clear();
        editPhone.sendKeys(newPhone);
        return this;
    }

    @Step("click on 'Discard Changes' button on 'Edit User Data' page")
    public UserData clickOnDiscardChangesButton() {
        discardChanges.click();
        return new UserData(driver);
    }

    @Step("click on 'Save changes' button on 'Edit User Data' page")
    public UserData clickOnSaveChangesButton() {
        saveChanges.click();
        return new UserData(driver);
    }

    private List<EditAddressContainer> getAddress() {
        List<EditAddressContainer> addressContainerList = new ArrayList<>();
        for (WebElement element : adressTable) {
            addressContainerList.add(new EditAddressContainer(element));
        }
        return addressContainerList;
    }

    @Step("choose number of address, which you want to edit")
    public EditAddressContainer chooseAddress(String numberAddress) {
        this.sleep(3000);
        return getAddress()
                .stream()
                .filter(element -> element.getAddressNumber().equals(numberAddress))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }


}
