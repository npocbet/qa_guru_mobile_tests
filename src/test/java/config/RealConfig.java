package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/real.properties"
})
public interface RealConfig extends Config {
    String deviceName();
    String platformName();
    String version();
    String appPackage();
    String appActivity();
    String appPath();
}