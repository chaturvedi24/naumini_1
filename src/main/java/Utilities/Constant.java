package Utilities;

public class Constant {

    private static final String BROWSER_NAME_KEY = "browser";
    private static final String TIMEOUT_IMPLICIT_KEY = "implicitWaitTime";
    private static final String URL_KEY = "url";
    private static final String BROWSER_VERSION_KEY = "browserVersion";
    private static final String PLATFORM_KEY = "platform";
    private static final String HUBURL_KEY = "hubURL";
    private static final String GRID_MODE_KEY = "grid-mode";

    public static String URL ;
    public static String BROWSER_NAME;
    public static int TIMEOUT_IMPLICIT;
    public static String BROWSER_VERSION;
    public static String PLATFORM;
    public static String hubURL;
    public static String GRID_MODE;
    public static String TEST_APP;

    static {
        try {
            loadProperties();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loading  values to global variables
     */
    public static void loadProperties() {
        ConfigFileReader configFileReader = new ConfigFileReader(System.getProperty("region"));

        try {
            // Elements and assign values to public variables so it could be accessed globally.
            BROWSER_NAME = (configFileReader.getPropertyValue(BROWSER_NAME_KEY)) == null ? "chrome" : configFileReader.getPropertyValue(BROWSER_NAME_KEY);
            URL = configFileReader.getPropertyValue(URL_KEY);
            TIMEOUT_IMPLICIT = Integer.parseInt(configFileReader.getPropertyValue(TIMEOUT_IMPLICIT_KEY));
            BROWSER_VERSION = configFileReader.getPropertyValue(BROWSER_VERSION_KEY);
            PLATFORM = configFileReader.getPropertyValue(PLATFORM_KEY);
            hubURL = (System.getProperty("host") != null) ? System.getProperty("host") : (configFileReader.getPropertyValue(HUBURL_KEY) != null) ? configFileReader.getPropertyValue(HUBURL_KEY) :"";
            GRID_MODE = (System.getProperty("grid") != null) ? System.getProperty("grid") : (configFileReader.getPropertyValue(GRID_MODE_KEY) != null) ? configFileReader.getPropertyValue(GRID_MODE_KEY) :"off";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
