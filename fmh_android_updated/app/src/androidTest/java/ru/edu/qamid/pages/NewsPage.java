package ru.edu.qamid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.edu.qamid.R;

public class NewsPage {
    public static final int ALL_NEWS_BUTTON = R.id.all_news_text_view;
    public static final int NEWS_LIST = R.id.news_list_recycler_view;
    public static final int SORT_BUTTON = R.id.news_sort_button;
    public static final int FILTER_BUTTON = R.id.news_filter_button;
    public static final int EDIT_BUTTON = R.id.news_edit_button;
    public static final int ADD_NEWS_BUTTON = R.id.add_news_image_view;

    public static final int TITLE_INPUT = R.id.news_title_edit_text;
    public static final int CATEGORY_INPUT = R.id.news_category_auto_complete;
    public static final int DATE_INPUT = R.id.news_publish_date_edit_text;
    public static final int DESCRIPTION_INPUT = R.id.news_description_edit_text;
    public static final int SAVE_BUTTON = R.id.news_save_button;

    public void clickAllNews() {
        Allure.step("Нажатие на ALL NEWS");
        onView(withId(ALL_NEWS_BUTTON)).perform(click());
    }

    public void clickEditButton() {
        Allure.step("Нажатие на кнопку редактирования");
        onView(withId(EDIT_BUTTON)).perform(click());
    }

    public void clickAddNews() {
        Allure.step("Нажатие на кнопку добавления новости");
        onView(withId(ADD_NEWS_BUTTON)).perform(click());
    }

    public NewsPage enterTitle(String title) {
        Allure.step("Ввод заголовка: " + title);
        onView(withId(TITLE_INPUT)).perform(replaceText(title), closeSoftKeyboard());
        return this;
    }

    public NewsPage enterDescription(String description) {
        Allure.step("Ввод описания");
        onView(withId(DESCRIPTION_INPUT)).perform(replaceText(description), closeSoftKeyboard());
        return this;
    }

    public NewsPage enterDate(String date) {
        Allure.step("Ввод даты: " + date);
        onView(withId(DATE_INPUT)).perform(replaceText(date), closeSoftKeyboard());
        return this;
    }

    public void clickSave() {
        Allure.step("Нажатие на кнопку Сохранить");
        onView(withId(SAVE_BUTTON)).perform(click());
    }

    public void checkNewsInList(String title) {
        Allure.step("Проверка: новость \"" + title + "\" отображается");
        onView(withText(title)).check(matches(isDisplayed()));
    }

    public void checkNewsListIsDisplayed() {
        Allure.step("Проверка: список новостей отображается");
        onView(withId(NEWS_LIST)).check(matches(isDisplayed()));
    }
    public void checkCreatingScreenIsDisplayed() {
        Allure.step("Проверка: экран создания новости отображается (ошибка не дала сохранить)");
        onView(withText("Creating")).check(matches(isDisplayed()));
    }
}