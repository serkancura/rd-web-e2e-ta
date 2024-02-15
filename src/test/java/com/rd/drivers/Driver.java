package com.rd.drivers;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Driver {
    public static WebDriver webDriver;

    @BeforeSuite
    public void initializeDriver() throws MalformedURLException {
        webDriver = DriverFactory.getDriver();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        webDriver.manage().window().maximize();
    }

    @AfterTest
    public void closeDriver() {
        webDriver.close();
    }

    @AfterSuite
    public void quitDriver() {
        webDriver.quit();
    }
}
