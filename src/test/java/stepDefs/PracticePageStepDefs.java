package stepDefs;

import PageFactory.HomePage;
import PageFactory.PracticePage;
import io.cucumber.java.en.Then;
import managers.Driver;
import org.openqa.selenium.WebDriver;

public class PracticePageStepDefs {

    PracticePage practicePage;

    @Then("^I am on practice page$")
    public void i_am_on_home_page() {
        practicePage = new PracticePage(Driver.getDriver());
    }

    @Then("^I select BMW radio button$")
    public void i_select_BMW_radio_button() {
        practicePage.selectRadio();
    }

    @Then("^I select (\\w+) from cars dropdown$")
    public void i_select_Honda_from_cars_dropdown(String carType) {
        practicePage.selectDropDn(carType);
    }
}
