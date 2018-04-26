package com.example.dharani.navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by durga on 22/4/18.
 */

public class SaveSharedPreference {


    static final String PREF_STATUS = "status";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }


    public static void setPrefStatus(Context ctx, String status)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_STATUS,status);
        editor.apply();
    }

    public static String getPrefStatus(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_STATUS, "");
    }
}
