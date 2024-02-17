package com.rd.drivers;

import com.rd.utils.PropertyManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

class DriverFactory {
    static PropertyManager propertyManager = new PropertyManager();
    static WebDriver getDriver() throws MalformedURLException {
        String browser = propertyManager.getProperty("BROWSER");
        if (browser == null) {
            WebDriverManager.chromedriver().arch64().setup();
            return new ChromeDriver();
        }
        switch (browser) {
            case "IE":
                WebDriverManager.iedriver().arch64().setup();
                return new InternetExplorerDriver();

            case "EDGE":
                WebDriverManager.edgedriver().arch64().setup();
                return new EdgeDriver();

            case "FIREFOX":
                WebDriverManager.firefoxdriver().arch64().setup();
                return new FirefoxDriver();

            case "SAFARI":
                WebDriverManager.safaridriver().arch64().setup();
                return new SafariDriver();

            case "REMOTE":
                WebDriverManager.chromedriver().arch64().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                if ("Y".equalsIgnoreCase(propertyManager.getProperty("HEADLESS"))) {
                    chromeOptions.addArguments("--headless");
                }
                return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);

            default:
                ChromeOptions chromeOption = new ChromeOptions();
                //LoggingPreferences logPrefs = new LoggingPreferences();
                chromeOption.addArguments("--no-sandbox");
                chromeOption.addArguments("--disable-notifications");
                chromeOption.addArguments("start-maximized");
                //logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
                //chromeOption.setCapability("goog:loggingPrefs", logPrefs);
                if ("Y".equalsIgnoreCase(propertyManager.getProperty("HEADLESS"))) {
                    chromeOption.addArguments("--headless");
                }
                WebDriverManager.chromedriver().arch64().setup();
                return new ChromeDriver(chromeOption);

        }
    }
}

