package com.ita.edu.greencity.ui.pages.UBS_admin;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.admin_customers.AdminCustomers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class UBSAdmin extends BasePage {
    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'sidebar-list-item-link')]")
    private List<WebElement> UBSadminItems;

    public UBSAdmin(WebDriver driver) {
        super(driver);
    }

    public AdminCustomers clickOnCustomers() {
        this.sleep(4000);
        UBSadminItems.get(0).click();
        return new AdminCustomers(driver);
    }

    public void clickOnCertificates() {
        UBSadminItems.get(1).click();
    }

    public void clickOnOrders() {
        UBSadminItems.get(2).click();
    }

    public void clickOnEmployees() {
        UBSadminItems.get(3).click();
    }

    public void clickOnDocuments() {
        UBSadminItems.get(4).click();
    }

    public void clickOnSchedule() {
        UBSadminItems.get(5).click();
    }

    public void clickOnTariffs() {
        UBSadminItems.get(6).click();
    }


}
