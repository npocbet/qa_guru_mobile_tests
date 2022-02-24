package tests.browserstack;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class BrowserstackAndroidTests extends TestBase {

    @Test
    void wikipediaSearchTest() {
        $(MobileBy.AccessibilityId("Search Wikipedia")).click();
        $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        $$(byClassName("android.widget.TextView")).shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    @Test
    void openSupportTest() {
        step("Open app menu", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/menu_overflow_button")).click());
        step("Open support page", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/explore_overflow_donate")).click());
        step("Open support page in browser", () ->
                $(MobileBy.id("android:id/resolver_list"))
                        .$(MobileBy.id("android:id/icon")).click());
        step("Check: page content is enabled", () ->
            $(MobileBy.id(("android:id/content"))).shouldBe(enabled));
    }
}