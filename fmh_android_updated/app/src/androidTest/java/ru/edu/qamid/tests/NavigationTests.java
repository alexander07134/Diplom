package ru.edu.qamid.tests;

import androidx.test.espresso.Espresso;
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
import ru.edu.qamid.pages.MainPage;
import ru.edu.qamid.steps.Steps;
import ru.edu.qamid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@Epic("Тестирование мобильного приложения Хоспис")
@Feature("Навигация")
public class NavigationTests {

    @Rule
    public ActivityScenarioRule<AppActivity> rule =
            new ActivityScenarioRule<>(AppActivity.class);

    private Steps steps = new Steps();
    private AuthPage authPage = new AuthPage();
    private MainPage mainPage = new MainPage();

    @Before
    public void setUp() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        steps.loginIfNeeded("login2", "password2");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Story("Проверка отображения главного меню")
    public void testMainMenuIsDisplayed() {
        mainPage.checkMainMenuButtonIsDisplayed();
    }
    @Test
    @Story("Закрытие приложения через системную кнопку Назад")
    public void testCloseAppViaBack() {
        try {
            Espresso.pressBack();
        } catch (androidx.test.espresso.NoActivityResumedException e) {
        }
    }
}