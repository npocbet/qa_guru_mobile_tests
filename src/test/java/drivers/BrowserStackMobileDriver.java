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

        desiredCapabilities.setCapability("browserstack.user", config.username());
        desiredCapabilities.setCapability("browserstack.key", config.password());
        desiredCapabilities.setCapability("app", config.app());
        desiredCapabilities.setCapability("device", config.device());
        desiredCapabilities.setCapability("os_version", config.os_version());
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
