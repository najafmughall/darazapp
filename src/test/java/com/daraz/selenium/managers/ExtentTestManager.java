package com.daraz.selenium.managers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.daraz.selenium.base.DriverFactory;
import com.daraz.selenium.models.ReportModel;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExtentTestManager {
    private ExtentReports extent;
    public static Map<String, ReportModel> reportsMap = new ConcurrentHashMap<>();

    ExtentTestManager(ExtentReports extend) {
        this.extent = extend;
    }

    ExtentTest startTest(String testName, String methodName, String reportDisplayName, WebDriver driver) {
        ReportModel reportModel = new ReportModel(extent.createTest(reportDisplayName), reportDisplayName == null
                ? testName : reportDisplayName);
        reportsMap.put(methodName, reportModel);
        DriverFactory.getInstance().setThreadAttrs(methodName);
        return reportModel.getExtentTest();
    }

    void endTest() {
        reportsMap = new HashMap<>();
    }
}
