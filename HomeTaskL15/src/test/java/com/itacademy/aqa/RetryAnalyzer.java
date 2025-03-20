package com.itacademy.aqa;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int counter = 0;
    private static final int retryLimit = 3;

    @Override
    public boolean retry(ITestResult result) {
        counter ++;
        if(counter<= retryLimit){
            return true;
        } else {
            return false;
        }
    }
}
