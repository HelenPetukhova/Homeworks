package com.itacademy.aqa.utils;

import com.itacademy.aqa.config.Browser;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class WPListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println((result.isSuccess() ? "PASS" : "FAILED") + "| " + result.getName() + "| "+ result.getMethod().getDescription());
        try{
            Browser.takeScreenShot();
        } catch (Exception e) {
            System.out.println("Failed to take a screenshot");
        }

    }
}
