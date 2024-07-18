package com.ita.edu.greencity.ui.pages.admin_menu;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.UBS_admin.UBSAdmin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class AdminMenu extends BasePage {
    @FindBy(how = How.CSS, using = "a.header_user-name")
    private WebElement menu;
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'drop-down-item')]")
    private List<WebElement> dropDowmElements;

    public AdminMenu(WebDriver driver) {
        super(driver);
    }

    public void clickOnSettings() {
        dropDowmElements.get(0).click();
    }

    public void clickOnManagment() {
        dropDowmElements.get(1).click();
    }

    public void clickOnSignOut() {
        dropDowmElements.get(2).click();
    }

    public UBSAdmin clickOnUBSAdmin() {
        dropDowmElements.get(3).click();
        return new UBSAdmin(driver);
    }

    public AdminMenu clickOnUBSAdminMenu() {
        this.sleep(5000);
        menu.click();
        return this;
    }


}
