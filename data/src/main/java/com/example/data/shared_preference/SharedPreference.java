package com.example.data.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class SharedPreference {

    private static SharedPreferences getSharedPref(Context context) {
        return context.getSharedPreferences("login", Context.MODE_PRIVATE);
    }

    public static void writeToSharedPref(Context context, String name, String email,String number) {
        getSharedPref(context).edit().putString("name", name).apply();
        getSharedPref(context).edit().putString("email", email).apply();
        getSharedPref(context).edit().putString("number", number).apply();
    }

    public static boolean isUserLogin(Context context) {
        return getSharedPref(context).contains("name");
    }

    public static void deleteUser(Context context) {
        getSharedPref(context).edit().clear().apply();
    }

    public static String getName(Context context) {
        return getSharedPref(context).getString("name","");
    }

    public static String getEmail(Context context) {
        return getSharedPref(context).getString("email","");
    }

    public static String getNumber(Context context) {
        return getSharedPref(context).getString("number","");
    }
}
