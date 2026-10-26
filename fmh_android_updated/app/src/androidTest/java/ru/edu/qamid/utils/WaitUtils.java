package ru.edu.qamid.utils;

import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.concurrent.TimeoutException;


public class WaitUtils {


    public static ViewAction waitDisplayed(final int viewId, final long timeout) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return Matchers.any(View.class);
            }

            @Override
            public String getDescription() {
                return "Ожидание отображения элемента с id: " + viewId + ", timeout: " + timeout;
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();
                final long endTime = System.currentTimeMillis() + timeout;
                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if (child.getId() == viewId && child.getVisibility() == View.VISIBLE) {
                            return;
                        }
                    }
                    uiController.loopMainThreadForAtLeast(50);
                } while (System.currentTimeMillis() < endTime);

                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }


    public static ViewAction waitForAnyOf(final int id1, final int id2, final long timeout) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return Matchers.any(View.class);
            }

            @Override
            public String getDescription() {
                return "Ожидание любого из элементов: " + id1 + " или " + id2 + ", timeout: " + timeout;
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();
                final long endTime = System.currentTimeMillis() + timeout;
                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if ((child.getId() == id1 || child.getId() == id2)
                                && child.getVisibility() == View.VISIBLE) {
                            return;
                        }
                    }
                    uiController.loopMainThreadForAtLeast(50);
                } while (System.currentTimeMillis() < endTime);

                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }

    public static ViewAction waitDisplayedText(final String text, final long timeout) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return Matchers.any(View.class);
            }

            @Override
            public String getDescription() {
                return "Ожидание текста: \"" + text + "\", timeout: " + timeout;
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();
                final long endTime = System.currentTimeMillis() + timeout;
                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if (child instanceof android.widget.TextView
                                && child.getVisibility() == View.VISIBLE
                                && text.contentEquals(((android.widget.TextView) child).getText())) {
                            return;
                        }
                    }
                    uiController.loopMainThreadForAtLeast(50);
                } while (System.currentTimeMillis() < endTime);

                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }
    public static ViewAction waitNotDisplayedText(final String text, final long timeout) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return Matchers.any(View.class);
            }

            @Override
            public String getDescription() {
                return "Ожидание исчезновения текста: \"" + text + "\", timeout: " + timeout;
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();
                final long endTime = System.currentTimeMillis() + timeout;
                do {
                    boolean found = false;
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if (child instanceof android.widget.TextView
                                && child.getVisibility() == View.VISIBLE
                                && text.contentEquals(((android.widget.TextView) child).getText())) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        return;
                    }
                    uiController.loopMainThreadForAtLeast(50);
                } while (System.currentTimeMillis() < endTime);

                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }
}