package com.ita.edu.greencity.ui.pages.user_data;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ChangePassword extends BasePage {

    @FindBy(how = How.XPATH, using = "//*[contains(@formcontrolname, 'currentPassword')]")
    private WebElement oldPassword;

    @FindBy(how = How.XPATH, using = "//*[contains(@formcontrolname, 'password')]")
    private WebElement newPassword;

    @FindBy(how = How.XPATH, using = "//*[contains(@formcontrolname, 'confirmPassword')]")
    private WebElement repeatNewPassword;

    @FindBy(how = How.XPATH, using = "//mat-dialog-actions/button[2]")
    private WebElement changePasswordButton;

    @FindBy(how = How.XPATH, using = "//mat-dialog-actions/button[1]")
    private WebElement cancelChangingPasswordButton;

    @FindBy(how = How.XPATH, using = "//mat-form-field[contains(@class, 'ng-valid')]/following::mat-error[1]")
    private WebElement errorMessageTheSamePassword;

    @FindBy(how = How.XPATH, using = "//mat-form-field[contains(@class, 'ng-valid')]/following::mat-error[1]")
    private WebElement errorMessageDontMatchPassword;

    public ChangePassword(WebDriver driver) {
        super(driver);
    }

    @Step("enter data in field 'old password'")
    public ChangePassword enterOldPassword(final String oldPasswordData) {
        oldPassword.clear();
        oldPassword.sendKeys(oldPasswordData);
        return this;
    }

    @Step("enter data in field 'new password'")
    public ChangePassword enterNewPassword(final String newPasswordData) {
        newPassword.clear();
        newPassword.sendKeys(newPasswordData);
        return this;
    }

    @Step("enter data in field 'repeat new password'")
    public ChangePassword enterRepeatNewPassword(final String repeatPasswordData) {
        repeatNewPassword.clear();
        repeatNewPassword.sendKeys(repeatPasswordData);
        return this;
    }

    @Step("click on 'Change password' button")
    public UserData clickOnChangePasswordButton() {
        changePasswordButton.click();
        return new UserData(driver);
    }

    @Step("click on 'Cancel' button")
    public UserData clickOnCancelChangingPasswordButton() {
        cancelChangingPasswordButton.click();
        return new UserData(driver);
    }

    @Step("get error message when the new password is the same as the old one")
    public String getErrorMessageTheSamePassword() {
        return errorMessageTheSamePassword.getText();
    }

    @Step("get error message when the data in 'the new password' and 'repeat the password' fields are different")
    public String getErrorMessageDontMatchPassword() {
        return errorMessageDontMatchPassword.getText();
    }

}
