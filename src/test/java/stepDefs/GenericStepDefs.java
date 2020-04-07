package stepDefs;

import PageFactory.HomePage;
import PageFactory.PracticePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GenericStepDefs {
    WebDriver driver;
    PracticePage practicePage;
    HomePage homePage;

    @Before
    public void setup () {
        System.setProperty("webdriver.gecko.driver","C:\\IJProjs\\geckodriver.exe");
        driver = new FirefoxDriver();
        practicePage = new PracticePage(driver);
        homePage = new HomePage(driver);
    }

    @Given("^I wait for (\\w+) milliseconds$")
    public void i_wait(long waitTime) throws InterruptedException {
        Thread.sleep(waitTime);
    }


    @Given("^I navigate to \"([^\"]*)\" url$")
    public void i_navigate_to_url(String string) {
        driver.get(string);
        System.out.println("Window Title:"+driver.getTitle());
    }

    @When("^I click on Practice$")
    public void i_click_on_Practice() {
        homePage.clickPractice();
    }

    @Then("^I select BMW radio button$")
    public void i_select_BMW_radio_button() {
        practicePage.selectRadio();
    }

    @Then("^I select (\\w+) from cars dropdown$")
    public void i_select_Honda_from_cars_dropdown(String carType) {
        practicePage.selectDropDn(carType);
    }

    @Then("^I validate enrollNow is displayed$")
    public void i_validate_enroll() {
        homePage.isEnrollNowDisplayed();
    }

    @And("^I validate home image source is \"([^\"]*)\"$")
    public void i_validate_homeimg_src(String expectedSrc) {
        homePage.validateHomeSrc(expectedSrc);
    }

    @After
    public void tearDown () {
        driver.quit();
    }

}
