package com.listener;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.base.BaseClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ITestListenerClass extends BaseClass implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            ExtentReport_Test.extenttest.pass(result.getMethod().getMethodName() + " : " + "TEST PASS",
                    MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            ExtentReport_Test.extenttest.fail(result.getMethod().getMethodName() + " : " + "TEST FAILED",
                    MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

