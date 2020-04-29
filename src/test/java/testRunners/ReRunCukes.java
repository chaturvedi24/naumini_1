package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = "@rerun/failed_scenarios.txt",
        glue = {"stepDefs"},
        plugin = {"pretty", "json:target/cucumber_rerun.json"}
)
public class ReRunCukes extends AbstractTestNGCucumberTests {
}
