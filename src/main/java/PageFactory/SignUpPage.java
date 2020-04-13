package PageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignUpPage {

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

}
