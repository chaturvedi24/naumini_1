package stepDefs;

import PageFactory.SignUpPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import managers.Driver;

public class SignUpPageStepDefs {

    SignUpPage signUpPage;

    @Then("^I am on signup page$")
    public void i_am_on_signup_page() {
        signUpPage = new SignUpPage(Driver.getDriver());
    }

    @Then("^I enter user name as \"([^\"]*)\"$")
    public void i_enter_user_name(String userNm) {
        signUpPage.enterUserNm(userNm);
    }

    @Then("^I enter user email as \"([^\"]*)\"$")
    public void i_enter_user_email(String userEml) {
        signUpPage.enterUserEmail(userEml);
    }

    @Then("^I enter user password as \"([^\"]*)\"$")
    public void i_enter_user_password(String userPass) {
        signUpPage.enterUserPass(userPass);
        signUpPage.enterConfirmPass(userPass);
    }

    @Then("^I agree terms and marketing$")
    public void i_agree_terms_market() {
        signUpPage.agreeAll();
    }

    @Then("^I verify sign up is \"([^\"]*)\"$")
    public void i_agree_terms_market(String enabled) throws Exception {
        if(enabled.equals("enabled")) signUpPage.VerifySignup(true);
        else signUpPage.VerifySignup(false);
    }

}
