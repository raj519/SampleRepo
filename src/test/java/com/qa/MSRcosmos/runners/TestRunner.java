package com.qa.MSRcosmos.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/main/java/com/qa/features",
    glue = "com.qa.steps",
    plugin = {"pretty", "html:target/cucumber-report.html"},
    monochrome = true,
    tags = "@HTTPPostAPI or @HTTPGetAPI or @jobSearchUI"
)

public class TestRunner {

}
