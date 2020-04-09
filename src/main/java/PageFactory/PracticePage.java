package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PracticePage {

    public PracticePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

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

    public void selectRadio() {
        bmwRadio.click();
    }

    public void selectDropDn(String value) {

        Select sel = new Select(dropDn);
        sel.selectByValue(value);
    }

    public void multiSelect(String value) {
        Select sel = new Select(multiSel);
        sel.selectByValue(value);
    }

    public void checkBenz() {
        checkBox.click();
    }

    public void enterNm(String value) {
        enterNm.clear();
        enterNm.sendKeys(value);
    }

    public void clickAlert() {
        alertBtn.click();
    }


}
