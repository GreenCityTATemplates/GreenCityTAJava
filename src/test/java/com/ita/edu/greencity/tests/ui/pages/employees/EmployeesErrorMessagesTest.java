package com.ita.edu.greencity.tests.ui.pages.employees;

import com.ita.edu.greencity.tests.utils.RetryAnalyzer;
import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.employees.Employees;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import com.ita.edu.greencity.utils.ValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class EmployeesErrorMessagesTest {

    protected static ValueProvider provider;
    final String xpathErroeMessageAddEmployeeName = "/html/body/div[2]/div[3]/div/snack-bar-container/simple-snack-bar/span";
    final String expectedErrorName = "firstName: must match \"[ЁёІіЇїҐґЄєА-Яа-яA-Za-z-'\\s.]{1,30}\"";
    final String expectedErrorPhone = "phoneNumber: Invalid phone number format.";
    final String expectedErrorSurname = "lastName: must match \"[ЁёІіЇїҐґЄєА-Яа-яA-Za-z-'\\s.]{1,30}\"";
    protected WebDriver driver;
    String CORRECT_MENU_NAME = "Leonard";
    String NOT_CORRECT_MENU_NAME = "Leonard1";
    String CORRECT_MENU_SURNAME = "Hofstadter";
    String NOT_CORRECT_MENU_SURNAME = "Hofstadter2";
    String CORRECT_MENU_PHONE = "676706767";
    String CORRECT_MENU_EMAIL = "test@ukr.net";

    @BeforeClass(description = "Make chromedriver setup")
    public void beforeSuite(ITestContext iTestContext) throws IOException {
        for (ITestNGMethod method : iTestContext.getAllTestMethods()) {
            method.setRetryAnalyzerClass(RetryAnalyzer.class);
        }
        WebDriverManager.chromedriver().setup();
        provider = new ValueProvider();
        System.out.println("0000");
    }

    @BeforeMethod
    public void beforeMethod(ITestContext iTestContext) {
        String xpathLoaderEmployeesPage = "//mat-spinner[@role = 'progressbar']";
        BasePage basePage = new BasePage(driver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(provider.getUbsHomePageURL());
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem("language", "en");
        driver.navigate().refresh();
        iTestContext.setAttribute("driver", driver);
        basePage.sleep(1000);
        System.out.println("1111");

        SignInComponent signInComponent = new SignInComponent(driver);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        SelectRegion selectRegion = new SelectRegion(driver);
        Employees employees = new Employees(driver);
        ubsHomePage.clickSingInButton();
        signInComponent.inputEmail(provider.getEmailAdmin()).inputPassword(provider.getPasswordAdmin());
//        employees.clickSingInButton();
        System.out.println("2222");
        basePage.sleep(12000);

        employees.clickAdminPopMenu();
        basePage.sleep(5000);
        System.out.println("3333");
        employees.clickButtonUbsAdmin();
        basePage.sleep(5000);
        System.out.println("4444");
        employees.clickEmployyesButton();
        employees.loadData(xpathLoaderEmployeesPage);
        System.out.println("5555");
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

    //
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void exampleMethod(String name, String surname, String phone) {
        Employees employees = new Employees(driver);
        BasePage basePage = new BasePage(driver);
        basePage.sleep(5000);
        employees.pressButtonAddEmployee();
        System.out.println("6666");
        employees.sendKeysNameArr(name);
        basePage.sleep(5000);
        employees.sendKeysSurnameArr(surname);
        employees.sendKeysPhoneArr(phone);
        System.out.println("7777");
        basePage.sleep(5000);
        employees.sendKeysEmailArr(CORRECT_MENU_EMAIL);
        employees.pressButtonServiceManager();
        basePage.sleep(5000);
        employees.pressButtonSetectRegionAddMenu();
        System.out.println("8888");
        employees.pressButtonAddEmployeeAddMenu();
        basePage.sleep(5000);
        employees.loadData(xpathErroeMessageAddEmployeeName);
        System.out.println("9999");

    }

    @Description("test name error messages")
    @Issue("")
    @Test()
    public void checkErrorMessageName() {
        exampleMethod(NOT_CORRECT_MENU_NAME, CORRECT_MENU_SURNAME, CORRECT_MENU_PHONE);
        System.out.println("-----");
        String actual = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/snack-bar-container/simple-snack-bar/span")).getText();
        System.out.println(actual);
        System.out.println("====");
        Assert.assertEquals(actual, expectedErrorName);
    }

    @Description("test name error messages")
    @Issue("")
    @Test()
    public void checkErrorMessageSurname() {
        exampleMethod(CORRECT_MENU_NAME, NOT_CORRECT_MENU_SURNAME, CORRECT_MENU_PHONE);
        System.out.println("-----");
        String actual = driver.findElement(By.xpath("//simple-snack-bar[@class='mat-simple-snackbar ng-star-inserted']//span")).getText();
        System.out.println(actual);
        System.out.println("====");
        Assert.assertEquals(actual, expectedErrorSurname);
    }

    @Description("test name error messages")
    @Issue("")
    @Test()
    public void checkErrorMessagePhone() {
        exampleMethod(CORRECT_MENU_NAME, NOT_CORRECT_MENU_SURNAME, CORRECT_MENU_PHONE);
        System.out.println("-----");
        String actual = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/snack-bar-container")).getText();
        System.out.println(actual);
        System.out.println("====");
        Assert.assertEquals(actual, expectedErrorPhone);
    }


}
