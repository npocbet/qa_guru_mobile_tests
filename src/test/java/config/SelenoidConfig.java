package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/selenoid.properties"
})
public interface SelenoidConfig extends Config {

    @DefaultValue("http://user1:1234@selenoid.autotests.cloud:4444/wd/hub")
    String selenoidUrl();

    @DefaultValue("8.1")
    String selenoidAppversion();

    @DefaultValue("https://github.com/qa-guru/wikipedia-mobile-tests/raw/local/src/test/resources/apk/app-alpha-universal-release.apk")
    String appPath();
}