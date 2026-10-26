package ru.edu.qamid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.edu.qamid.R;
import ru.edu.qamid.utils.WaitUtils;

public class QuotesPage {


    public static final int QUOTES_TITLE = R.id.our_mission_title_text_view;


    public static final int OUR_MISSION_BUTTON = R.id.our_mission_image_button;

    private static final long UI_TIMEOUT = 15000;

    public void openQuotes() {
        Allure.step("Открытие раздела Цитаты через кнопку Our Mission");
        onView(withId(OUR_MISSION_BUTTON)).perform(click());
    }

    public void checkQuotesListIsDisplayed() {
        Allure.step("Проверка: раздел цитат отображается");
        onView(withId(QUOTES_TITLE)).check(matches(isDisplayed()));
        onView(withText("Love is all")).check(matches(isDisplayed()));
    }


    public void checkAtLeastOneQuoteIsDisplayed() {
        Allure.step("Проверка: хотя бы одна цитата отображается");
        onView(isRoot()).perform(WaitUtils.waitDisplayed(QUOTES_TITLE, UI_TIMEOUT));
        onView(withId(QUOTES_TITLE)).check(matches(isDisplayed()));

    }
}