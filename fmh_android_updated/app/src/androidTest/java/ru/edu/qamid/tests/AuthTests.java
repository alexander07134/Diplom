package ru.edu.qamid.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.edu.qamid.pages.AuthPage;
import ru.edu.qamid.steps.Steps;
import ru.edu.qamid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@Epic("Тестирование мобильного приложения Хоспис")
@Feature("Авторизация")
public class AuthTests {

    @Rule
    public ActivityScenarioRule<AppActivity> rule =
            new ActivityScenarioRule<>(AppActivity.class);

    private Steps steps = new Steps();
    private AuthPage authPage = new AuthPage();

    @Before
    public void setUp() {
        try {
            Thread.sleep(20000); // Ждём загрузки приложения
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Story("Позитивный сценарий авторизации")
    public void testValidLogin() {
        steps.login("login2", "password2");
        authPage.checkErrorIconIsDisplayed();
    }

    @Test
    @Story("Негативный сценарий: невалидный логин")
    public void testInvalidLogin() {
        steps.login("invalid", "password2");
        authPage.checkErrorIconIsDisplayed();
    }

    @Test
    @Story("Негативный сценарий: невалидный пароль")
    public void testInvalidPassword() {
        steps.login("login2", "wrong");
        authPage.checkErrorIconIsDisplayed();
    }

    @Test
    @Story("Негативный сценарий: пустые поля")
    public void testEmptyFields() {
        authPage.clickEnterButton();
        authPage.checkErrorIconIsDisplayed();
    }
    @Test
    @Story("Негативный сценарий: пустой логин")
    public void testEmptyLogin() {
        steps.login("", "password2");
        authPage.checkErrorIconIsDisplayed();
    }

    @Test
    @Story("Негативный сценарий: пустой пароль")
    public void testEmptyPassword() {
        steps.login("login2", "");
        authPage.checkErrorIconIsDisplayed();
    }

    @Test
    @Story("Негативный сценарий: логин с пробелами в начале")
    public void testLoginWithLeadingSpaces() {
        steps.login(" login2", "password2");
        authPage.checkErrorIconIsDisplayed();
    }

    @Test
    @Story("Негативный сценарий: логин с пробелами в конце")
    public void testLoginWithTrailingSpaces() {
        steps.login("login2 ", "password2");
        authPage.checkErrorIconIsDisplayed();
    }

    @Test
    @Story("Негативный сценарий: логин в верхнем регистре")
    public void testLoginUpperCase() {
        steps.login("LOGIN2", "password2");
        authPage.checkErrorIconIsDisplayed();
    }

    @Test
    @Story("Негативный сценарий: логин в смешанном регистре")
    public void testLoginMixedCase() {
        steps.login("LoGiN2", "password2");
        authPage.checkErrorIconIsDisplayed();
    }

    @Test
    @Story("Негативный сценарий: спецсимволы в логине")
    public void testLoginWithSpecialChars() {
        steps.login("!@#$%^&*()", "password2");
        authPage.checkErrorIconIsDisplayed();
    }
}