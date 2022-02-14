package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties"
})
public interface HostConfig extends Config {

    @Key("host")
    @DefaultValue("local")
    Host host();
}
