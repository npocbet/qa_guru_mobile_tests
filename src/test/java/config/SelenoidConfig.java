package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/selenoid.properties"
})
public interface SelenoidConfig extends Config {

    @Key("selenoidUrl")
    String selenoidUrl();

    @Key("selenoidAppversion")
    String selenoidAppversion();

    @Key("appPath")
    String appPath();
}