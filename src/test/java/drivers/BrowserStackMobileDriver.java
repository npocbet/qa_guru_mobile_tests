package drivers;

import Config.ProjectConfig;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackMobileDriver implements WebDriverProvider {
    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

//        // Set your access credentials
//        desiredCapabilities.setCapability("browserstack.user", "svyatoslavkravts_qty3dX");
//        desiredCapabilities.setCapability("browserstack.key", "Vp4F7XXxpn2A2MdgxqF4");
//
//        // Set URL of the application under test
//        desiredCapabilities.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");
//
//        // Specify device and os_version for testing
//
////        desiredCapabilities.setCapability("device", "Google Pixel 3");
////        desiredCapabilities.setCapability("os_version", "9.0");
//        desiredCapabilities.setCapability("device", "Samsung Galaxy S9");
//        desiredCapabilities.setCapability("os_version", "8.0");
//        // Set other BrowserStack capabilities
//        desiredCapabilities.setCapability("project", "First Java Project");
//        desiredCapabilities.setCapability("build", "browserstack-build-1");
//        desiredCapabilities.setCapability("name", "first_test");
        // Set your access credentials
        desiredCapabilities.setCapability("browserstack.user", config.username());
        desiredCapabilities.setCapability("browserstack.key", config.password());

        // Set URL of the application under test
        desiredCapabilities.setCapability("app", config.app());

        // Specify device and os_version for testing

//        desiredCapabilities.setCapability("device", "Google Pixel 3");
//        desiredCapabilities.setCapability("os_version", "9.0");
        desiredCapabilities.setCapability("device", config.device());
        desiredCapabilities.setCapability("os_version", config.os_version());
        // Set other BrowserStack capabilities
        desiredCapabilities.setCapability("project", config.project());
        desiredCapabilities.setCapability("build", config.build());
        desiredCapabilities.setCapability("name", config.name());

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        return new AndroidDriver(
                getWebBrowserstackUrl(), desiredCapabilities);

    }

    public static URL getWebBrowserstackUrl(){
        try {
            return new URL("http://hub.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
