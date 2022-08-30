package com.daraz.selenium.base;

import org.openqa.selenium.WebDriver;

public class AbstractPage {

    public void navigate(final String url) {
        DriverFactory.getInstance().getDriver().navigate().to(url);
    }
}
