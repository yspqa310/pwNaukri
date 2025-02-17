package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "./src/test/java/features/naukri"
        , glue = {"StepDefinition"},
        tags = "@nau",
        plugin = {"pretty", "html:target/cucumber-reports/index.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
