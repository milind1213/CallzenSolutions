package com.callzen.utility;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeners implements ITestListener, ISuiteListener {
    ExtentTest test;
    static String messageBody;
    static ExtentReports extent = ExtentReportNG.getReportObject();
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Thread

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);
        //System.out.println(methodName.toUpperCase() + " STARTED!");
        ExtentTest test = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        test.assignCategory(result.getTestContext().getSuite().getName());

        test.assignCategory(className);
        extentTest.set(test);
        extentTest.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "TEST CASE '" + methodName.toUpperCase() + "' PASSED" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().pass(m); // extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        try {
            TestUtil.captureScreenshot(methodName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get()
                .fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured: Click to View"
                        + "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>"
                        + " \n");

        extentTest.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of Failure" + "</font>" + "</b>",
                MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.screenshotName).build());

        String failureLogg = "TEST CASE FAILED";
        Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, m);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.AMBER);
        extentTest.get().skip(m);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}