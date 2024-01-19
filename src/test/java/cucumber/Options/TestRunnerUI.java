package cucumber.Options;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import testBase.BaseTest;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/feature/"},
        glue = {"step.definitions"},
        monochrome = true,
        tags = "@trelloLogin",
        plugin = {
                "json:target/jsonReports/cucumber-report.json",
                "html:target/cucumber-reports.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "pretty",
        })

        public class TestRunnerUI extends BaseTest {

                @BeforeClass
                public static void invokeDriver() throws IOException {
                        if (driver == null) {
                            initialization();
                        }
                }
                @AfterClass
                public static void tearDown() {
                        if (driver != null) {
                                driver.quit();
                        }
                }
        }
