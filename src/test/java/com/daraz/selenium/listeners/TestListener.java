package com.daraz.selenium.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.daraz.selenium.base.DriverFactory;
import com.daraz.selenium.managers.ExtentTestManager;
import com.daraz.selenium.models.ReportModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.util.Calendar;

public class TestListener implements ITestListener {

    private static final Log logger = LogFactory.getLog(TestListener.class);

    private String resourcePath = "application-test.properties";

    private String reportPath;

    @Override
    public void onStart(ITestContext context) {
        logger.info("*** Test Suite " + context.getName() + " started ***");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("*** Test Suite " + context.getName() + " ending ***");
        logger.debug("onFinish" + Thread.currentThread().getId());
    }

    public void onTestStart(ITestResult result) {
        logger.info("*** Running test method " + result.getMethod().getMethodName() + "...");
    }

    @Override
    public void onTestSuccess(@NotNull ITestResult result) {
        logger.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ReportModel reportModel = getReportModelWithExecutionTime(result.getMethod().getMethodName());
        reportModel.getExtentTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            logger.debug("onTestFailure" + Thread.currentThread().getId());
            logger.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
            String fileName = String.format("Screenshot-%s.png", Calendar.getInstance().getTimeInMillis());

            String projectPath = System.getProperty( "user.dir" );
            String dest = projectPath + "/reports/screenshots/" + fileName;
            TakesScreenshot ts = (TakesScreenshot) DriverFactory.getInstance().getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);

            File snapshotDest = new File(dest);
            FileUtils.copyFile(source, snapshotDest);
            ReportModel reportModel = getReportModelWithExecutionTime(result.getMethod().getMethodName());
            reportModel.getExtentTest().log(Status.FAIL, "Test Failed",
                    MediaEntityBuilder.createScreenCaptureFromPath(dest).build());


        } catch (Exception e) {
            throw new RuntimeException("Failed to take screenshot !", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("*** Test " + result.getMethod().getMethodName() + " skipped...");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }

    private ReportModel getReportModelWithExecutionTime(String testCase) throws NullPointerException {
        ReportModel reportModel = ExtentTestManager.reportsMap.get(testCase);
        reportModel.setExecutionTime(System.currentTimeMillis() - reportModel.getStartTime());
        reportModel.getExtentTest().log(Status.INFO, reportModel.getReportName() + " is completed in " + reportModel.getExecutionTime());
        return reportModel;
    }
}
