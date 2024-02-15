package com.rd.drivers;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

class DriverFactory {
    private static final String FIREFOX = "firefox";
    private static final String IE = "ie";
    private static final String DEFAULT = "chrome";
    private static final String REMOTE = "remote";
    private static WebDriver driver;

    static WebDriver getDriver() throws MalformedURLException {
        String browser = System.getenv("BROWSER");
        if (browser == null) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        switch (browser) {
            case "IE":
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();

            case "EDGE":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case "REMOTE":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
                    chromeOptions.addArguments("--headless");
                }
                return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);

            default:
                ChromeOptions chromeOption = new ChromeOptions();
                LoggingPreferences logPrefs = new LoggingPreferences();
                chromeOption.addArguments("--no-sandbox");
                chromeOption.addArguments("--disable-notifications");
                chromeOption.addArguments("start-maximized");
                logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
                chromeOption.setCapability("goog:loggingPrefs", logPrefs);
                if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
                    chromeOption.addArguments("--headless");
                }
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(chromeOption);

        }
    }
}

