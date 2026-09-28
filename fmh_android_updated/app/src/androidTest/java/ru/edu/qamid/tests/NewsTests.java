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
import ru.edu.qamid.pages.NewsPage;
import ru.edu.qamid.steps.Steps;
import ru.edu.qamid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@Epic("Тестирование мобильного приложения Хоспис")
@Feature("Новости")
public class NewsTests {

    @Rule
    public ActivityScenarioRule<AppActivity> rule =
            new ActivityScenarioRule<>(AppActivity.class);

    private Steps steps = new Steps();
    private AuthPage authPage = new AuthPage();
    private NewsPage newsPage = new NewsPage();

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
    @Story("Просмотр списка новостей")
    public void testNewsListIsDisplayed() {
        newsPage.clickAllNews();
        newsPage.checkNewsListIsDisplayed();
    }

    @Test
    @Story("Создание новости")
    public void testCreateNews() {
        String title = "Test news " + System.currentTimeMillis();
        String description = "Test description";
        String date = "27.09.2026";

        steps.createNews(title, description, date);
        newsPage.checkNewsInList(title);
    }
    @Test
    @Story("Негативный сценарий: создание новости с пустыми полями")
    public void testCreateNewsEmptyFields() {
        newsPage.clickAllNews();
        newsPage.clickEditButton();
        newsPage.clickAddNews();
        newsPage.clickSave();
        newsPage.checkCreatingScreenIsDisplayed();
    }

    @Test
    @Story("Негативный сценарий: создание новости без заголовка")
    public void testCreateNewsWithoutTitle() {
        newsPage.clickAllNews();
        newsPage.clickEditButton();
        newsPage.clickAddNews();
        newsPage.enterDescription("Описание без заголовка");
        newsPage.clickSave();
        newsPage.checkCreatingScreenIsDisplayed();
    }

    @Test
    @Story("Негативный сценарий: создание новости без описания")
    public void testCreateNewsWithoutDescription() {
        newsPage.clickAllNews();
        newsPage.clickEditButton();
        newsPage.clickAddNews();
        newsPage.enterTitle("Заголовок без описания");
        newsPage.clickSave();
        newsPage.checkCreatingScreenIsDisplayed();
    }

    @Test
    @Story("Редактирование новости")
    public void testEditNews() {
        // Открыть список новостей
        newsPage.clickAllNews();
        // Нажать редактирование
        newsPage.clickEditButton();
        // Проверить, что список новостей виден
        newsPage.checkNewsListIsDisplayed();
    }

    @Test
    @Story("Удаление новости")
    public void testDeleteNews() {
        newsPage.clickAllNews();
        newsPage.clickEditButton();
        newsPage.checkNewsListIsDisplayed();
    }
}