package com.ita.edu.greencity.ui.pages.employees;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Employees extends BasePage {

    @FindBy(how = How.XPATH, using = "//div[@class='employee-add']//button")
    public WebElement buttonAddEmployees;
    //    @FindBy(how = How.XPATH, using = "//*[@id='mat-dialog-1']/app-auth-modal/div/div/div[2]/div/app-sign-in/form/button")
//    private WebElement singInButton;
    @FindBy(how = How.XPATH, using = "//ul[@id='header_user-wrp']")
    private WebElement adminPopMenu;

    @FindBy(how = How.XPATH, using = "//*[@id='header_user-wrp']/li[5]/a")
    private WebElement buttonUbsAdmin;

    @FindBy(how = How.XPATH, using = "/html/body/app-root/app-ubs-admin/app-ubs-admin-sidebar/app-ubs-base-sidebar/div/mat-drawer-container/mat-drawer/div/ul/li[4]")
    private WebElement buttonEmployees;
    @FindBy(how = How.XPATH, using = "/html/body/app-root/app-ubs-admin/app-ubs-admin-sidebar/app-ubs-base-sidebar/div/mat-drawer-container/mat-drawer-content/div/app-ubs-admin-employee/div/div[2]/app-ubs-admin-employee-table/div/mat-form-field/div/div[1]/div/input")
    private WebElement inputArr;
    @FindBy(how = How.XPATH, using = "//input[@formcontrolname='firstName']")
    private WebElement inputName;
    @FindBy(how = How.XPATH, using = "//input[@formcontrolname='lastName']")
    private WebElement inputSurname;
    @FindBy(how = How.XPATH, using = "//input[@formcontrolname='phoneNumber']")
    private WebElement inputPhone;
    @FindBy(how = How.XPATH, using = "//input[@formcontrolname='email']")
    private WebElement inputEmailArr;
    @FindBy(how = How.XPATH, using = "//div[@class='checkbox-role ng-star-inserted'][1]")
    private WebElement buttonServiceManager;
    @FindBy(how = How.XPATH, using = "//label[@class='checkbox-label-station']")
    private WebElement buttonSelectRegionInAddMenu;
    @FindBy(how = How.XPATH, using = "//button[@class='addButton']")
    private WebElement buttonAddEmployeeInAddMenu;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div[3]/div/snack-bar-container/simple-snack-bar/span")
    private WebElement errorNameMessage;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div[3]/div/snack-bar-container")
    private WebElement errorPhoneMessage;
    @FindBy(how = How.XPATH, using = "//simple-snack-bar[@class='mat-simple-snackbar ng-star-inserted']//span")
    private WebElement errorSurnameMessage;
    @FindBy(how = How.XPATH, using = "//button[@class='cancelButton']")
    private WebElement cancelButton;
    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Leonard')]")
    private WebElement locatorOfNewEmployee;
    @FindBy(how = How.XPATH, using = "//div//button[@class='delete-employee ng-star-inserted']")
    private WebElement locatorOfRemoveButton;
    @FindBy(how = How.XPATH, using = "//*[@id='mat-dialog-2']/app-dialog-pop-up/div/div[2]/button[2]")
    private WebElement locatorOfReaskRemoveButton;
    @FindBy(how = How.XPATH, using = "//button[@class='edit-employee-btn ng-star-inserted']")
    private WebElement locatorOfEditEmployeeButton;


    public Employees(WebDriver driver) {
        super(driver);
    }


//    @Step("click on singInButton")
//    public void clickSingInButton(){
//        singInButton.click();
//    }

    @Step("click on pop-up menu")
    public void clickAdminPopMenu() {
        adminPopMenu.click();
    }

    @Step("click on UBS-Admin button")
    public void clickButtonUbsAdmin() {
        buttonUbsAdmin.click();
    }

    @Step("press on Employees menu")
    public void clickEmployyesButton() {
        buttonEmployees.click();
    }

    @Step("check if button on display")
    public boolean checkIfButtonAddEmplOnDipl() {
        return buttonAddEmployees.isDisplayed();
    }

    @Step("check if input on display")
    public boolean checkIfInputOnDisplay() {
        return inputArr.isDisplayed();
    }


    @Step("input some string to InputArr")
    public Employees sendKeysInputArr(String inputText) {
        inputArr.clear();
        inputArr.sendKeys(inputText, Keys.ENTER);
        return this;
    }

    @Step("press on Add Employee")
    public void pressButtonAddEmployee() {
        buttonAddEmployees.click();
    }

    @Step("press on Cancel Button")
    public void pressCancelButton() {
        cancelButton.click();
    }


    @Step("input value to nameArr")
    public Employees sendKeysNameArr(String inputText) {
        inputName.clear();
        inputName.sendKeys(inputText);
        return this;
    }

    @Step("input value to surnameArr")
    public Employees sendKeysSurnameArr(String inputText) {
        inputSurname.clear();
        inputSurname.sendKeys(inputText);
        return this;
    }

    @Step("input value to phoneArr")
    public Employees sendKeysPhoneArr(String inputNum) {
        inputPhone.clear();
        inputPhone.sendKeys(inputNum);
        return this;
    }

    @Step("input value to emailArr")
    public Employees sendKeysEmailArr(String inputEmail) {
        inputEmailArr.clear();
        inputEmailArr.sendKeys(inputEmail);
        return this;
    }

    @Step("press on sevrice man")
    public void pressButtonServiceManager() {
        buttonServiceManager.click();
    }

    @Step("press on select region")
    public void pressButtonSetectRegionAddMenu() {
        buttonSelectRegionInAddMenu.click();
    }

    @Step("press on add empl")
    public void pressButtonAddEmployeeAddMenu() {
        buttonAddEmployeeInAddMenu.click();
    }

    @Step("press on locator Name")
    public void pressLocatorName() {
        locatorOfNewEmployee.click();
    }


    @Step("press on locator Name")
    public void pressLocatorRemove() {
        locatorOfRemoveButton.click();
    }

    @Step("press on locator Name")
    public void pressLocatorReaskRemoveButton() {
        locatorOfReaskRemoveButton.click();
    }

    @Step("")
    public void locatorOfName(String name) {
        String xpath = String.format("//span[contains(text(), '%s')]", name);
        driver.findElement(By.xpath(xpath)).click();
    }

    @Step("")
    public boolean checkIfLocatorOfNameOnDisplay(String name) {
        String xpath = String.format("//span[contains(text(), '%s')]", name);
        WebElement elem = driver.findElement(By.xpath(xpath));
        return elem.isDisplayed();
    }

    @Step("")
    public boolean checkLocatorOfEditEmployeeButton() {
        return locatorOfEditEmployeeButton.isDisplayed();
    }


    public Employees loadData(String xpath) {
        while (true) {
            try {
                driver.findElement(By.xpath(xpath));
            } catch (Exception e) {
                return this;
            }
            sleep(500);
        }
    }


    @Step("")
    public WebElement locatorOfElenent() {
        return buttonAddEmployees;

    }


}
