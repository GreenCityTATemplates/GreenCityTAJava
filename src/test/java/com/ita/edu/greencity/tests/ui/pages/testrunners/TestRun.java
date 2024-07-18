package com.ita.edu.greencity.tests.ui.pages.testrunners;


import com.ita.edu.greencity.tests.utils.RetryAnalyzer;
import com.ita.edu.greencity.tests.utils.TestNGListener;
import com.ita.edu.greencity.utils.ValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.time.Duration;

@Listeners(TestNGListener.class)
public class TestRun {
    protected static ValueProvider provider;
    protected WebDriver driver;

    @BeforeSuite(description = "Make chromedriver setup")
    public void beforeSuite(ITestContext iTestContext) throws IOException {
//        for (ITestNGMethod method : iTestContext.getAllTestMethods()) {
//            method.setRetryAnalyzerClass(RetryAnalyzer.class);
//        }
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
    }

    @AfterMethod(description = "Quite chromedriver")
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }

    }


}