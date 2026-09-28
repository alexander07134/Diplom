package ru.edu.qamid.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import io.qameta.allure.kotlin.Step;
import ru.edu.qamid.R;
import ru.edu.qamid.pages.AuthPage;
import ru.edu.qamid.pages.MainPage;
import ru.edu.qamid.pages.NewsPage;

public class Steps {
    private AuthPage authPage = new AuthPage();
    private NewsPage newsPage = new NewsPage();
    private MainPage mainPage = new MainPage();

    @Step("Авторизация с данными: {login} / {password}")
    public void login(String login, String password) {
        authPage.enterLogin(login)
                .enterPassword(password)
                .clickEnterButton();
    }

    @Step("Авторизация, если приложение не авторизовано")
    public void loginIfNeeded(String login, String password) {
        try {
            // Проверяем, есть ли поле логина на экране
            onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()));
            // Поле логина есть — значит, приложение не авторизовано
            login(login, password);
        } catch (Exception e) {
            // Поля логина нет — приложение уже авторизовано, пропускаем логин
        }
    }

    @Step("Создание новости с заголовком: {title}")
    public void createNews(String title, String description, String date) {
        newsPage.clickAllNews();
        newsPage.clickEditButton();
        newsPage.clickAddNews();
        newsPage.enterTitle(title)
                .enterDescription(description)
                .enterDate(date)
                .clickSave();
    }
}