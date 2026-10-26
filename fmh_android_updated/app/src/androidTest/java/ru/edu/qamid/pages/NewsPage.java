package ru.edu.qamid.pages;

import static androidx.test.espresso.Espresso.onIdle;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.edu.qamid.R;
import ru.edu.qamid.utils.WaitUtils;

public class NewsPage {
    public static final int ALL_NEWS_BUTTON = R.id.all_news_text_view;
    public static final int NEWS_LIST = R.id.news_list_recycler_view;
    public static final int SORT_BUTTON = R.id.news_sort_button;
    public static final int FILTER_BUTTON = R.id.news_filter_button;
    public static final int ADD_NEWS_BUTTON = R.id.add_news_image_view;
    public static final int EDIT_BUTTON = R.id.news_edit_button;
    public static final int CATEGORY_INPUT = R.id.news_category_auto_complete;
    public static final int TITLE_INPUT = R.id.news_title_edit_text;
    public static final int DATE_INPUT = R.id.news_publish_date_edit_text;
    public static final int TIME_INPUT = R.id.news_publish_time_edit_text;
    public static final int DESCRIPTION_INPUT = R.id.news_description_edit_text;
    public static final int SAVE_BUTTON = R.id.news_save_button;
    public static final int NEWS_DELETE_BUTTON = R.id.news_item_delete_image_view;
    public static final int NEWS_EDIT_BUTTON = R.id.news_item_edit_image_view;

    private static final long UI_TIMEOUT = 15000;

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return Matchers.any(View.class);
            }

            @Override
            public String getDescription() {
                return "Клик по дочернему элементу с id: " + id;
            }

            @Override
            public void perform(UiController uiController, View view) {
                View childView = view.findViewById(id);
                if (childView != null) {
                    childView.performClick();
                }
            }
        };
    }

    public void openControlPanel() {
        Allure.step("Открытие панели управления новостями");
        try {
            onView(withId(ALL_NEWS_BUTTON)).check(matches(withEffectiveVisibility(VISIBLE)));
            onView(withId(ALL_NEWS_BUTTON)).perform(click());
        } catch (Exception e) {

        }
        waitForControlPanel();
    }

    public void waitForControlPanel() {
        Allure.step("Ожидание загрузки панели управления");
        onView(isRoot()).perform(WaitUtils.waitDisplayed(NEWS_LIST, UI_TIMEOUT));
    }

    public void clickAllNews() {
        openControlPanel();
    }

    public void clickEditButton() {
        Allure.step("Нажатие на кнопку редактирования (карандаш)");
        onView(withId(EDIT_BUTTON)).perform(click());
    }

    public void clickAddNews() {
        Allure.step("Нажатие на кнопку добавления новости (плюс)");
        onView(withId(ADD_NEWS_BUTTON)).perform(click());
    }

    public NewsPage selectCategory(String category) {
        Allure.step("Выбор категории: " + category);
        onView(withId(CATEGORY_INPUT)).perform(click());
        onView(withText(category))
                .inRoot(isPlatformPopup())
                .perform(click());
        return this;
    }

    public NewsPage enterTitle(String title) {
        Allure.step("Ввод заголовка: " + title);
        onView(withId(TITLE_INPUT)).perform(replaceText(title));
        return this;
    }

    public NewsPage enterDescription(String description) {
        Allure.step("Ввод описания");
        onView(withId(DESCRIPTION_INPUT)).perform(replaceText(description));
        return this;
    }

    public NewsPage enterDate(String date) {
        Allure.step("Ввод даты: " + date);
        onView(withId(DATE_INPUT)).perform(replaceText(date));
        return this;
    }

    public NewsPage enterTime(String time) {
        Allure.step("Ввод времени: " + time);
        onView(withId(TIME_INPUT)).perform(click());
        onView(isRoot()).perform(WaitUtils.waitDisplayed(android.R.id.button1, UI_TIMEOUT));
        onView(withId(android.R.id.button1)).perform(click());
        return this;
    }

    public void clickSave() {
        Allure.step("Нажатие на кнопку Сохранить");
        onView(withId(SAVE_BUTTON)).perform(click());
    }


    public void checkNewsInList(String title) {
        Allure.step("Проверка: новость \"" + title + "\" отображается в списке");
        onView(isRoot()).perform(WaitUtils.waitDisplayed(NEWS_LIST, UI_TIMEOUT));
        onView(withId(NEWS_LIST)).perform(scrollTo(hasDescendant(withText(title))));
        onView(allOf(withId(R.id.news_item_material_card_view), hasDescendant(withText(title))))
                .check(matches(isDisplayed()));
    }

    public void checkNewsNotInList(String title) {
        Allure.step("Проверка: новость \"" + title + "\" отсутствует в списке");
        onView(isRoot()).perform(WaitUtils.waitNotDisplayedText(title, UI_TIMEOUT));
        onView(withText(title)).check(doesNotExist());
    }

    public void checkDescriptionInList(String title, String description) {
        Allure.step("Проверка: описание новости \"" + title + "\" = \"" + description + "\"");
        onView(isRoot()).perform(WaitUtils.waitDisplayed(NEWS_LIST, UI_TIMEOUT));
        onView(withId(NEWS_LIST)).perform(scrollTo(hasDescendant(withText(title))));
        onView(allOf(withId(R.id.news_item_material_card_view), hasDescendant(withText(title))))
                .check(matches(hasDescendant(withText(description))));
    }

    public void waitForNewsList() {
        Allure.step("Ожидание загрузки списка новостей");
        onView(isRoot()).perform(WaitUtils.waitDisplayed(NEWS_LIST, UI_TIMEOUT));
    }

    public void checkNewsListIsDisplayed() {
        Allure.step("Проверка: список новостей отображается");
        onView(isRoot()).perform(WaitUtils.waitDisplayed(NEWS_LIST, UI_TIMEOUT));
        onView(withId(NEWS_LIST)).check(matches(withEffectiveVisibility(VISIBLE)));
    }

    public void checkErrorMessageIsDisplayed(String expectedText) {
        Allure.step("Проверка: сообщение об ошибке \"" + expectedText + "\"");
        onView(isRoot()).perform(WaitUtils.waitDisplayedText(expectedText, UI_TIMEOUT));
        onView(withText(expectedText)).check(matches(isDisplayed()));
    }

    public void scrollToNewsAndEdit(String title) {
        Allure.step("Прокрутка к новости и нажатие редактирования: " + title);
        onView(isRoot()).perform(WaitUtils.waitDisplayed(NEWS_LIST, UI_TIMEOUT));
        onView(withId(NEWS_LIST)).perform(actionOnItem(
                hasDescendant(withText(title)),
                clickChildViewWithId(NEWS_EDIT_BUTTON)
        ));
    }

    public void scrollToNewsAndDelete(String title) {
        Allure.step("Прокрутка к новости и нажатие удаления: " + title);
        onView(isRoot()).perform(WaitUtils.waitDisplayed(NEWS_LIST, UI_TIMEOUT));
        onView(withId(NEWS_LIST)).perform(actionOnItem(
                hasDescendant(withText(title)),
                clickChildViewWithId(NEWS_DELETE_BUTTON)
        ));
        confirmDelete();
    }

    public void confirmDelete() {
        Allure.step("Подтверждение удаления");
        onView(isRoot()).perform(WaitUtils.waitDisplayedText("OK", 5000));
        onView(withText("OK")).perform(click());
    }

    public void sortNewsByDate() {
        Allure.step("Сортировка новостей по дате");
        onView(withId(SORT_BUTTON)).perform(click());
        onIdle();
    }
}