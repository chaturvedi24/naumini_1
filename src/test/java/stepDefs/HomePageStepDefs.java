package stepDefs;

import PageFactory.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.Driver;

public class HomePageStepDefs {

    HomePage homePage;

    @Then("^I am on home page$")
    public void i_am_on_home_page() {
        homePage = new HomePage(Driver.getDriver());
    }

    @When("^I click on Practice$")
    public void i_click_on_Practice() {
        homePage.clickPractice();
    }

    @Then("^I validate enrollNow is displayed$")
    public void i_validate_enroll() {
        homePage.isEnrollNowDisplayed();
    }

    @And("^I validate home image source is \"([^\"]*)\"$")
    public void i_validate_homeimg_src(String expectedSrc) {
        homePage.validateHomeSrc(expectedSrc);
    }

    @Then("^I click on enrollNow$")
    public void i_click_enroll(){
        homePage.clickSignup();
    }

}
