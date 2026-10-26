package ru.edu.qamid.tests;

import static androidx.test.espresso.Espresso.onIdle;

import androidx.test.core.app.ActivityScenario;
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
import ru.edu.qamid.pages.QuotesPage;
import ru.edu.qamid.steps.Steps;
import ru.edu.qamid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@Epic("Тестирование мобильного приложения Хоспис")
@Feature("Цитаты")
public class QuotesTests {

    @Rule
    public ActivityScenarioRule<AppActivity> rule =
            new ActivityScenarioRule<>(AppActivity.class);

    private Steps steps = new Steps();
    private AuthPage authPage = new AuthPage();
    private QuotesPage quotesPage = new QuotesPage();
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
    @Story("Просмотр раздела Цитаты")
    public void testQuotesDisplay() {
        quotesPage.openQuotes();
        quotesPage.checkQuotesListIsDisplayed();
    }

    @Test
    @Story("Текст цитаты отображается")
    public void testQuoteTextIsDisplayed() {
        quotesPage.openQuotes();
        quotesPage.checkQuotesListIsDisplayed();
        quotesPage.checkAtLeastOneQuoteIsDisplayed();
    }
}