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
    String platform;

    public Hooks(AutomationContext context) {
        this.context = context;
        platform = context.getContextCache("platform");
    }

    @Before
    public void setup(Scenario scenario) {
        Driver.setBrowserNmVersion(context.getConfigFileReader().getPropertyValue("browser"), context.getConfigFileReader().getPropertyValue("browserVersion"));
        if (platform.equalsIgnoreCase("web")) {
            Driver.initializeDriver();
            Driver.getDriver().manage().window().maximize();
        }
        context.getScenarioManager().setScenario(scenario);

    }

    @After
    public void tearDown(Scenario scenario) {
        if (platform.equalsIgnoreCase("web")) {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/jpeg", "failure_image");
            }
            Driver.getDriver().quit();
        }

    }
}
