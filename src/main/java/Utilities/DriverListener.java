package Utilities;

import managers.Driver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class DriverListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        if (iInvokedMethod.isTestMethod()) {
            String browserName = iInvokedMethod.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
            String browserVersion = iInvokedMethod.getTestMethod().getXmlTest().getLocalParameters().get("browserVersion");
            String platform = iInvokedMethod.getTestMethod().getXmlTest().getLocalParameters().get("platform");
            String testapp = iInvokedMethod.getTestMethod().getXmlTest().getLocalParameters().get("testapp");
            Constant.TEST_APP = testapp;
            if (testapp.equalsIgnoreCase("web")) {
                Constant.BROWSER_NAME = browserName;// set Browser Name
                Constant.BROWSER_VERSION = browserVersion;// set Browser Version
                Constant.PLATFORM = platform;// set platform
                Driver.createInstance(browserName, browserVersion, platform);
            }

        }
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        if (iInvokedMethod.isTestMethod() && iInvokedMethod.getTestMethod().getXmlTest().getLocalParameters().get("testapp").equalsIgnoreCase("web")) {
            Driver.getDriver().quit();
        }
    }
}
