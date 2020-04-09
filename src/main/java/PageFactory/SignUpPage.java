package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//h1[contains(text(),\"Sign Up to Let's Kode It\")]")
    public static WebElement signUpHeader;

    @FindBy(how = How.ID, using = "user_name")
    public static WebElement userName;

    @FindBy(how = How.ID, using = "user_email")
    public static WebElement userEmail;

    @FindBy(how = How.ID, using = "user_password")
    public static WebElement userPwd;

   @FindBy(how = How.ID, using = "user_password_confirmation")
    public static WebElement confirmPwd;

   @FindBy(how = How.ID, using = "user_unsubscribe_from_marketing_emails")
    public static WebElement agreeMarketing;

   @FindBy(how = How.ID, using = "user_agreed_to_terms")
    public static WebElement agreeTerms;

   @FindBy(how = How.NAME, using = "commit")
    public static WebElement signUp;

   public void validateHeader() throws Exception {
       if(signUpHeader.isDisplayed()) {
           System.out.println("Sign up header is displayed");
       } else {
           System.out.println("Sign up header is not displayed");
           throw new Exception("Sign up header is not displayed");
       }
   }

   public void enterUserNm (String userNm) {
       userName.sendKeys(userNm);
   }

   public void enterUserEmail (String userEml) {
       userEmail.sendKeys(userEml);
   }

   public void enterUserPass (String userPass) {
       userPwd.sendKeys(userPass);
   }

   public void enterConfirmPass (String confirmPass) {
       confirmPwd.sendKeys(confirmPass);
   }

   public void agreeAll () {
       agreeMarketing.click();
       agreeTerms.click();
   }

   public void VerifySignup(boolean enabled) throws Exception {
       if(signUp.isEnabled() == enabled) {
           System.out.println("pass: signup button is "+signUp.isEnabled());
       } else {
           System.out.println("fail:signup button is "+signUp.isEnabled());
           throw new Exception("fail:signup button is "+signUp.isEnabled());
       }

   }



}
