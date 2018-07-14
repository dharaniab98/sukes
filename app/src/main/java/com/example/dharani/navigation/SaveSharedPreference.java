package com.example.dharani.navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by durga on 22/4/18.
 */

public class SaveSharedPreference {


    static final String PREF_STATUS = "status";
    static final String PREF_USERID="userid";
    static final String PREF_REFCODE="refCode";
    static final String PREF_PAYSTATUS="paystatus";
    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }


    public static void setPrefStatus(Context ctx, String status,String userid,String refCode,String paystatus)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_STATUS,status);
        editor.putString(PREF_USERID,userid);
        editor.putString(PREF_REFCODE,refCode);
        editor.putString(PREF_PAYSTATUS,paystatus);
        editor.apply();
    }

    public static String getPrefStatus(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_STATUS, "");
    }
    public static String getPrefUserid(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USERID, "");
    }

    public static String getPrefRefcode(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_REFCODE, "");
    }

    public static String getPrefPaystatus(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_PAYSTATUS, "");
    }
}
