package com.daraz.selenium.managers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.daraz.selenium.models.enums.TestCaseTypes;
import org.openqa.selenium.WebDriver;

import java.io.File;


public class ReportManager {
    private ExtentReports report;
    private ExtentSparkReporter htmlReporter;
    private ExtentTestManager extentTestManager;

    String path = "D:\\OneDrive - Constellation HomeBuilder Systems\\Test Reports\\";

    public ExtentReports getReporter(TestCaseTypes type) {
        if (report == null) {
            File directory = new File(path);
            boolean isDirectoryExists = directory.isDirectory();

            if (!isDirectoryExists)
                isDirectoryExists = directory.mkdirs();

            if (isDirectoryExists) {
                String dest = path + "report.html";
                htmlReporter = new ExtentSparkReporter(dest);
                report = new ExtentReports();
                report.attachReporter(htmlReporter);
            }
        }
        return report;
    }

    public ExtentTest getExtendReporter(TestCaseTypes type, WebDriver driver) {
        if (extentTestManager == null)
            extentTestManager = new ExtentTestManager(getReporter(type));
        return extentTestManager.startTest(type.getType(), type.getReportName(), type.getReportDisplayName(), driver);
    }

    public void endTest() throws Exception {
        extentTestManager.endTest();
        report.flush();
    }

    public ExtentTestManager getExtentTestManager() {
        return extentTestManager;
    }

    public ExtentReports getReport() {
        return report;
    }
}
