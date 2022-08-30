package com.daraz.selenium.models;

import com.aventstack.extentreports.ExtentTest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class ReportModel {
    private ExtentTest extentTest;
    private String reportName;
    private Long startTime;
    private Long endTime;
    private Long executionTime;

    public ReportModel(ExtentTest extentTest, String reportName) {
        this.extentTest = extentTest;
        this.reportName = reportName;
        this.startTime = System.currentTimeMillis();
    }
}
