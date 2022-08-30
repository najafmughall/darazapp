package com.daraz.selenium.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum DriverType implements DriverSetup
{

    CHROME
            {
                @Override
                public RemoteWebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities)
                {
                    String downloadPath = System.getProperty("user.dir");

                    HashMap<String, Object> chromePreferences = new HashMap<>();
                    chromePreferences.put("profile.password_manager_enabled", false);
                    chromePreferences.put("profile.default_content_settings.popups", 0);
                    chromePreferences.put("credentials_enable_service", false);
                    chromePreferences.put("download.default_directory", downloadPath);
                    ChromeOptions options = new ChromeOptions();
                    chromePreferences.put("profile.default_content_setting_values.notifications", 2);
                    options.setExperimentalOption("prefs", chromePreferences);
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--no-proxy-server");
                    options.addArguments("--no-sandbox");
                    //options.addArguments("--incognito");
                    options.addArguments("--no-default-browser-check");
                    options.addArguments("--start-maximized");
                    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                    options.setExperimentalOption("prefs", chromePreferences);
                    WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                    return new ChromeDriver(options);

                }
   /*
        public RemoteWebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            HashMap<String, Object> chromePreferences = new HashMap<>();
            chromePreferences.put("profile.password_manager_enabled", false);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--no-default-browser-check");
            options.addArguments("--start-maximized");
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.setExperimentalOption("prefs", chromePreferences);
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
            return new ChromeDriver(options);
        }*/

            },


    CHROME_HEADLESS
            {
                @Override
                public RemoteWebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities)
                {
                    HashMap<String, Object> chromePreferences = new HashMap<>();
                    chromePreferences.put("profile.password_manager_enabled", false);
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--no-default-browser-check");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--start-maximized");
                    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                    options.addArguments("--headless");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.setExperimentalOption("prefs", chromePreferences);
                    WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                    return new ChromeDriver(options);
                }
            },
    FIREFOX
            {
                @Override
                public RemoteWebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities)
                {
                    FirefoxOptions options = new FirefoxOptions();
                    WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                    return new FirefoxDriver(options);
                }
            },
    FIREFOX_HEADLESS
            {
                @Override
                public RemoteWebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities)
                {
                    FirefoxOptions options = new FirefoxOptions();
                    options.setHeadless(true);
                    WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                    FirefoxDriver driver = new FirefoxDriver(options);
                    driver.manage().window().maximize();
                    return driver;
                }
            },
    SAFARI
            {
                @Override
                public RemoteWebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities)
                {
                    System.setProperty("webdriver.safari.noinstall", "true");
                    SafariOptions options = new SafariOptions();
                    WebDriverManager.getInstance(DriverManagerType.SAFARI).setup();
                    SafariDriver driver = new SafariDriver(options);
                    driver.manage().window().maximize();
                    return driver;
                }
            },
    EDGE
            {
                @Override
                public RemoteWebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities)
                {
                    String downloadPath = System.getProperty("user.dir");
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("download.default_directory", downloadPath);
                    EdgeOptions edgeOptions = new EdgeOptions();
                    WebDriverManager.getInstance(DriverManagerType.EDGE).setup();
                    edgeOptions.setExperimentalOption("prefs", prefs);

                    EdgeDriver driver = new EdgeDriver(edgeOptions);
                    driver.manage().window().maximize();
                    return driver;
                }
            },

}
