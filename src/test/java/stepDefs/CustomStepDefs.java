package stepDefs;

import PageFactory.HomePage;
import PageFactory.PracticePage;
import Utilities.AutomationContext;
import Utilities.WebElementMgr;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomStepDefs {
    AutomationContext context;

    public CustomStepDefs(AutomationContext context) {
        this.context = context;
    }

    @Then("^I validate signup page fields using \"(.*)\"")
    public void i_validate_signup(String fileNm) throws Exception {
        boolean pass = true;
        ArrayList<HashMap<String, String>> validationData = context.getHashMapDataCache(fileNm);
        WebElement nameEle = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), "userName");
        WebElement emailEle = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), "userEmail");
        WebElement pwdEle = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), "userPwd");
        WebElement confirmEle = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), "confirmPwd");
        WebElement signUpEle = (WebElement) WebElementMgr.getWebElement(context.getPageObjectMgr().getCurrentPage(), "signUp");

        for(HashMap<String, String> eachMap: validationData) {
            boolean expectedState = eachMap.get("SignUpEnabled").equalsIgnoreCase("enabled");
            nameEle.clear();
            nameEle.sendKeys(eachMap.get("Name"));
            emailEle.clear();
            emailEle.sendKeys(eachMap.get("Email"));
            pwdEle.clear();
            pwdEle.sendKeys(eachMap.get("Password"));
            confirmEle.clear();
            confirmEle.sendKeys(eachMap.get("Confirm"));
            if(signUpEle.isEnabled() == expectedState) {
                context.getScenarioManager().getScenario().write("Pass; "+eachMap.get("TC")+": signup button is:"+eachMap.get("SignUpEnabled"));
            } else {
                pass = false;
                context.getScenarioManager().getScenario().write("Fail; "+eachMap.get("TC")+": signup button is not:"+eachMap.get("SignUpEnabled"));
            }
        }
        if(!pass) throw new Exception ("Validation failed. Check outputs!");

    }

    @Then("^I click practice btn from \"(.*)\"$")
    public void clickpractice(String page) {
        if (page.equals("Home Page"))
            HomePage.clickPractice();
    }
}
