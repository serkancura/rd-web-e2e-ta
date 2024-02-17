package com.rd.tests;

import com.rd.drivers.Driver;
import com.rd.utils.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;

public class GoogleTests {

    public static WebDriver driver;
    Driver webDriver = new Driver();
    PropertyManager propertyManager= new PropertyManager();
    String url =propertyManager.getProperty("APP_URL");



    @BeforeMethod(alwaysRun = true)
    public void before() throws MalformedURLException {
        driver = webDriver.initializeDriver();
        driver.get(url);
    }

    @Test
    public void openGooglePageTest() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle,"Google");
    }

    @Test
    public void searchGoogleOnGoogle(){
        WebElement txtSearch = driver.findElement(new By.ByName("q"));
        WebElement txtSearch2 = driver.findElement(new By.ByCssSelector("textarea[name='q']"));

        txtSearch.sendKeys("Google");
        txtSearch.clear();
        txtSearch2.sendKeys("Google");
        txtSearch2.sendKeys(Keys.ENTER);

        WebElement divSearch = driver.findElement(new By.ById("search"));
        Assert.assertTrue(divSearch.isDisplayed());
    }

    @AfterMethod(alwaysRun = true)
    public void  after(){
        webDriver.quitDriver();
    }
}
