package tests.local;

import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AndroidTests extends TestBase {

    @Test
    void wikipediaSearchTest() {
        back();
        $(MobileBy.AccessibilityId("Search Wikipedia")).click();
        $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        $$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(sizeGreaterThan(0));
    }

    @Test
    void openEditsTest() {
        step("Close getting started by using back button", () ->
                back()
        );

        step("Open edits page", () ->
                $(MobileBy.AccessibilityId("Edits")).click()
        );

        step("Check: page content is enabled", () -> {
            $(MobileBy.id(("org.wikipedia.alpha:id/messageTitleView"))).
                    shouldHave(text("Did you know that everyone can edit Wikipedia?"));
        });
    }

    @Test
    void gettingStartedTest() {

        step("1st screen check", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView")).
                        shouldHave(text("The Free Encyclopedia …in over 300 languages"))
        );

        step("go to 2nd screen", () -> {
            $(MobileBy.id(("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))).
                    click();
        });

        step("2nd screen check", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView")).
                        shouldHave(text("New ways to explore"))
        );

        step("go to 2nd screen", () -> {
            $(MobileBy.id(("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))).
                    click();
        });

        step("3rd screen check", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/secondaryTextView")).
                        shouldHave(text("Join Wikipedia"))
        );

        step("go to 4th screen", () -> {
            $(MobileBy.id(("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))).
                    click();
        });

        step("4th screen check", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/switchView")).
                        click()
        );

        step("get out from onboarding", () -> {
            $(MobileBy.id(("org.wikipedia.alpha:id/fragment_onboarding_done_button"))).
                    click();
        });

        step("should be Wikipedia title on main page", () -> {
            $(MobileBy.id(("org.wikipedia.alpha:id/main_toolbar_wordmark"))).
                    shouldBe(enabled);
        });

    }

}