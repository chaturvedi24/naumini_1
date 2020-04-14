package stepDefs;

import Utilities.AutomationContext;
import Utilities.WebElementMgr;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import managers.Driver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class NavigationStepDefs {

    AutomationContext context;

    public NavigationStepDefs(AutomationContext context) {
        this.context = context;
    }

    @Given("^I wait for (\\w+) milliseconds$")
    public void i_wait(long waitTime) throws InterruptedException {
        Thread.sleep(waitTime);
        context.getScenarioManager().getScenario().write("Waited for " + waitTime + " milli seconds");
    }

    @Given("^I navigate to \"([^\"]*)\" url$")
    public void i_navigate_to_url(String url) {
        if (url.endsWith(".url")) url = context.getConfigFileReader().getPropertyValue(url);
        Driver.getDriver().get(url);
        context.setContextCache("parenthandle", Driver.getDriver().getWindowHandle());
        context.getScenarioManager().getScenario().write("Window Title:" + Driver.getDriver().getTitle());
    }

    @Then("^I am on the (\\w+) page$")
    public void i_am_on_page(String pageNm) throws Exception {
        context.getPageObjectMgr().setCurrentPage(pageNm, context.getConfigFileReader().getPropertyValue("pageobjpkg"));
        context.getScenarioManager().getScenario().write("User is on " + pageNm + " page");
    }

    @Then("^I switch to (\\w+) iframe$")
    public void i_switch_to_frame(String iframeNm) {
        WebElement iframeElement = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), iframeNm);
        Driver.getDriver().switchTo().frame(iframeElement);
        context.getScenarioManager().getScenario().write("Switched to iframe:" + iframeNm);
    }

    @Then("^I switch out of iframe$")
    public void i_switch_out_frame() {
        Driver.getDriver().switchTo().defaultContent();
    }

    @Then("^I navigate back$")
    public void i_navigate_back() throws InterruptedException {
        Driver.getDriver().navigate().back();
        Thread.sleep(5000);
        context.getScenarioManager().getScenario().write("Window Title:" + Driver.getDriver().getTitle());
    }

    @Then("^I navigate forward$")
    public void i_navigate_forward() throws InterruptedException {
        Driver.getDriver().navigate().forward();
        Thread.sleep(5000);
        context.getScenarioManager().getScenario().write("Window Title:" + Driver.getDriver().getTitle());
    }

    @Then("^I refresh page$")
    public void i_refresh_page() throws InterruptedException {
        Driver.getDriver().navigate().refresh();
        Thread.sleep(5000);
        context.getScenarioManager().getScenario().write("Window Title:" + Driver.getDriver().getTitle());
    }

    @Then("^I switch to child window$")
    public void i_switch_to_child_window() {
        Set<String> wHandles = Driver.getDriver().getWindowHandles();
        for (String wHandle : wHandles) {
            if (!context.getContextCache("parenthandle").equals(wHandle)) {
                Driver.getDriver().switchTo().window(wHandle);
            }
        }
    }

}

