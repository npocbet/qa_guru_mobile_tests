package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties"
})
public interface BrowserStackConfig extends Config {

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

    @Key("project")
    @DefaultValue("First Java Project")
    String project();

    @Key("build")
    @DefaultValue("browserstack-build-1")
    String build();

    @Key("name")
    @DefaultValue("first_test")
    String name();

    @Key("remote_api_url")
    String remote_api_url();

    @Key("video_path_on_api")
    String video_path_on_api();

}