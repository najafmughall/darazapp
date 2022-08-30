package com.daraz.selenium.models;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignUpModel {
@FindBy(how =How.XPATH, using = "//h3[contains(text(),'Create your Daraz Account')]")
public WebElement createAccountHeading;
}
