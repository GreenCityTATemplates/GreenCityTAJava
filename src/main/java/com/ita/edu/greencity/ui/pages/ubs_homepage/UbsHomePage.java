package com.ita.edu.greencity.ui.pages.ubs_homepage;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class UbsHomePage extends BasePage {


    @FindBy(how = How.XPATH, using = "//a[@class='ubs-header-sign-in']")
    private WebElement singInButton;

    @FindBy(how = How.XPATH, using = "//div[1]/img[@src='assets/img/ubs/armored_truck.svg']")
    private WebElement imgArmoredTrack;

    @FindBy(how = How.XPATH, using = "//*/h1")
    private WebElement headerMainText;

    @FindBy(how = How.XPATH, using = "//*/header/div[@class='header-right']/*/p")
    private WebElement headerText;

    @FindBy(how = How.XPATH, using = "//div[@class='ubsHomepage']//*/div[@class='main-content']/button")
    private WebElement headerButtonOrderCourier;

    @FindBy(how = How.XPATH, using = "//h1")
    private WebElement h1Text;

    @FindBy(how = How.XPATH, using = "//*[@class='section-header']")
    private WebElement middleText;

    @FindBy(how = How.XPATH, using = "/html/body/app-root/app-ubs/app-ubs-main-page/div/header/div[2]/div/button")
    private WebElement orderCourier;

    @FindBy(how = How.XPATH, using = "//*[@class='ubs-polygon']")
    private WebElement arrImg;

    @FindBy(how = How.XPATH, using = "//*/section/img[@class='ubs-image']")
    private WebElement selectionImg;

    @FindBy(how = How.XPATH, using = "//*/section/div[@class='header-right']/*/h2")
    private WebElement sectionMainText;

    @FindBy(how = How.XPATH, using = "//div[@id='how-works-content']//following-sibling::button")
    private WebElement middleButtonOrderCourier;

    @FindBy(how = How.XPATH, using = "//div[@class='header-right']//ul//li[4]")
    private WebElement section4MiddleText;

    @FindBy(how = How.XPATH, using = "//*[@id='main-content']/button")
    private WebElement secondButtonOrderCourier;

    @FindBy(how = How.XPATH, using = "//div[@class='main-content']/*/button")
    private WebElement footerButton;

    @FindBy(how = How.XPATH, using = "//section/div[@class='how-preparing'][1]")
    private WebElement divHowToPrepare;

    @FindBy(how = How.XPATH, using = "//section[1]//*/div[@class='header-right']/ul[1]//*")
    private WebElement listOfHowToPrepare;

    @FindBy(how = How.XPATH, using = "//*[@id='mat-dialog-2']/app-ubs-order-location-popup/div/mat-dialog-actions/button[2]")
    private WebElement chooseLocationPopUpContinueButton;

    @FindBy(how = How.XPATH, using = "//div[@class='how-preparing']/div[@class='header-right']/div[@class='main-content']")
    private WebElement divNecessaryRole;

    @FindBy(how = How.XPATH, using = "//h2[@class='price-header']")
    private WebElement priceUbsCourier;

    @FindBy(how = How.XPATH, using = "//header/div[@class = 'header-right']/div/h2")
    private WebElement homePageTitle;

    @FindBy(how = How.XPATH, using = "//div[@class='menu-icon-wrapper']")
    private WebElement burgerMenu;

    @FindBy(how = How.XPATH, using = "//ul[@role='tablist']//a[contains(text(),'UBS courier')]")
    private WebElement UBSCourierButtonBarMenu;


    @FindBy(how = How.XPATH, using = "//ul[@role='tablist']")
    private WebElement elementsInBurgerMenu;

    @FindBy(how = How.XPATH, using = "//div//img")
    private List<WebElement> listOfAllImgValue;

    public UbsHomePage(WebDriver driver) {
        super(driver);
    }

    //__________________________________________________
    public List<WebElement> getListOfAllImgValue() {
        return listOfAllImgValue;
    }

    public void clickSingInButton() {
        singInButton.click();
    }


    public boolean checkImgArmoredTrackOnDisplay() {
        return imgArmoredTrack.isDisplayed();
    }

    public String getTitleH1Text() {
        return h1Text.getText();
    }

    public SignInComponent pressOrderCourierUnlogin() {
        sleep(3000);
        headerButtonOrderCourier.click();
        return new SignInComponent(driver);
    }

    public SelectRegion pressOrderCourierLogin() {
        sleep(3000);
        headerButtonOrderCourier.click();
        return new SelectRegion(driver);
    }

    public String getTextHeaderMainText() {
        return headerMainText.getText();
    }

    public String getHeaderText() {
        return headerText.getCssValue("font-family");

    }

    public UbsHomePage clickHeaderButtonOrderCourier() {
        headerButtonOrderCourier.click();
        return null;
    }

    @Step("get text from title of message")
    public String getMiddleText() {
        return middleText.getText();

    }

    public String getHomePageTitle() {
        return homePageTitle.getText();
    }

    public boolean checkIfDivHowToPrepare() {
        return divHowToPrepare.isDisplayed();

    }

    public String getSectionFirstMiddleText() {
        return section4MiddleText.getText();
    }

    @Step("looking for element on page by xpath{headerButtonOrderCourier}")
    public boolean checkIfButtonOnDisplay() {
        return headerButtonOrderCourier.isDisplayed();
    }

    public String checkTextPriceUbsCourier() {
        return priceUbsCourier.getText();
    }

    public boolean checkIfBurgerMenuOnDisplay() {
        return burgerMenu.isDisplayed();
    }

    @Step("make click on button")
    public UbsHomePage clickBurgerMenu() {
        burgerMenu.click();
        return null;
    }


    public void clickUBSCourierButtonBarMenu() {
        UBSCourierButtonBarMenu.click();
    }

//    public UbsHomePage getSectionMainText() {
//        sectionMainText.click();
//        return this;
//    }

//    public int  getListOfHowToPrepare(){
//        String x = listOfHowToPrepare.getAttribute("href");
//        return x;
//    }
//        public UbsHomePage getVal(){
//        middleButtonOrderCourier.getTagName();
//        return this;
//    }

//    public void clickChooseLocationPopUpContinueButton() {
//        chooseLocationPopUpContinueButton.click();
//    }

    //    public int getStatusCode() {
//        headerButtonOrderCourier.get;
//    }

//    public UbsHomePage getTypeOfHeaderButtonOrderCourier() {
//        headerButtonOrderCourier.getText();
//        return this;
//    }

}