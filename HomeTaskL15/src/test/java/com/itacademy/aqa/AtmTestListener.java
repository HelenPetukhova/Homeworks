package com.itacademy.aqa;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AtmTestListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println((result.isSuccess() ? "PASS" : "FAILED") + "| " + result.getName() + "| "+ result.getMethod().getDescription());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println((result.isSuccess() ? "PASS" : "FAILED") + "| " + result.getName() + "| "+ result.getMethod().getDescription());

    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("The test suit running is complete");
    }
}
