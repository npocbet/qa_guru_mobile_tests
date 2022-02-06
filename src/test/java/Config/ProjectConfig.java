package Config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/remote.properties"
})
public interface ProjectConfig extends Config {

    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("app")
    String app();

    @Key("device")
    @DefaultValue("Samsung Galaxy S9")
    String device();

    @Key("os_version")
    @DefaultValue("8.0")
    String os_version();

    @Key("remoteDriverUrl")
    String remoteDriverUrl();

    @Key("project")
    @DefaultValue("First Java Project")
    String project();

    @Key("build")
    @DefaultValue("browserstack-build-1")
    String build();

    @Key("name")
    @DefaultValue("first_test")
    String name();

}