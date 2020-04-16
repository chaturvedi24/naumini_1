package stepDefs;

import Utilities.AutomationContext;
import Utilities.WebElementMgr;
import io.cucumber.java.en.Then;
import managers.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class ValidationStepDefs {

    AutomationContext context;

    public ValidationStepDefs(AutomationContext context) {
        this.context = context;
    }

    @Then("^I validate (\\w+) is displayed$")
    public void i_validate_displayed(String elementNm) throws Exception {
        WebElement displayElement = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), elementNm);
        if (!displayElement.isDisplayed()) {
            context.getScenarioManager().getScenario().write(elementNm + " not displayed");
        } else {
            context.getScenarioManager().getScenario().write(elementNm + " displayed");
            throw new Exception(elementNm + " displayed");
        }
    }

    /**
     * Step to validate element's attribute value with expected
     *
     * @param elementNm
     * @param attributeNm
     * @param expectedValue
     */
    @Then("^I validate (\\w+) (\\w+) is \"([^\"]*)\"$")
    public void i_validate_attribute(String elementNm, String attributeNm, String expectedValue) throws Exception {
        WebElement attrElement = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), elementNm);
        String actualValue = attrElement.getAttribute(attributeNm);
        if (!actualValue.equals(expectedValue)) {
            context.getScenarioManager().getScenario().write(expectedValue + " not found on page. Found " + actualValue);
            throw new Exception(expectedValue + " not found on page. Found " + actualValue);
        }
    }

    @Then("^I validate (\\w+) is (enabled|disabled)$")
    public void i_validate_enable_disable(String elementNm, String state) throws Exception {
        WebElement stateElement = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), elementNm);
        boolean stateBool = state.equalsIgnoreCase("enabled");
        if (stateElement.isEnabled() == stateBool) {
            context.getScenarioManager().getScenario().write("Pass; " + elementNm + "button is:" + stateBool);
        } else {
            context.getScenarioManager().getScenario().write("Fail; " + elementNm + "button is:" + stateBool);
            throw new Exception("Fail; " + elementNm + "button is:" + stateBool);
        }
    }

    @Then("^I validate (\\w+) has (\\w+) elements and print (\\w+)$")
    public void i_validate_list(String elementNm, int count, String attr) throws Exception {
        List<WebElement> listElement = (List<WebElement>) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), elementNm);
        if (listElement.size() == count) {
            context.getScenarioManager().getScenario().write("Pass; There are " + count + " " + elementNm);
            for (WebElement we : listElement) context.getScenarioManager().getScenario().write(we.getAttribute(attr));
        } else {
            context.getScenarioManager().getScenario().write("Fail; There are " + listElement.size() + " " + elementNm);
            for (WebElement we : listElement) context.getScenarioManager().getScenario().write(we.getAttribute(attr));
            throw new Exception("Fail; There are " + listElement.size() + " " + elementNm);
        }
    }

    /**
     * Step validates a given element's attribute/text with cache key value
     * Ex: I validate courseHeader src with "coursekey"
     *
     * @param elementNm
     * @param attr
     * @param key
     */
    @Then("^I validate (\\w+) (\\w+) with \"(.*)\"$")
    public void i_enter_given_value(String elementNm, String attr, String key) throws Exception {
        String actValue;
        WebElement storeElement = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), elementNm);
        if (attr.equalsIgnoreCase("text")) {
            actValue = storeElement.getText();
        } else {
            actValue = storeElement.getAttribute(attr);
        }
        if (actValue.equals(context.getContextCache(key))) {
            context.getScenarioManager().getScenario().write("Pass; value matching with cache:" + context.getContextCache(key));
        } else {
            context.getScenarioManager().getScenario().write("Fail; value not matching with cache:" + context.getContextCache(key) + ";Instead found:" + actValue);
            throw new Exception("Fail; value not matching with cache:" + context.getContextCache(key) + ";Instead found:" + actValue);
        }
    }

    /**
     * step hovers on element, gets text for the hovered element and match with expected value
     * @param elementNm
     * @param hoverEle
     * @param expText
     */
    @Then("^I hover on (\\w+) and then validate text for (\\w+) to match with \"(.*)\"")
    public void i_hover_get_text(String elementNm, String hoverEle, String expText) throws Exception {
        WebElement mainElement = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), elementNm);
        WebElement hovElement = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), hoverEle);
        Actions actions = new Actions(Driver.getDriver());
        try {
            actions.moveToElement(mainElement).build().perform();
            if(hovElement.getText().equals(expText))
                context.getScenarioManager().getScenario().write("Found hover element "+hoverEle + " text "+expText);
            else throw new Exception("Found hover element "+hoverEle + " text "+hovElement.getText());
        } catch (NoSuchMethodException e) {
            throw new Exception("Unable to hover on "+ elementNm+";Error encountered:"+e.getMessage());
        }
    }

    @Then("^I wait for (\\w+) to be (visible|clickable)$")
    public void i_wait_for_state(String elementNm, String state) {
        WebElement waitElement = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), elementNm);
        FluentWait<WebDriver> wait = new WebDriverWait(Driver.getDriver(),
                Long.parseLong(context.getConfigFileReader().getPropertyValue("fluentwait"))).
                pollingEvery(Duration.ofSeconds(Long.parseLong(context.getConfigFileReader().getPropertyValue("waitPolling"))))
                .ignoring(NoSuchElementException.class);
        if(state.equals("visible")) wait.until(ExpectedConditions.visibilityOf(waitElement));
        else wait.until(ExpectedConditions.elementToBeClickable(waitElement));
    }


}
