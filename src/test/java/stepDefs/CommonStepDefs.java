package stepDefs;

import Utilities.AutomationContext;
import Utilities.WebElementMgr;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonStepDefs {

    AutomationContext context;

    public CommonStepDefs(AutomationContext context) {
        this.context = context;
    }

    @Given("^I click on (\\w+)$")
    public void i_click_on_button(String elementNm) throws Exception {
        WebElement clickElement = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), elementNm);
        try {
            clickElement.click();
            context.getScenarioManager().getScenario().write("Clicked on " + elementNm);
        } catch (Exception e) {
            context.getScenarioManager().getScenario().write("Unable to click on " + elementNm + "; Error encountered:" + e.getMessage());
            throw new Exception("Unable to click on " + elementNm + "; Error encountered:" + e.getMessage());
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
        WebElement selectElement = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), elementNm);
        try {
            Select sel = new Select(selectElement);
            sel.selectByValue(selectionVal);
            context.getScenarioManager().getScenario().write("Selected " + selectionVal + " from " + elementNm + " " + type);
        } catch (Exception e) {
            context.getScenarioManager().getScenario().write("Unable to Select " + selectionVal + " from " + elementNm + " " + type + "; Error encountered:" + e.getMessage());
        }
    }

    /**
     * Step enters given value in the element text input field
     * @param value
     * @param elementNm
     */
    @Then("^I enter \"([^\"]*)\" in (\\w+)$")
    public void i_enter_given_value(String value, String elementNm) {
        WebElement editElement = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), elementNm);
        try {
            editElement.clear();
            editElement.sendKeys(value);
            context.getScenarioManager().getScenario().write("Entered value " + value + " in " + elementNm);
        } catch (Exception e) {
            context.getScenarioManager().getScenario().write("Unable to enter value " + value + " in " + elementNm + "; Error encountered:" + e.getMessage());
        }
    }

    /**
     * Step stores a given element's attribute/text in a cache key
     * Ex: I store courseHeader text in "coursekey"
     * @param elementNm
     * @param attr
     * @param key
     */
    @Then("^I store (\\w+) (\\w+) in \"(.*)\"$")
    public void i_enter_given_value(String elementNm, String attr, String key) {
        WebElement storeElement = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), elementNm);
        if(attr.equalsIgnoreCase("text")) {
            context.setContextCache(key,storeElement.getText());
        } else context.setContextCache(key,storeElement.getAttribute(attr));
        context.getScenarioManager().getScenario().write("Stored "+elementNm+" "+attr+ " in "+key+" :"+context.getContextCache(key));
    }

}
