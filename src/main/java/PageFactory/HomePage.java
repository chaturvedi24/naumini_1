package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver homepageDriver;

    public HomePage (WebDriver driver) {
        homepageDriver = driver;
        PageFactory.initElements(homepageDriver, this);
    }

    @FindBy(how = How.XPATH, using = "//img[@alt=\"Let's Kode It\"]")
    public static WebElement homeImg;

    @FindBy(how = How.XPATH, using = "//a[@href='/pages/practice']")
    public static WebElement practiceBtn;

    @FindBy(how = How.XPATH, using = "//a[@href='/sign_up']")
    public static WebElement enrollNow;

    public void clickPractice() {
        practiceBtn.click();
    }

    public void validateHomeSrc (String expectedSrc) {
        String actualSrc = homeImg.getAttribute("src");
        assert (actualSrc.equals(expectedSrc));
    }

    public void isEnrollNowDisplayed() {
        enrollNow.isDisplayed();
    }

}
