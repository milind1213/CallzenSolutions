
package com.callzen.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "//reports//ExtentTestReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Automation Report");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("UserName", "Milind");
        extent.setSystemInfo("OS", "Ubuntu/Linux");
        extent.setSystemInfo("Os Vesrion", "22.04");
        extent.setSystemInfo("Java Version", "11.0.20");
        extent.setSystemInfo("Time", "Asia/Calcuta");
        return extent;
    }
}
