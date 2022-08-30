package com.daraz.selenium.pages;

import com.daraz.selenium.models.HomeModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    public void clickSignUpButton(WebDriver webDriver, WebDriverWait webDriverWait, HomeModel homeModel){
        webDriverWait.until(ExpectedConditions.visibilityOf(homeModel.signUpButton));
        homeModel.signUpButton.click();
    }

}
