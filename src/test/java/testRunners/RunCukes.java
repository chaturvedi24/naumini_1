package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "classpath:features",
        glue = {"stepDefs"},
        plugin = {"pretty", "json:target/cucumber.json",
                "rerun:rerun/failed_scenarios.txt"}
)
public class RunCukes extends AbstractTestNGCucumberTests {
}
