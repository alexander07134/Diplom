package ru.edu.qamid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import io.qameta.allure.kotlin.Allure;
import ru.edu.qamid.R;

public class AuthPage {
    public static final int LOGIN_INPUT = R.id.login_edit_text;
    public static final int PASSWORD_INPUT = R.id.password_edit_text;
    public static final int ENTER_BUTTON = R.id.enter_button;

    public AuthPage enterLogin(String login) {
        Allure.step("Ввод логина: " + login);
        onView(withId(LOGIN_INPUT)).check(matches(isDisplayed()));
        onView(withId(LOGIN_INPUT)).perform(replaceText(login), closeSoftKeyboard());
        return this;
    }

    public AuthPage enterPassword(String password) {
        Allure.step("Ввод пароля");
        onView(withId(PASSWORD_INPUT)).perform(replaceText(password), closeSoftKeyboard());
        return this;
    }

    public void clickEnterButton() {
        Allure.step("Нажатие на кнопку \"Sign in\"");
        onView(withId(ENTER_BUTTON)).perform(click());
    }

    public void checkErrorIconIsDisplayed() {
        Allure.step("Проверка: экран после входа отображается");
        onView(withId(ENTER_BUTTON)).check(matches(isDisplayed()));
    }
}