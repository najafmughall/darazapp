package com.daraz.selenium.models.enums;

public enum TestCaseTypes {
    VALIDATE_HOME_SCREEN("Home Screen", "Home Screen Test", "Home Screen");

    private String type;
    private String reportName;
    private String reportDisplayName;

    TestCaseTypes(String type, String reportName, String reportDisplayName)
    {
        this.type = type;
        this.reportName = reportName;
        this.reportDisplayName = reportDisplayName;
    }

    public String getType()
    {
        return type;
    }

    public String getReportName()
    {
        return reportName;
    }

    public String getReportDisplayName()
    {
        return reportDisplayName;
    }
}
