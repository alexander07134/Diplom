package ru.edu.qamid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import io.qameta.allure.kotlin.Allure;
import ru.edu.qamid.R;

public class MainPage {
    public static final int MAIN_MENU_BUTTON = R.id.main_menu_image_button;
    public static final int AUTHORIZATION_BUTTON = R.id.authorization_image_button;

    public void clickMainMenu() {
        Allure.step("Нажатие на кнопку Main menu");
        onView(withId(MAIN_MENU_BUTTON)).perform(click());
    }

    public void checkMainMenuButtonIsDisplayed() {
        Allure.step("Проверка: кнопка Main menu отображается");
        onView(withId(MAIN_MENU_BUTTON)).check(matches(isDisplayed()));
    }
}