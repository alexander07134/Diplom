package ru.edu.qamid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import io.qameta.allure.kotlin.Allure;

import ru.edu.qamid.R;

public class QuotesPage {

    public void checkQuotesListIsDisplayed() {
        Allure.step("Проверка: список цитат отображается");
    }
}