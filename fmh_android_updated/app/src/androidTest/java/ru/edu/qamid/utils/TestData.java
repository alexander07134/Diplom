package ru.edu.qamid.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestData {
    public static final String VALID_LOGIN = "login2";
    public static final String VALID_PASSWORD = "password2";
    public static final String INVALID_LOGIN = "invalid";
    public static final String INVALID_PASSWORD = "wrong_password";

    public static final String ERROR_EMPTY_FIELDS = "Login and password cannot be empty";
    public static final String ERROR_INVALID_CREDENTIALS = "Something went wrong. Try again later.";
    public static final String ERROR_EMPTY_NEWS_FIELDS = "Fill empty fields";

    public static final String NEWS_CATEGORY = "Объявление";
    public static final String NEWS_TITLE = "Test news ";
    public static final String NEWS_DESCRIPTION = "Test description";
    public static final String NEWS_DATE = "27.09.2026";
    public static final String NEWS_TIME = "12:00";

    public static final String LOGIN_WITH_LEADING_SPACE  = " login2";
    public static final String LOGIN_WITH_TRAILING_SPACE = "login2 ";
    public static final String LOGIN_UPPERCASE           = "LOGIN2";
    public static final String LOGIN_MIXED_CASE          = "LoGiN2";
    public static final String LOGIN_SPECIAL_CHARS       = "!@#$%^&*()";

    public static final String NEWS_DESCRIPTION_EDITED = "Edited description";

    public static final String NEWS_FUTURE_DATE = "01.01.2030";

    private TestData() {
    }

    public static String getTodayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }
}