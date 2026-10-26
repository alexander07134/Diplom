package ru.edu.qamid.tests;

import static androidx.test.espresso.Espresso.onIdle;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.edu.qamid.pages.AuthPage;
import ru.edu.qamid.steps.Steps;
import ru.edu.qamid.ui.AppActivity;
import ru.edu.qamid.utils.TestData;

@RunWith(AndroidJUnit4.class)
@Epic("Тестирование мобильного приложения Хоспис")
@Feature("Авторизация")
public class AuthTests {

    private ActivityScenario<AppActivity> scenario;
    private Steps steps = new Steps();
    private AuthPage authPage = new AuthPage();

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(AppActivity.class);
        scenario.onActivity(activity -> {

        });
        onIdle();
        authPage.waitForAnyScreen();
        authPage.logoutIfNeeded();
        authPage.waitForLoad();
    }

    @After
    public void tearDown() {
        if (scenario != null) {
            scenario.close();
        }
    }



    @Test
    @Story("Позитивный сценарий: вход с валидными данными")
    public void testValidLogin() {
        steps.loginWithValidData();
        authPage.checkMainScreenIsDisplayed();
    }

    @Test
    @Story("Негативный сценарий: невалидный логин")
    public void testInvalidLogin() {
        steps.loginWithInvalidData(TestData.INVALID_LOGIN, TestData.VALID_PASSWORD);
        authPage.checkErrorMessageIsDisplayed(TestData.ERROR_INVALID_CREDENTIALS);
    }

    @Test
    @Story("Негативный сценарий: невалидный пароль")
    public void testInvalidPassword() {
        steps.loginWithInvalidData(TestData.VALID_LOGIN, TestData.INVALID_PASSWORD);
        authPage.checkErrorMessageIsDisplayed(TestData.ERROR_INVALID_CREDENTIALS);
    }

    @Test
    @Story("Негативный сценарий: пустые поля")
    public void testEmptyFields() {
        steps.loginWithEmptyFields();
        authPage.checkErrorMessageIsDisplayed(TestData.ERROR_EMPTY_FIELDS);
    }



    @Test
    @Story("Негативный сценарий: пустой логин")
    public void testEmptyLogin() {
        steps.loginWithEmptyLogin(TestData.VALID_PASSWORD);
        authPage.checkErrorMessageIsDisplayed(TestData.ERROR_EMPTY_FIELDS);
    }

    @Test
    @Story("Негативный сценарий: пустой пароль")
    public void testEmptyPassword() {
        steps.loginWithEmptyPassword(TestData.VALID_LOGIN);
        authPage.checkErrorMessageIsDisplayed(TestData.ERROR_EMPTY_FIELDS);
    }

    @Test
    @Story("Позитивный сценарий: пробел в начале логина игнорируется")
    public void testLoginWithLeadingSpace() {
        steps.loginWithWhitespaceLogin(TestData.LOGIN_WITH_LEADING_SPACE);
        authPage.checkMainScreenIsDisplayed();
    }

    @Test
    @Story("Позитивный сценарий: пробел в конце логина игнорируется")
    public void testLoginWithTrailingSpace() {
        steps.loginWithWhitespaceLogin(TestData.LOGIN_WITH_TRAILING_SPACE);
        authPage.checkMainScreenIsDisplayed();
    }

    @Test
    @Story("Негативный сценарий: логин в верхнем регистре")
    public void testLoginUppercase() {
        steps.loginWithInvalidData(TestData.LOGIN_UPPERCASE, TestData.VALID_PASSWORD);
        authPage.checkErrorMessageIsDisplayed(TestData.ERROR_INVALID_CREDENTIALS);
    }

    @Test
    @Story("Негативный сценарий: логин в смешанном регистре")
    public void testLoginMixedCase() {
        steps.loginWithInvalidData(TestData.LOGIN_MIXED_CASE, TestData.VALID_PASSWORD);
        authPage.checkErrorMessageIsDisplayed(TestData.ERROR_INVALID_CREDENTIALS);
    }

    @Test
    @Story("Негативный сценарий: спецсимволы в логине")
    public void testLoginSpecialChars() {
        steps.loginWithInvalidData(TestData.LOGIN_SPECIAL_CHARS, TestData.VALID_PASSWORD);
        authPage.checkErrorMessageIsDisplayed(TestData.ERROR_INVALID_CREDENTIALS);
    }

    @Test
    @Story("Позитивный сценарий: маскировка пароля")
    public void testPasswordIsMasked() {
        steps.checkPasswordMasking();
    }
}