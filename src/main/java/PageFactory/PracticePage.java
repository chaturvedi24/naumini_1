package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PracticePage {

    @FindBy(how = How.ID, using = "bmwradio")
    public static WebElement bmwRadio;

    @FindBy(how = How.ID, using = "carselect")
    public static WebElement dropDn;

    @FindBy(how = How.ID, using = "multiple-select-example")
    public static WebElement multiSel;

    @FindBy(how = How.ID, using = "benzcheck")
    public static WebElement checkBox;

    @FindBy(how = How.NAME, using = "enter-name")
    public static WebElement enterNm;

    @FindBy(how = How.ID, using = "alertbtn")
    public static WebElement alertBtn;

    @FindBy(how = How.XPATH, using = "//legend[text()='Radio Button Example']")
    public static WebElement radioExample;

    @FindBy(how = How.XPATH, using = "//iframe[@src='https://learn.letskodeit.com/courses']")
    public static WebElement iframeExample;

    @FindBy(how = How.XPATH, using = "//button[text()='Open Window']")
    public static WebElement openWindow;

    @FindBy(how = How.XPATH, using = "//button[@id='mousehover']")
    public static WebElement mouseHover;

    @FindBy(how = How.XPATH, using = "//div[@class='mouse-hover-content']/a[1]")
    public static WebElement hover1;

    @FindBy(how = How.ID, using = "user_name")
    public static WebElement userName;

}
