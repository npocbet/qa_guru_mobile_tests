package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties"
})
public interface LocalConfig extends Config {
    String deviceName();
    String platformName();
    String version();
    String appPackage();
    String appActivity();
    String appPath();
}