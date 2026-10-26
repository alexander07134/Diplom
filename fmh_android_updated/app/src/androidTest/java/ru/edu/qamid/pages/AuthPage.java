package ru.edu.qamid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.text.InputType;
import android.widget.EditText;

import io.qameta.allure.kotlin.Allure;
import ru.edu.qamid.R;
import ru.edu.qamid.utils.WaitUtils;

public class AuthPage {


    public static final int LOGIN_INPUT = R.id.login_edit_text;
    public static final int PASSWORD_INPUT = R.id.password_edit_text;
    public static final int ENTER_BUTTON = R.id.enter_button;
    public static final int MAIN_SCREEN = R.id.main_news_list_container;


    public static final int AUTHORIZATION_BUTTON = R.id.authorization_image_button;


    public AuthPage waitForAnyScreen() {
        Allure.step("Ожидание загрузки приложения");
        onView(isRoot()).perform(WaitUtils.waitForAnyOf(
                LOGIN_INPUT, AUTHORIZATION_BUTTON, 30000));
        return this;
    }

    public AuthPage waitForLoad() {
        Allure.step("Ожидание экрана авторизации");
        onView(isRoot()).perform(WaitUtils.waitDisplayed(LOGIN_INPUT, 30000));
        return this;
    }


    public boolean isAuthorized() {
        try {
            onView(withId(AUTHORIZATION_BUTTON)).check(matches(isDisplayed()));
            return true;
        } catch (AssertionError e) {

            return false;
        } catch (Exception e) {

            return false;
        }
    }


    public AuthPage logoutIfNeeded() {
        Allure.step("Выход из аккаунта (если авторизован)");
        if (!isAuthorized()) {
            return this;
        }

        onView(withId(AUTHORIZATION_BUTTON)).perform(click());

        onView(withText("Log out")).check(matches(isDisplayed()));

        onView(withText("Log out")).perform(click());

        onView(isRoot()).perform(WaitUtils.waitDisplayed(LOGIN_INPUT, 30000));
        return this;
    }


    public AuthPage enterLogin(String login) {
        Allure.step("Ввод логина: " + login);
        onView(withId(LOGIN_INPUT)).perform(replaceText(login));
        return this;
    }


    public AuthPage enterPassword(String password) {
        Allure.step("Ввод пароля");
        onView(withId(PASSWORD_INPUT)).perform(replaceText(password));
        return this;
    }


    public void clickEnterButton() {
        Allure.step("Нажатие на кнопку \"Sign in\"");
        onView(withId(ENTER_BUTTON)).perform(click());
    }


    public void checkMainScreenIsDisplayed() {
        Allure.step("Проверка: главный экран отображается");
        onView(isRoot()).perform(WaitUtils.waitDisplayed(MAIN_SCREEN, 15000));
        onView(withId(MAIN_SCREEN)).check(matches(isDisplayed()));
    }


    public void checkErrorMessageIsDisplayed(String expectedText) {
        Allure.step("Проверка: сообщение об ошибке \"" + expectedText + "\"");
        onView(withText(expectedText)).check(matches(isDisplayed()));
    }


    public AuthPage enterRawLogin(String login) {
        Allure.step("Ввод логина (raw): \"" + login + "\"");
        onView(withId(LOGIN_INPUT)).perform(replaceText(login));
        return this;
    }


    public AuthPage enterRawPassword(String password) {
        Allure.step("Ввод пароля (raw)");
        onView(withId(PASSWORD_INPUT)).perform(replaceText(password));
        return this;
    }


    public AuthPage enterLoginOnly(String login) {
        Allure.step("Ввод только логина: " + login);
        onView(withId(LOGIN_INPUT)).perform(replaceText(login));
        return this;
    }


    public AuthPage enterPasswordOnly(String password) {
        Allure.step("Ввод только пароля");
        onView(withId(PASSWORD_INPUT)).perform(replaceText(password));
        return this;
    }


    public void checkPasswordIsMasked() {
        Allure.step("Проверка: пароль маскируется");
        onView(withId(PASSWORD_INPUT)).check((view, noViewFoundException) -> {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }
            EditText editText = (EditText) view;
            int variation = editText.getInputType() & InputType.TYPE_MASK_VARIATION;
            boolean passwordVariation =
                    variation == InputType.TYPE_TEXT_VARIATION_PASSWORD
                            || variation == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                            && editText.getTransformationMethod() != null;
            boolean hasTransformation = editText.getTransformationMethod() != null;
            if (!hasTransformation && !passwordVariation) {
                throw new AssertionError("Поле пароля не маскирует ввод");
            }
        });
    }


    public void checkLoginFieldText(String expected) {
        Allure.step("Проверка: в поле логина \"" + expected + "\"");
        onView(withId(LOGIN_INPUT)).check(matches(withText(expected)));
    }
}