package com.ita.edu.greencity.ui.pages.user_data;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class UserData extends BasePage {

    @FindBy(how = How.CSS, using = "button.btn.btn-outline-success.edit")
    private WebElement editData;

    @FindBy(how = How.XPATH, using = "(//p[@class = 'ng-star-inserted'])[1]")
    private WebElement name;

    @FindBy(how = How.XPATH, using = "(.//*[contains(@class, 'personal inline')]/div/label)[1]")
    private WebElement nameLabelText;

    @FindBy(how = How.XPATH, using = "(//p[@class = 'ng-star-inserted'])[2]")
    private WebElement surname;

    @FindBy(how = How.XPATH, using = "(.//*[contains(@class, 'personal inline')]/div/label)[2]")
    private WebElement surnameLabelText;

    @FindBy(how = How.XPATH, using = "(//p[@class = 'ng-star-inserted'])[3]")
    private WebElement email;

    @FindBy(how = How.XPATH, using = "(.//*[contains(@class, 'contacts inline')]/div/label)[1]")
    private WebElement emailLabelText;

    @FindBy(how = How.XPATH, using = "(//p[@class = 'ng-star-inserted'])[4]")
    private WebElement phone;

    @FindBy(how = How.XPATH, using = "(.//*[contains(@class, 'contacts inline')]/div/label)[2]")
    private WebElement phoneLabelText;

    @FindBy(how = How.CSS, using = "button.btn.btn-outline-success.open")
    private WebElement changePassword;

    @FindBy(how = How.CSS, using = "button.btn.btn-outline-success.delete")
    private WebElement deleteProfile;

    @FindBy(how = How.XPATH, using = ".//*[contains(@formarrayname, 'address')]")
    private List<WebElement> allAdresses;

    public UserData(WebDriver driver) {
        super(driver);
    }

    @Step("click on 'Edit data' button")
    public EditUserData clickOnEditDataButton() {
        this.sleep(3000);
        editData.click();
        return new EditUserData(driver);

    }

    @Step("get data from 'Name' field")
    public String getTextFromNameField() {
        return name.getText();
    }

    @Step("get data from 'Surname' field")
    public String getTextFromSurnameField() {
        return surname.getText();
    }

    @Step("get data from 'Email' field")
    public String getTextFromEmailField() {
        return email.getText();
    }

    @Step("get data from 'Phone' field")
    public String getTextFromPhoneField() {
        return phone.getText();
    }

    @Step("get data from 'Name' label")
    public String getTextFromNameLabel() {
        return nameLabelText.getText();
    }

    @Step("get data from 'Surname' label")
    public String getTextFromSurnameLabel() {
        return surnameLabelText.getText();
    }

    @Step("get data from 'Email' label")
    public String getTextFromEmailLabel() {
        return emailLabelText.getText();
    }

    @Step("get data from 'Phone' label")
    public String getTextFromPhoneLabel() {
        return phoneLabelText.getText();
    }

    @Step("get data from 'Edit data' button")
    public String getTextFromEditDataButton() {
        return editData.getText();
    }

    @Step("get data from 'Change password' button")
    public String getTextFromChangePasswordButton() {
        return changePassword.getText();
    }

    @Step("get data from 'Delete profile' button")
    public String getTextFromDeleteProfileButton() {
        return deleteProfile.getText();
    }


    @Step("click on 'Change password' button")
    public ChangePassword clickOnChangePasswordButton() {
        this.sleep(10000);
        changePassword.click();
        return new ChangePassword(driver);
    }

    @Step("click on 'Delete profile' button")
    public DeleteProfile clickOnDeleteProfileButton() {
        deleteProfile.click();
        return new DeleteProfile(driver);
    }

    private List<DisplayAddressContainer> getAddress() {
        List<DisplayAddressContainer> addressContainerList = new ArrayList<>();
        for (WebElement element : allAdresses) {
            addressContainerList.add(new DisplayAddressContainer(element));
        }
        return addressContainerList;
    }

    @Step("choose number of address, which you want to get data")
    public DisplayAddressContainer chooseAddressShow(String number) {
        this.sleep(3000);
        return getAddress()
                .stream()
                .filter(element -> element.getAddressNumberShow().equals(number))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }


}







