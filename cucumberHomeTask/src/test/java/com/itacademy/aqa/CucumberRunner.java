package com.itacademy.aqa;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features",
        stepNotifications = true,
        glue = "com/itacademy/aqa",
        tags = "@All"
)
public class CucumberRunner {
}
