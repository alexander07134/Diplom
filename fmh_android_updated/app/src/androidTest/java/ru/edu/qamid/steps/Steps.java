package ru.edu.qamid.steps;

import io.qameta.allure.kotlin.Step;
import ru.edu.qamid.pages.AuthPage;
import ru.edu.qamid.pages.NewsPage;
import ru.edu.qamid.utils.TestData;
import static androidx.test.espresso.Espresso.onIdle;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class Steps {
    private AuthPage authPage = new AuthPage();
    private NewsPage newsPage = new NewsPage();

    @Step("Авторизация с валидными данными")
    public void loginWithValidData() {
        if (authPage.isAuthorized()) {
            logout();
        }
        authPage.waitForLoad()
                .enterLogin(TestData.VALID_LOGIN)
                .enterPassword(TestData.VALID_PASSWORD)
                .clickEnterButton();
        authPage.checkMainScreenIsDisplayed();
    }

    @Step("Авторизация с невалидными данными: логин {login}")
    public void loginWithInvalidData(String login, String password) {
        if (authPage.isAuthorized()) {
            logout();
        }
        authPage.waitForLoad()
                .enterLogin(login)
                .enterPassword(password)
                .clickEnterButton();
    }

    @Step("Авторизация с пустыми полями")
    public void loginWithEmptyFields() {
        if (authPage.isAuthorized()) {
            logout();
        }
        authPage.waitForLoad().clickEnterButton();
    }

    @Step("Выход из аккаунта")
    public void logout() {
        // Заглушка
    }


    @Step("Авторизация: пустой логин, пароль {password}")
    public void loginWithEmptyLogin(String password) {
        if (authPage.isAuthorized()) {
            logout();
        }
        authPage.waitForLoad()
                .enterPasswordOnly(password)
                .clickEnterButton();
    }


    @Step("Авторизация: логин {login}, пустой пароль")
    public void loginWithEmptyPassword(String login) {
        if (authPage.isAuthorized()) {
            logout();
        }
        authPage.waitForLoad()
                .enterLoginOnly(login)
                .clickEnterButton();
    }


    @Step("Авторизация с логином, содержащим пробелы: \"{login}\"")
    public void loginWithWhitespaceLogin(String login) {
        if (authPage.isAuthorized()) {
            logout();
        }
        authPage.waitForLoad()
                .enterRawLogin(login)
                .enterPassword(TestData.VALID_PASSWORD)
                .clickEnterButton();
        authPage.checkMainScreenIsDisplayed();
    }


    @Step("Проверка маскировки пароля")
    public void checkPasswordMasking() {
        authPage.waitForLoad()
                .enterRawPassword(TestData.VALID_PASSWORD);
        authPage.checkPasswordIsMasked();
    }

    @Step("Создание новости с заголовком: {title}")
    public void createNews(String title, String description, String date) {
        newsPage.openControlPanel();
        newsPage.clickEditButton();
        newsPage.clickAddNews();
        newsPage.selectCategory(TestData.NEWS_CATEGORY);
        newsPage.enterTitle(title)
                .enterDescription(description)
                .enterDate(date)
                .enterTime(TestData.NEWS_TIME)
                .clickSave();
        newsPage.waitForControlPanel();
        onIdle();


        for (int i = 0; i < 4; i++) {
            onView(withId(NewsPage.NEWS_LIST)).perform(swipeUp());
        }
    }

    @Step("Редактирование новости: {oldTitle} → {newTitle}")
    public void editNews(String oldTitle, String newTitle) {
        newsPage.openControlPanel();


        for (int i = 0; i < 4; i++) {
            onView(withId(NewsPage.NEWS_LIST)).perform(swipeUp());
        }

        newsPage.scrollToNewsAndEdit(oldTitle);
        newsPage.enterTitle(newTitle);
        newsPage.clickSave();
        onIdle();
    }
    @Step("Удаление новости: {title}")
    public void deleteNews(String title) {
        newsPage.openControlPanel();
        newsPage.waitForControlPanel();


        for (int i = 0; i < 4; i++) {
            onView(withId(NewsPage.NEWS_LIST)).perform(swipeUp());
        }

        newsPage.checkNewsInList(title);
        newsPage.scrollToNewsAndDelete(title);
        onIdle();
        newsPage.checkNewsNotInList(title);
    }

    @Step("Создание новости без заголовка: описание {description}")
    public void createNewsWithoutTitle(String description, String date) {
        newsPage.openControlPanel();
        newsPage.clickEditButton();
        newsPage.clickAddNews();
        newsPage.selectCategory(TestData.NEWS_CATEGORY);
        newsPage.enterDescription(description)
                .enterDate(date)
                .enterTime(TestData.NEWS_TIME)
                .clickSave();
        newsPage.checkErrorMessageIsDisplayed(TestData.ERROR_EMPTY_NEWS_FIELDS);
    }


    @Step("Создание новости без описания: {title}")
    public void createNewsWithoutDescription(String title, String date) {
        newsPage.openControlPanel();
        newsPage.clickEditButton();
        newsPage.clickAddNews();
        newsPage.selectCategory(TestData.NEWS_CATEGORY);
        newsPage.enterTitle(title)
                .enterDate(date)
                .enterTime(TestData.NEWS_TIME)
                .clickSave();
        newsPage.checkErrorMessageIsDisplayed(TestData.ERROR_EMPTY_NEWS_FIELDS);
    }


    @Step("Создание новости с датой в будущем: {title}")
    public void createNewsWithFutureDate(String title, String description) {
        newsPage.openControlPanel();
        newsPage.clickEditButton();
        newsPage.clickAddNews();
        newsPage.selectCategory(TestData.NEWS_CATEGORY);
        newsPage.enterTitle(title)
                .enterDescription(description)
                .enterDate(TestData.NEWS_FUTURE_DATE)
                .enterTime(TestData.NEWS_TIME)
                .clickSave();
        newsPage.waitForControlPanel();
    }


    @Step("Редактирование описания новости: {oldTitle}")
    public void editNewsDescription(String oldTitle, String newDescription) {
        newsPage.openControlPanel();


        for (int i = 0; i < 4; i++) {
            onView(withId(NewsPage.NEWS_LIST)).perform(swipeUp());
        }

        newsPage.scrollToNewsAndEdit(oldTitle);
        newsPage.enterDescription(newDescription);
        newsPage.clickSave();
        onIdle();
    }
}