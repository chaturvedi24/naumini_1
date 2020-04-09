package managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        String browserName = System.getProperty("browser");
        String browserVersion = System.getProperty("browserVersion");

        if (driver == null) {
            switch (browserName) {
                case "chrome":
                    if (!browserVersion.equals("")) {
                        WebDriverManager.chromedriver().version(browserVersion).setup();
                    } else WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    if (!browserVersion.equals("")) {
                        WebDriverManager.firefoxdriver().version(browserVersion).setup();
                    } else WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    if (!browserVersion.equals("")) {
                        WebDriverManager.edgedriver().version(browserVersion).setup();
                    } else WebDriverManager.edgedriver().setup();

                    driver = new EdgeDriver();
                    break;
            }
        }

        return driver;
    }

}
