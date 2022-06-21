package com.HMRC.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
    static ExtentReports extentReports;
    public static ExtentReports getReportObject(){
        String reportPath = System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Web Automation Test Report");
        reporter.config().setDocumentTitle("Automation Test Results");
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester","Tamil");
        extentReports.setSystemInfo("Env", "SIT");
        extentReports.setSystemInfo("Build", "Integration");
        extentReports.setSystemInfo("Browser", "Chrome");
        return extentReports;

    }
}
