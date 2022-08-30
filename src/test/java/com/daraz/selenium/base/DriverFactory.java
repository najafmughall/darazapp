package com.daraz.selenium.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

import static java.lang.ThreadLocal.withInitial;

public class DriverFactory {

    private static String driverType = "CHROME";

    private DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    private DriverFactory(){
        System.setProperty("org.freemarker.loggerLibrary", "none");
    }

    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance(){
        return instance;
    }

    private ThreadLocal<WebDriver> driver = withInitial(() -> getDriverType().getWebDriverObject(desiredCapabilities));

    public WebDriver getDriver(){
        return driver.get();
    }

    void removeDriver(){
        driver.get().quit();
        driver.remove();
        threadAttrs.remove();
    }

    private DriverType getDriverType(){
        return DriverType.valueOf(driverType);
    }

    private ThreadLocal<Map<String, String>> threadAttrs = ThreadLocal.withInitial(HashMap::new);

    public String getThreadAttrs() {
        return threadAttrs.get().get(driverType);
    }

    public void setThreadAttrs(String value) {
        threadAttrs.get().put(driverType, value);
    }
}
