package com.ita.edu.greencity.ui.pages.ubs_user;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Messages extends BasePage {

    @FindBy(how = How.XPATH, using = "//*[@class = 'if-empty ng-star-inserted']")
    private WebElement emptyMessagesPageLabel;

    public Messages(WebDriver driver) {
        super(driver);
    }

    public String getEmptyMessagesPageLabel() {
        return emptyMessagesPageLabel.getText();
    }

    public UbsUser getUbsUserPage() {
        return new UbsUser(driver);
    }
}
