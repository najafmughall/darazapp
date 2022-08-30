package com.daraz.selenium.base;

import lombok.Value;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class LoadUrl extends AbstractPage{
    private String url = "https://www.daraz.pk/";

    public void openApplication(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        super.navigate(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }




}
