package com.ita.edu.greencity.tests.api;

import com.ita.edu.greencity.tests.utils.RetryAnalyzer;
import com.ita.edu.greencity.tests.utils.TestNGListener;
import com.ita.edu.greencity.utils.ValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.IOException;

@Listeners(TestNGListener.class)
public class ApiTestRunner {
    protected static ValueProvider provider;

    @BeforeSuite(alwaysRun = true)
    public void setUp(ITestContext iTestContext) throws IOException {
        for (ITestNGMethod method : iTestContext.getAllTestMethods()) {
            method.setRetryAnalyzerClass(RetryAnalyzer.class);
        }
        WebDriverManager.chromedriver().setup();
        if (provider == null) {
            provider = new ValueProvider();
        }
    }

}
