package managers;

import Utilities.Constant;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static RemoteWebDriver driver;
    protected static DesiredCapabilities capability;
    protected static String node;
    protected static String oPlatform;

    public static RemoteWebDriver getDriver() {
        if (driver == null) {
            createInstance(Constant.BROWSER_NAME, Constant.BROWSER_VERSION, Constant.PLATFORM);
        }
        return driver;
    }

    public static void createInstance(String browserName, String browserVersion, String platform) {
        browserName = (browserName != null) ? browserName : "chrome";
        oPlatform = platform;
        setDesiredCapabilities(browserName);
        setPlatform(platform);
        setBrowserName(browserName);
        capability.setVersion(browserVersion);

        if (Constant.GRID_MODE.equals("on")) {
            node = (System.getProperty("remoteURL") == null) ? Constant.hubURL : "";
            try {
                driver = new RemoteWebDriver(new URL(node), capability);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            createLocalInstance(browserName, browserVersion);
        }
        setWebDriver();
    }

    public static void setWebDriver() {
        driver.manage().timeouts().implicitlyWait(Constant.TIMEOUT_IMPLICIT, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.navigate().to(Constant.URL);
    }

    private static void setBrowserName(String browserName) {
        switch (browserName) {
            case "chrome":
                capability.setBrowserName(BrowserType.CHROME);
                break;
            case "firefox":
                capability.setBrowserName(BrowserType.FIREFOX);
                break;
            case "safari":
                capability.setBrowserName(BrowserType.SAFARI);
                break;
            case "ie":
                capability.setBrowserName("internet explorer");
                break;
            case "edge":
                capability.setBrowserName(BrowserType.EDGE);
                break;
        }
    }

    private static void setPlatform(String platform) {
        switch (platform) {
            case "windows":
                capability.setPlatform(Platform.WINDOWS);
                break;
            case "mac":
                capability.setPlatform(Platform.MAC);
                break;
            case "linux":
                capability.setPlatform(Platform.LINUX);
                break;
            default:
                System.exit(1);
                break;
        }
    }

    private static void setDesiredCapabilities(String browserName) {
        capability = new DesiredCapabilities();
        switch (browserName) {
            case "chrome":
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                capability = DesiredCapabilities.chrome();
                capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capability.setCapability(ChromeOptions.CAPABILITY, options);
                System.out.println("Success : setDesiredCapabilities");
                break;
            case "firefox":
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setPreference("enableNativeEvents", true);
                firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
                firefoxProfile.setPreference("browser.download.folderList", 2);
                //firefoxProfile.setPreference("browser.download.dir", DOWNLOAD_PATH);
                firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
                firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
                firefoxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
                firefoxProfile.setPreference("browser.download.useDownloadDir", true);
                firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
                firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                firefoxProfile.setPreference("browser.download.manager.closeWhenDone", true);
                firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
                firefoxProfile.setPreference("browser.download.manager.useWindow", false);
                firefoxProfile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
                firefoxProfile.setPreference("pdfjs.disabled", true);
                firefoxProfile.setPreference("browser.cache.disk.enable", false);
                firefoxProfile.setPreference("browser.cache.memory.enable", false);
                firefoxProfile.setPreference("browser.cache.offline.enable", false);
                firefoxProfile.setPreference("network.http.use-cache", false);
                capability = DesiredCapabilities.firefox();
                //enables to run your existing tests on Firefox 46+ without modifying your tests.
                capability.setCapability("marionette", true);
                capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capability.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
                System.out.println("Success : setDesiredCapabilities");
                break;
            case "safari":
                capability = DesiredCapabilities.safari();
                capability.setJavascriptEnabled(true);
                capability.setCapability("unexpectedAlertBehaviour", "accept");
                break;
            case "ie":
                capability = DesiredCapabilities.internetExplorer();
                //"Enhanced Protected Mode" must be disabled for IE 10 and higher
                capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capability.setCapability("ignoreProtectedModeSettings", true);
                capability.setCapability("requireWindowFocus", true);
                capability.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, true);
                capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capability.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
                capability.setCapability("ignoreZoomSetting", true);
                capability.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
                capability.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                break;
            case "edge":
                capability = DesiredCapabilities.edge();
                capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capability.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
                capability.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
                break;
            default:
                System.exit(1);
                break;
        }
    }

    private static void createLocalInstance(String browserName, String browserVersion) {
        ChromeOptions chromeOptions = new ChromeOptions();
        switch (browserName) {
            case "chrome":
                chromeOptions.merge(capability);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.merge(capability);
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "ie":
                InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                internetExplorerOptions.merge(capability);
                WebDriverManager.iedriver().version(browserVersion).setup();
                driver = new InternetExplorerDriver(internetExplorerOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.merge(capability);
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                chromeOptions = new ChromeOptions();
                chromeOptions.merge(capability);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
        }
    }

}
