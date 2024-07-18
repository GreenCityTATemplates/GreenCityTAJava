package com.ita.edu.greencity.tests.ui.pages.ubs_homepage;

import com.ita.edu.greencity.tests.utils.RetryAnalyzer;
import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.employees.Employees;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import com.ita.edu.greencity.utils.ValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UbsHomePageTest {

    protected static ValueProvider provider;
    protected WebDriver driver;

    @BeforeClass(description = "Make chromedriver setup")
    public void beforeSuite(ITestContext iTestContext) throws IOException {
        for (ITestNGMethod method : iTestContext.getAllTestMethods()) {
            method.setRetryAnalyzerClass(RetryAnalyzer.class);
        }
        WebDriverManager.chromedriver().setup();
        provider = new ValueProvider();
    }

    @BeforeMethod(description = "Configure chromedriver and go to UbsHomePageURL")
    public void beforeMethod(ITestContext iTestContext) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(provider.getUbsHomePageURL());
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem("language", "en");
        driver.navigate().refresh();
        iTestContext.setAttribute("driver", driver);

        SignInComponent signInComponent = new SignInComponent(driver);
        BasePage basePage = new BasePage(driver);
        Employees employees = new Employees(driver);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        SelectRegion selectRegion = new SelectRegion(driver);
        ubsHomePage.clickSingInButton();
        signInComponent.inputEmail(provider.getEmailAdmin()).inputPassword(provider.getPasswordAdmin());
//        basePage.implicitWait(12000);
        employees.loadData("//ul[@role='tablist']//a[contains(text(),'UBS courier')]");
        ubsHomePage.clickUBSCourierButtonBarMenu();
    }

    @AfterMethod(description = "Quite chromedriver")
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }

    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Description("Check if url is current")
    @Issue("")
    @Severity(SeverityLevel.CRITICAL)
    public void checkUbsCurrentsUrl() {
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        String actual = driver.getCurrentUrl();
        System.out.println(actual);
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Check if image on display")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkImgArmoredTrackOnDisplay() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        BasePage basePage = new BasePage(driver);
        String xpath = "//div[1]/img[@src='assets/img/ubs/armored_truck.svg']";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        Assert.assertTrue(ubsHomePage.checkImgArmoredTrackOnDisplay());

    }

    @Test
    @Description("Check if text equals sample")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkIfUbsTextEqualsEnLang() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "UBS Courier";
        Assert.assertEquals(ubsHomePage.getTextHeaderMainText(), expected);
    }

    @Test
    @Description("Check if correct text not equal with sample")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkErrorIfUbsTextEqualsEnLang() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "wrong!!!";
        Assert.assertNotEquals(ubsHomePage.getTextHeaderMainText(), expected);
    }

    @Test
    @Description("Check if style of text is correct")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkStyleOfElement() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "Lato, sans-serifsdfsdfsd";
        Assert.assertNotEquals(ubsHomePage.getHeaderText(), expected);
    }

    @Test
    @Description("Check if text equals sample")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void getSection4MiddleTextEnLang() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "We are accepting Fabric wastes (damaged clothes) for the utilization in a separate package of 120L size or 120L size";
        Assert.assertEquals(ubsHomePage.getSectionFirstMiddleText(), expected);
    }

    @Test
    @Description("Check if url is current")
    @Issue("")
    @Severity(SeverityLevel.CRITICAL)
    public void checkOrderCurrentUrl() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver)
                .clickHeaderButtonOrderCourier();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        SelectRegion selectRegion = new SelectRegion(driver);
        selectRegion.clickOnContinueButton();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs/order";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Check if text equals sample")
    @Issue("")
    @Severity(SeverityLevel.CRITICAL)
    public void checkIfTextEquals() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "The UBS Courier service will save your time and money for getting to the sorting station. And as a bonus, you will receive a free delivery of our eco-products around Kyiv.";
        Assert.assertEquals(ubsHomePage.getMiddleText(), expected);
    }

    @Test
    @Description("Check if element on display")
    @Issue("")
    @Severity(SeverityLevel.CRITICAL)
    public void checkIfDivHowToPrepareOnDisplay() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        Assert.assertTrue(ubsHomePage.checkIfDivHowToPrepare());
    }

    @Test
    @Description("Check whether the number of crayfish matches the specified number")
    @Issue("")
    @Severity(SeverityLevel.NORMAL)
    public void checkLengthListHowToPrepare() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        BasePage basePage = new BasePage(driver);
        basePage.implicitWait(5000);
        int expected = 7;
        List<WebElement> UlElement = driver.findElements(By.xpath("//section[1]//*/div[@class='header-right']/ul[1]//*"));
        List<WebElement> actual = new ArrayList<>();
        actual.addAll(UlElement);
//        UlElement.forEach(i->actual.add(i));
        System.out.println(actual.size());
        Assert.assertEquals(actual.size(), expected);
    }

    @Test
    @Description("Сheck that the text in the lines matches the specified text ")
    @Issue("")
    @Severity(SeverityLevel.NORMAL)
    public void checkElementsInListHowToPrepare() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        BasePage basePage = new BasePage(driver);
        basePage.implicitWait(5000);
        List<String> expected = Arrays.asList(
                ("check out our Rules of sorting wastes (link)."),
                ("Everything you give to the Courier must be clean and dry – it’s Must Have."),
                ("Recyclables and non-recyclable plastics can be put together in one package /sack of a 120 liters size - we will re-sort everything."),
                ("We are accepting Fabric wastes (damaged clothes) for the utilization in a separate package of 120L size or 120L size"),
                ("1 package/sack should be up to 30 kg."),
                ("If you don't have a package/sack of the necessary size you can get it right from the Courier or order a whole roll of packages in our store and pack the wastes beforehand so you will save a big amount of time."),
                ("The courier picks up at least 2 packages within Kyiv. Driving a long distance to take only 1 package is not environmentally friendly, and it significantly increases the waiting time for each customer. If you live within a radius of 20 km from Kyiv - the Courier takes out 20 packages of 120 liters.")
        );
        List<WebElement> UlElement = driver.findElements(By.xpath("//section[1]//*/div[@class='header-right']/ul[1]//*"));
        List<String> actual = new ArrayList<>();
        UlElement.forEach(i -> actual.add(i.getText()));
        System.out.println(actual.size());
        System.out.println(expected.size());
        Assert.assertEquals(actual, expected);
    }


    @Test
    @Description("Check if button is displayed")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkButton() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        Assert.assertTrue(ubsHomePage.checkIfButtonOnDisplay());
    }

    @Test
    @Description("Check if text is correct")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkTextPriceUbsCourier() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "The cost of the service “UBS Courier";
        Assert.assertEquals(ubsHomePage.checkTextPriceUbsCourier(), expected);
    }

    @Test
    @Description("Check if element on display")
    @Issue("")
    @Severity(SeverityLevel.CRITICAL)
    public void checkIfBurgerMenuonDisplay() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        Assert.assertTrue(ubsHomePage.checkIfBurgerMenuOnDisplay());
    }

    @Test
    @Description("Check if ...")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void clickBurgerMenu() {
        driver.manage().window().setSize(new Dimension(900, 900));
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        ubsHomePage.clickBurgerMenu();
        List<WebElement> listOfMenu = driver.findElements(By.xpath("//ul[@role='tablist']//a"));
        System.out.println(listOfMenu.size());
        System.out.println(listOfMenu);
        int expected = 7;
        Assert.assertEquals(listOfMenu.size(), expected);
    }

    @Test
    @Description("Check if ...")
    @Issue("")
    @Severity(SeverityLevel.NORMAL)
    public void printListOfname() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        System.out.println(ubsHomePage.getListOfAllImgValue().size());
        System.out.println(ubsHomePage.getListOfAllImgValue());
        for (WebElement i : ubsHomePage.getListOfAllImgValue()) System.out.println(i.getAttribute("alt"));
    }


}



