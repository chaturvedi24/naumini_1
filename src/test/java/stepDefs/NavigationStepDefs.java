package stepDefs;

import Utilities.AutomationContext;
import Utilities.WebElementMgr;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import managers.Driver;
import org.openqa.selenium.WebElement;

public class NavigationStepDefs {

    AutomationContext context;

    public NavigationStepDefs(AutomationContext context) {
        this.context = context;
    }

    @Given("^I wait for (\\w+) milliseconds$")
    public void i_wait(long waitTime) throws InterruptedException {
        Thread.sleep(waitTime);
    }

    @Given("^I navigate to \"([^\"]*)\" url$")
    public void i_navigate_to_url(String string) {
        Driver.getDriver().get(string);
        System.out.println("Window Title:" + Driver.getDriver().getTitle());
    }

    @Then("^I am on the (\\w+) page$")
    public void i_am_on_page(String pageNm) throws Exception {
        context.getPageObjectMgr().setCurrentPage(pageNm);
    }

    @Then("^I switch to (\\w+) iframe$")
    public void i_switch_to_frame(String iframeNm) {
        WebElement iframeElement = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), iframeNm);
        Driver.getDriver().switchTo().frame(iframeElement);
    }


}
