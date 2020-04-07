package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"stepDefs"},
        plugin = {"pretty","json:target/cucumber.json"}
)
public class RunCukesTest {
}
