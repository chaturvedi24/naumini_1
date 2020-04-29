package stepDefs;

import Utilities.AutomationContext;
import Utilities.Constant;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
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
        if (Constant.TEST_APP.equalsIgnoreCase("web")) {
            scenario.write("Browser is:" + Constant.BROWSER_NAME);
        }
        context.getScenarioManager().setScenario(scenario);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (Constant.TEST_APP.equalsIgnoreCase("web")) {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/jpeg", "failure_image");
            }
            //Driver.getDriver().quit();
        }
    }
}
