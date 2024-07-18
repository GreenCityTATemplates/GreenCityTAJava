package com.ita.edu.greencity.ui.pages.user_data;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DeleteProfile extends BasePage {

    @FindBy(how = How.XPATH, using = "//mat-dialog-actions/button[1]")
    private WebElement backButton;

    @FindBy(how = How.XPATH, using = "//mat-dialog-actions/button[2]")
    private WebElement deleteButton;

    public DeleteProfile(WebDriver driver) {
        super(driver);
    }

    public UserData clickOnBackButton() {
        backButton.click();
        return new UserData(driver);
    }

    public UserData clickOnDeleteButton() {
        deleteButton.click();
        return new UserData(driver);
    }
}
