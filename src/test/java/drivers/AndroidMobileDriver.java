package drivers;

import config.*;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static utils.FileUtils.getAbsolutePath;

public class AndroidMobileDriver implements WebDriverProvider {
    public static HostConfig hostConfig = ConfigFactory.create(HostConfig.class, System.getProperties());

    public static BrowserStackConfig BrowserstackConfig = ConfigFactory.create(BrowserStackConfig.class, System.getProperties());
    public static SelenoidConfig selenoidConfig = ConfigFactory.create(SelenoidConfig.class);
    public static LocalConfig localConfig = ConfigFactory.create(LocalConfig.class, System.getProperties());
    public static RealConfig realConfig = ConfigFactory.create(RealConfig.class, System.getProperties());

    @Override
    public WebDriver createDriver(@Nonnull DesiredCapabilities desiredCapabilities) {
        if (hostConfig.host().equals(Host.BROWSERSTACK)) {
            return getBrowserStackMobileDriver(desiredCapabilities);
        }

        if (hostConfig.host().equals(Host.LOCAL)) {
            return getLocalMobileDriver(desiredCapabilities);
        }

        if (hostConfig.host().equals(Host.REAL)) {
            return getRealMobileDriver(desiredCapabilities);
        }

        return getSelenoidMobileDriver(desiredCapabilities);
    }

    public AndroidDriver<WebElement> getBrowserStackMobileDriver(@Nonnull DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability("browserstack.user", BrowserstackConfig.username());
        desiredCapabilities.setCapability("browserstack.key", BrowserstackConfig.password());
        desiredCapabilities.setCapability("app", BrowserstackConfig.app());
        desiredCapabilities.setCapability("device", BrowserstackConfig.device());
        desiredCapabilities.setCapability("os_version", BrowserstackConfig.os_version());
        desiredCapabilities.setCapability("project", BrowserstackConfig.project());
        desiredCapabilities.setCapability("build", BrowserstackConfig.build());
        desiredCapabilities.setCapability("name", BrowserstackConfig.name());

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        return new AndroidDriver(
                getWebBrowserstackUrl(), desiredCapabilities);
    }

    public AndroidDriver<WebElement> getSelenoidMobileDriver(@Nonnull DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability("deviceName", "android");
        desiredCapabilities.setCapability("version", selenoidConfig.selenoidAppversion());

        desiredCapabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia.alpha");

        desiredCapabilities.setCapability("app", selenoidConfig.appPath());
        try {
            return new AndroidDriver<>(new URL(selenoidConfig.selenoidUrl()), desiredCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public AndroidDriver<WebElement> getLocalMobileDriver(@Nonnull DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability("deviceName", localConfig.deviceName());
        desiredCapabilities.setCapability("platformName", localConfig.platformName());
        desiredCapabilities.setCapability("version", localConfig.version());

        desiredCapabilities.setCapability("appPackage", localConfig.appPackage());
        desiredCapabilities.setCapability("appActivity", localConfig.appActivity());
        desiredCapabilities.setCapability("app",
                getAbsolutePath(localConfig.appPath()));

        return new AndroidDriver(
                getLocalWebBrowserUrl(), desiredCapabilities);
    }

    public AndroidDriver<WebElement> getRealMobileDriver(@Nonnull DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability("deviceName", realConfig.deviceName());
        desiredCapabilities.setCapability("platformName", realConfig.platformName());
        desiredCapabilities.setCapability("version", realConfig.version());

        desiredCapabilities.setCapability("appPackage", realConfig.appPackage());
        desiredCapabilities.setCapability("appActivity", realConfig.appActivity());
        desiredCapabilities.setCapability("app",
                getAbsolutePath(realConfig.appPath()));

        return new AndroidDriver(
                getLocalWebBrowserUrl(), desiredCapabilities);
    }

    public static URL getLocalWebBrowserUrl() {
        try {
            return new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static URL getWebBrowserstackUrl(){
        try {
            return new URL("http://hub.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
