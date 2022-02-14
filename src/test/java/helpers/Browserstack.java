package helpers;

import config.BrowserStackConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class Browserstack {
    public static BrowserStackConfig config = ConfigFactory.create(BrowserStackConfig.class, System.getProperties());


    public static String videoUrl(String sessionId) {
        return given()
                .auth().basic(config.username(), config.password())
                .when()
                .get(config.remote_api_url() + sessionId + ".json")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .path(config.video_path_on_api());
    }
}