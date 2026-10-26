package ru.edu.qamid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.edu.qamid.R;

public class MainPage {
    public static final int MAIN_MENU_BUTTON = R.id.main_menu_image_button;


    public void openMainMenu() {
        Allure.step("Открытие главного меню");
        onView(withId(MAIN_MENU_BUTTON)).perform(click());
    }


    public void clickMainMenu() {
        Allure.step("Переход в раздел Main через главное меню");
        openMainMenu();

        onView(withText("Main"))
                .inRoot(isPlatformPopup())
                .check(matches(isDisplayed()));
        onView(withText("Main"))
                .inRoot(isPlatformPopup())
                .perform(click());
    }


    public void clickNewsMenuItem() {
        Allure.step("Переход в раздел News через главное меню");
        openMainMenu();
        onView(withText("News"))
                .inRoot(isPlatformPopup())
                .check(matches(isDisplayed()));
        onView(withText("News"))
                .inRoot(isPlatformPopup())
                .perform(click());
    }

    public void checkMainMenuButtonIsDisplayed() {
        Allure.step("Проверка: кнопка Main menu отображается");
        onView(withId(MAIN_MENU_BUTTON)).check(matches(isDisplayed()));
    }
}