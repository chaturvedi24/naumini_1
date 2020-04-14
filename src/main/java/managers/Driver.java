package managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    private static WebDriver driver;
    private static String browser;
    private static String browserVersion;
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    public static void setBrowserNmVersion(String browserNm, String browserVer) {
        browser = browserNm;
        browserVersion = browserVer;
    }

    public static void initializeDriver() {
        browser = System.getProperty("browser") !=null ? System.getProperty("browser"): browser != null? browser : "chrome";
       browserVersion = System.getProperty("browserVersion") !=null ? System.getProperty("browserVersion"): browserVersion != null? browser : "";
        switch (browser) {
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

}
