package cloud.autotests.tests;

import cloud.autotests.config.App;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Feature("Authorization")
public class AuthorizationTests extends TestBase {

    @Test
    @AllureId("5416")
    @DisplayName("User can log in with valid credentials")
    void loginTestWithValidCredentials() {
        step("Open login page", () ->
                open("/login"));

        step("Fill login form with valid credentials", () -> {
            $("#j_username").setValue(App.config.userLogin());
            $("#j_password").setValue(App.config.userPassword())
                    .pressEnter();
        });

        step("Verify successful authorization", () ->
                $(".js-account-profile-link").shouldHave(text(" Welcome Ekaterina")));
    }

    @Test
    @AllureId("5417")
    @DisplayName("User cannot log in with invalid password")
    void loginTestWithInvalidUsername() {
        step("Open login page", () ->
                open("/login"));

        step("Fill login form with invalid username and valid password", () -> {
            $("#j_username").setValue("katharinegolovko123");
            $("#j_password").setValue(App.config.userPassword())
                    .pressEnter();
        });

        step("Verify authorization was unsuccessful", () ->
                $(".alert").shouldHave(text("Your username or password was incorrect.")));
    }

    @Test
    @AllureId("5418")
    @DisplayName("User cannot log in with invalid password")
    void loginTestWithInvalidPassword() {
        step("Open login page", () ->
                open("/login"));

        step("Fill login form with valid username and invalid password", () -> {
            $("#j_username").setValue(App.config.userLogin());
            $("#j_password").setValue("Password")
                    .pressEnter();
        });

        step("Verify authorization was unsuccessful", () ->
                $(".alert").shouldHave(text("Your username or password was incorrect.")));
    }


}