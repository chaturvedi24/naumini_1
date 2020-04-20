package PageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {

    @FindBy(how = How.XPATH, using = "//img[@alt=\"Let's Kode It\"]")
    public static WebElement homeImg;

    @FindBy(how = How.XPATH, using = "//a[@href='/pages/practice']")
    public static WebElement practiceBtn;

    @FindBy(how = How.XPATH, using = "//a[@href='/sign_up']")
    public static WebElement enrollNow;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'course-block')]/div[@class='container']/h2")
    public static WebElement courseHeader;

    public static void clickPractice() {
        practiceBtn.click();
    }

}
