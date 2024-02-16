/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 13
 */

package com.mich.gwan.shallom.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LocaleHelper {
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    // the method is used to set the language at runtime
    public static Context setLocale(Context context, String language) {
        persist(context, language);

        // updating the language for devices above android nougat
        return updateResources(context, language);
        // for devices having lower version of android os
    }

    private static void persist(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    // the method is used update the language of application by creating
    // object of inbuilt Locale class and passing language argument to it
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        return context.createConfigurationContext(configuration);
    }


    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.locale = locale;
        configuration.setLayoutDirection(locale); // for RTL changes

        context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());

        return context;
    }


    /**
     * context = LocaleHelper.setLocale(MainActivity.this, "hi");
     *                 resources = context.getResources();
     */
}
