package helpers;

import Config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class Browserstack {
    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());


    public static String videoUrl(String sessionId) {
        return given()
                .auth().basic(config.username(), config.password())
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId + ".json")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .path("automation_session.video_url");
    }
}