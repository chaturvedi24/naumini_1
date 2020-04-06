import PageFactory.PracticePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Sample1 {

    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.gecko.driver","C:\\IJProjs\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("https://learn.letskodeit.com/p/practice");
        Thread.sleep(5000);

        PracticePage practicePage = new PracticePage(driver);

        practicePage.selectRadio();
        practicePage.selectDropDn("honda");
        practicePage.multiSelect("apple");
        practicePage.multiSelect("peach");
        practicePage.checkBenz();
        practicePage.enterNm("chatur");
        practicePage.clickAlert();

        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();

        Thread.sleep(3000);
        driver.quit();
    }

}
