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
import ru.edu.qamid.pages.NewsPage;
import ru.edu.qamid.steps.Steps;
import ru.edu.qamid.ui.AppActivity;
import ru.edu.qamid.utils.TestData;

@RunWith(AndroidJUnit4.class)
@Epic("Тестирование мобильного приложения Хоспис")
@Feature("Новости")
public class NewsTests {

    private ActivityScenario<AppActivity> scenario;
    private Steps steps = new Steps();
    private AuthPage authPage = new AuthPage();
    private NewsPage newsPage = new NewsPage();

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(AppActivity.class);
        scenario.onActivity(activity -> {});
        onIdle();
        authPage.waitForAnyScreen();
        authPage.logoutIfNeeded();
        authPage.waitForLoad();
        steps.loginWithValidData();
    }

    @After
    public void tearDown() {
        if (scenario != null) {
            scenario.close();
        }
    }

    @Test
    @Story("Просмотр списка новостей")
    public void testNewsListIsDisplayed() {
        newsPage.clickAllNews();
        newsPage.checkNewsListIsDisplayed();
    }

    @Test
    @Story("Создание новости")
    public void testCreateNews() {
        String title = TestData.NEWS_TITLE + System.currentTimeMillis();
        steps.createNews(title, TestData.NEWS_DESCRIPTION, TestData.getTodayDate());

        newsPage.waitForControlPanel();
        onIdle();
        newsPage.checkNewsInList(title);
    }

    @Test
    @Story("Редактирование новости")
    public void testEditNews() {
        String oldTitle = TestData.NEWS_TITLE + System.currentTimeMillis();
        steps.createNews(oldTitle, TestData.NEWS_DESCRIPTION, TestData.getTodayDate());

        newsPage.waitForNewsList();
        newsPage.checkNewsInList(oldTitle);

        String newTitle = oldTitle + "_edited";
        steps.editNews(oldTitle, newTitle);

        newsPage.waitForNewsList();
        onIdle();
        newsPage.checkNewsInList(newTitle);
        newsPage.checkNewsNotInList(oldTitle);
    }

    @Test
    @Story("Редактирование описания новости")
    public void testEditNewsDescription() {
        String title = TestData.NEWS_TITLE + "desc_" + System.currentTimeMillis();
        steps.createNews(title, TestData.NEWS_DESCRIPTION, TestData.getTodayDate());

        newsPage.waitForNewsList();
        onIdle();
        newsPage.checkNewsInList(title);

        String newDescription = TestData.NEWS_DESCRIPTION_EDITED + " " + System.currentTimeMillis();
        steps.editNewsDescription(title, newDescription);

        newsPage.waitForNewsList();
        onIdle();
        newsPage.checkNewsInList(title);
        newsPage.checkDescriptionInList(title, newDescription);
    }

    @Test
    @Story("Удаление новости")
    public void testDeleteNews() {
        String title = TestData.NEWS_TITLE + System.currentTimeMillis();
        steps.createNews(title, TestData.NEWS_DESCRIPTION, TestData.getTodayDate());

        newsPage.waitForNewsList();
        onIdle();
        newsPage.checkNewsInList(title);

        steps.deleteNews(title);
        onIdle();
        newsPage.checkNewsNotInList(title);
    }
    @Test
    @Story("Негативный сценарий: создание новости с пустыми полями")
    public void testCreateNewsEmptyFields() {
        newsPage.clickAllNews();
        newsPage.clickEditButton();
        newsPage.clickAddNews();
        newsPage.clickSave();
        newsPage.checkErrorMessageIsDisplayed(TestData.ERROR_EMPTY_NEWS_FIELDS);
    }

    @Test
    @Story("Негативный сценарий: создание без заголовка")
    public void testCreateNewsWithoutTitle() {
        String description = TestData.NEWS_DESCRIPTION + System.currentTimeMillis();
        steps.createNewsWithoutTitle(description, TestData.getTodayDate());
    }

    @Test
    @Story("Негативный сценарий: создание без описания")
    public void testCreateNewsWithoutDescription() {
        String title = TestData.NEWS_TITLE + "no_desc_" + System.currentTimeMillis();
        steps.createNewsWithoutDescription(title, TestData.getTodayDate());
    }

    @Test
    @Story("Известный баг: создание новости с датой в будущем")
    public void testCreateNewsWithFutureDate() {
        String title = TestData.NEWS_TITLE + "future_" + System.currentTimeMillis();
        steps.createNewsWithFutureDate(title, TestData.NEWS_DESCRIPTION);

        newsPage.waitForControlPanel();
        onIdle();
        newsPage.checkNewsInList(title);
    }
}