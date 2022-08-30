package com.daraz.selenium.pages;

import com.daraz.selenium.models.SignUpModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
    public boolean isSignUpPageDisplayed(WebDriver webDriver, WebDriverWait webDriverWait, SignUpModel signUpModel){
        webDriverWait.until(ExpectedConditions.visibilityOf(signUpModel.createAccountHeading));
        return signUpModel.createAccountHeading.isDisplayed();
    }
}
