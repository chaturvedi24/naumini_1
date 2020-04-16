package stepDefs;

import Utilities.AutomationContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import managers.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    AutomationContext context;

    public Hooks(AutomationContext context) {
        this.context = context;
    }

    @Before
    public void setup(Scenario scenario) {
        Driver.setBrowserNmVersion(context.getConfigFileReader().getPropertyValue("browser"), context.getConfigFileReader().getPropertyValue("browserVersion"));
        Driver.initializeDriver();
        context.getScenarioManager().setScenario(scenario);
        Driver.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        if(scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/jpeg", "failure_image");
        }
        Driver.getDriver().quit();
    }
}
