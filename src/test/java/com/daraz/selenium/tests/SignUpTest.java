package com.daraz.selenium.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.daraz.selenium.base.AbstractTest;
import com.daraz.selenium.models.HomeModel;
import com.daraz.selenium.models.SignUpModel;
import com.daraz.selenium.models.enums.TestCaseTypes;
import com.daraz.selenium.pages.HomePage;
import com.daraz.selenium.pages.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpTest extends AbstractTest {

    private HomeModel homeModel;
    private SignUpModel signUpModel;
    private HomePage homePage = new HomePage();
    private SignUpPage signUpPage = new SignUpPage();

    @BeforeMethod
    public void setUp() {
        homeModel = PageFactory.initElements(driver, HomeModel.class);
        signUpModel = PageFactory.initElements(driver, SignUpModel.class);
    }

    @Test
    public void verifySignUpPage(){
        ExtentTest logger = manager.getExtendReporter(TestCaseTypes.VALIDATE_HOME_SCREEN, driver);
        loadURL.openApplication(driver);
        logger.log(Status.PASS, "Application loaded");
        homePage.clickSignUpButton(driver, wait, homeModel);
        Assert.assertTrue(signUpPage.isSignUpPageDisplayed(driver, wait, signUpModel));
        logger.log(Status.PASS, "Test Passed.");
    }

}
