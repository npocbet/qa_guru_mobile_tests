package drivers;

import Config.ProjectConfig;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static utils.FileUtils.getAbsolutePath;

public class LocalMobileDriver implements WebDriverProvider {
    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        //desiredCapabilities.setCapability("deviceName", "Pixel_4_API_30");
        desiredCapabilities.setCapability("deviceName", "w4mfsgaqhymv8xai");
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("version", "11.0");

        desiredCapabilities.setCapability("appPackage", "org.wikipedia.alpha");
        desiredCapabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        desiredCapabilities.setCapability("app",
                getAbsolutePath("src/test/resources/apk/app-alpha-universal-release.apk"));

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        return new AndroidDriver(
                getWebBrowserstackUrl(), desiredCapabilities);

    }

    public static URL getWebBrowserstackUrl(){
        try {
            return new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


}
