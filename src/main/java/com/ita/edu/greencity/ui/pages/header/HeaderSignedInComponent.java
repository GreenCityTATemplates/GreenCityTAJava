package com.ita.edu.greencity.ui.pages.header;

import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.UbsUserOrders;
import com.ita.edu.greencity.ui.pages.user_data.EditUserData;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderSignedInComponent extends HeaderComponent {

    @FindBy(how = How.XPATH, using = "//*[@id='header_user-wrp']/li[1]/a")
    private WebElement userMenu;

    @FindBy(how = How.XPATH, using = "//*[@id='header_user-wrp']/li[2]/a")
    private WebElement settings;

    @FindBy(how = How.XPATH, using = "//*[@id='header_user-wrp']/li[3]/a")
    private WebElement signOut;

    @FindBy(how = How.XPATH, using = "//*[@id='header_user-wrp']/li[4]/a")
    private WebElement ubsUser;

    public HeaderSignedInComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Click on User menu button")
    public HeaderSignedInComponent clickUserMenu() {
        this.sleep(3000);
        userMenu.click();
        return this;
    }

    @Step("Click on Settings button")
    public EditUserData clickSettings() {
        settings.click();
        return new EditUserData(driver);
    }

    @Step("Click on Sign out button")
    public UbsHomePage clickSignOut() {
        signOut.click();
        return new UbsHomePage(driver);
    }

    @Step("Click on UBS-user button")
    public UbsUserOrders clickUbsUser() {
        sleep(3000);
        ubsUser.click();
        return new UbsUserOrders(driver);
    }

    @Step("Get user name")
    public String getUserName() {
        sleep(500);
        return userMenu.getText();
    }

    @Step("Get text from Settings button")
    public String getSettingsButtonText() {
        return settings.getText();
    }

    @Step("Get text from Sign out button")
    public String getSignOutButtonText() {
        return signOut.getText();
    }

    @Step("Get text from Ubs User button")
    public String getUbsUserButtonText() {
        return ubsUser.getText();
    }
}
