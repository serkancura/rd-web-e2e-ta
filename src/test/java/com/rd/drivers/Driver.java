package com.rd.drivers;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Driver {
    public static WebDriver webDriver;

    public WebDriver initializeDriver() throws MalformedURLException {
        webDriver = DriverFactory.getDriver();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        webDriver.manage().window().maximize();

        return webDriver;
    }


    public void quitDriver() {
        webDriver.quit();
    }
}
