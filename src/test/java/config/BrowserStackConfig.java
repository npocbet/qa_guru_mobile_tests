package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties"
})
public interface BrowserStackConfig extends Config {

    String username();
    String password();
    String app();

    @DefaultValue("Samsung Galaxy S9")
    String device();

    @DefaultValue("8.0")
    String os_version();

    @DefaultValue("First Java Project")
    String project();

    @DefaultValue("browserstack-build-1")
    String build();

    @DefaultValue("first_test")
    String name();

    String remote_api_url();
    String video_path_on_api();
}