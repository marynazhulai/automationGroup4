package com.customertimes.cucumber.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = "pretty",
        monochrome = true,
        tags = "smoke",
        glue = "com.customertimes.cucumber",
        features = "classpath:io.customertimes/features/"

)


public class CucumberBaseTest extends AbstractTestNGCucumberTests {
}
