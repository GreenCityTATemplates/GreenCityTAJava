package com.ita.edu.greencity.ui.pages.admin_customers;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ExportToExcel extends BasePage {
    @FindBy(how = How.XPATH, using = "//mat-radio-button")
    private List<WebElement> allRadioButton;
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'mat-dialog-actions d-flex')]/button")
    private List<WebElement> allButton;

    public ExportToExcel(WebDriver driver) {
        super(driver);
    }

    @Step("select 'Save current view' option")
    public ExportToExcel selectSaveCurrentView() {
        this.sleep(2000);
        allRadioButton.get(0).click();
        return this;
    }

    @Step("select 'Save all table' option")
    public ExportToExcel selectSaveAllTable() {
        allRadioButton.get(1).click();
        return this;
    }

    @Step("click on 'Cancel' button")
    public void cancelButton() {
        allButton.get(0).click();
    }

    @Step("click on 'Next' button")
    public void nextButton() {
        allButton.get(1).click();
    }

}


