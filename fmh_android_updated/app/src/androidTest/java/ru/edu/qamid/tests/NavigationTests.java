package ru.edu.qamid.tests;

import static androidx.test.espresso.Espresso.onIdle;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.edu.qamid.pages.AuthPage;
import ru.edu.qamid.pages.MainPage;
import ru.edu.qamid.pages.NewsPage;
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
    private NewsPage newsPage = new NewsPage();
    private ActivityScenario<AppActivity> scenario;

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(AppActivity.class);
        scenario.onActivity(activity -> {});
        onIdle();
        authPage.waitForAnyScreen();
        authPage.logoutIfNeeded();
        authPage.waitForLoad();
        // Логинимся
        steps.loginWithValidData();
    }

    @After
    public void tearDown() {
        if (scenario != null) {
            scenario.close();
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

    @Test
    @Story("Переключение между Main и News")
    public void testSwitchBetweenMainAndNews() {

        mainPage.checkMainMenuButtonIsDisplayed();


        newsPage.clickAllNews();
        newsPage.checkNewsListIsDisplayed();


        mainPage.clickMainMenu();
        mainPage.checkMainMenuButtonIsDisplayed();
    }

    @Test
    @Story("Отображение кнопки ALL NEWS на главном экране")
    public void testAllNewsButtonIsDisplayed() {
        onView(withId(NewsPage.ALL_NEWS_BUTTON)).check(matches(isDisplayed()));
    }

    @Test
    @Story("Свёртывание и восстановление приложения")
    public void testAppBackgroundAndResume() {

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.moveToState(Lifecycle.State.RESUMED);

        mainPage.checkMainMenuButtonIsDisplayed();
    }
}