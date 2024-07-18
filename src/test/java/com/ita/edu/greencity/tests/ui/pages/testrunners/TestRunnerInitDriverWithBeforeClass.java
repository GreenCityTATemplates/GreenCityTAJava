package com.ita.edu.greencity.tests.ui.pages.testrunners;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class TestRunnerInitDriverWithBeforeClass extends TestRun {

    @BeforeClass
    public void beforeClass(ITestContext iTestContext) {
        super.beforeMethod(iTestContext);
    }

    @BeforeMethod
    public void beforeMethod() {

    }

    @AfterMethod
    public void afterMethod() {

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
