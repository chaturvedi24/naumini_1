package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import managers.Driver;

public class Hooks {

    @Before
    public void setup () {
        Driver.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown () {
        Driver.getDriver().quit();
    }
}
