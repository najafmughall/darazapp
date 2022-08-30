package com.daraz.selenium.models;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomeModel {

@FindBy(how = How.XPATH, using = "//section[@data-mode-name='banner-slider']")
public WebElement slider;

@FindBy(how = How.XPATH, using ="//a[contains(text(),'Signup')]")
public WebElement signUpButton;
}
