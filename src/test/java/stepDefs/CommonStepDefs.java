package stepDefs;

import Utilities.AutomationContext;
import Utilities.WebElementMgr;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import managers.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class CommonStepDefs {

    AutomationContext context;

    public CommonStepDefs(AutomationContext context) {
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

    @Given("^I click on (\\w+)$")
    public void i_click_on_button(String elementNm) {
        Object currentPage = context.getPageObjectMgr().getCurrentPage();
        WebElement clickElement = WebElementMgr.getWebElement(currentPage, elementNm);
        clickElement.click();
    }

    @Then("^I am on the (\\w+) page$")
    public void i_am_on_page(String pageNm) throws Exception {
        context.getPageObjectMgr().setCurrentPage(pageNm);
    }

    @Then("^I validate (\\w+) is displayed$")
    public void i_validate_displayed(String elementNm) {
        Object currentPage = context.getPageObjectMgr().getCurrentPage();
        WebElement displayElement = WebElementMgr.getWebElement(currentPage, elementNm);
        if (!displayElement.isDisplayed()) {
            System.out.println(elementNm + " not displayed");
        } else System.out.println(elementNm + " displayed");
    }

    /**
     * Step to validate element's attribute value with expected
     * @param elementNm
     * @param attributeNm
     * @param expectedValue
     */
    @Then("^I validate (\\w+) (\\w+) is \"([^\"]*)\"$")
    public void i_validate_attribute(String elementNm, String attributeNm, String expectedValue) {
        Object currentPage = context.getPageObjectMgr().getCurrentPage();
        WebElement attrElement = WebElementMgr.getWebElement(currentPage, elementNm);
        String actualValue = attrElement.getAttribute(attributeNm);
        if (!actualValue.equals(expectedValue)) {
            System.out.println(expectedValue + " not found on page. Found " + actualValue);
        }
    }

    /**
     * Step to select given value from a dropdown or multiselect element
     * @param selectionVal
     * @param elementNm
     * @param type
     */
    @Then("^I select (\\w+) from (\\w+) (dropdown|multiselect)$")
    public void i_select(String selectionVal, String elementNm, String type) {
        Object currentPage = context.getPageObjectMgr().getCurrentPage();
        WebElement selectElement = WebElementMgr.getWebElement(currentPage, elementNm);
        Select sel = new Select(selectElement);
        sel.selectByValue(selectionVal);
    }

    /**
     * Step enters given value in the element text input field
     * @param value
     * @param elementNm
     */
    @Then("^I enter \"([^\"]*)\" in (\\w+)")
    public void i_enter_given_value(String value, String elementNm) {
        Object currentPage = context.getPageObjectMgr().getCurrentPage();
        WebElement editElement = WebElementMgr.getWebElement(currentPage, elementNm);
        editElement.clear();
        editElement.sendKeys(value);
    }




}
