package com.daraz.selenium.base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.daraz.selenium.managers.ReportManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.logging.Level;

public class AbstractTest {
    protected WebDriverWait wait;
    protected WebDriver driver;
    protected ReportManager manager = new ReportManager();
    protected LoadUrl loadURL = new LoadUrl();


    @BeforeMethod
    public void initObjects()
    {
        driver = DriverFactory.getInstance().getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @AfterMethod
    public void close()
    {
        DriverFactory.getInstance().removeDriver();
    }

    @AfterSuite
    public void tearDown() throws Exception
    {
        if (manager != null)
        {
            manager.endTest();
        }
    }

    public void implicitlyWait()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }

    public void addBrowserLogs(ExtentTest logger)
    {
        driver.manage().logs().get(LogType.BROWSER).forEach(logEntry -> {
            Status status = logEntry.getLevel() == Level.INFO ? Status.INFO : Status.WARNING;
            logger.log(status, "[Browser] " + logEntry.getMessage());
        });
    }
}
