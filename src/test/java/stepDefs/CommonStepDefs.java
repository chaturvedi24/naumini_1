package stepDefs;

import io.cucumber.java.en.Given;
import managers.Driver;
import org.openqa.selenium.WebDriver;

public class CommonStepDefs {

    @Given("^I wait for (\\w+) milliseconds$")
    public void i_wait(long waitTime) throws InterruptedException {
        Thread.sleep(waitTime);
    }

    @Given("^I navigate to \"([^\"]*)\" url$")
    public void i_navigate_to_url(String string) {
        Driver.getDriver().get(string);
        System.out.println("Window Title:"+Driver.getDriver().getTitle());
    }
}
