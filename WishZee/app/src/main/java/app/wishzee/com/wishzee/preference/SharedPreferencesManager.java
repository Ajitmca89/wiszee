package app.wishzee.com.wishzee.preference;

import android.content.Context;
import android.content.SharedPreferences;

import app.wishzee.com.wishzee.constant.Constants;

/**
 * Created by Ajit Gupta on 5/11/2016.
 */
public class SharedPreferencesManager {

    private SharedPreferencesManager() {
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(Constants.APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static String getUsername(Context context) {
        return getSharedPreferences(context).getString(Constants.USERNAME, null);
    }

    public static void setUsername(Context context, String username) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(Constants.USERNAME, username);
        editor.commit();
    }

    public static String getEmail(Context context) {
        return getSharedPreferences(context).getString(Constants.EMAIL, null);
    }

    public static void setEmail(Context context, String email) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(Constants.EMAIL, email);
        editor.commit();
    }

    public static String getGender(Context context) {
        return getSharedPreferences(context).getString(Constants.GENDER, null);
    }

    public static void setGender(Context context, String gender) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(Constants.GENDER, gender);
        editor.commit();
    }

    public static String getPhone(Context context) {
        return getSharedPreferences(context).getString(Constants.PHONE, null);
    }

    public static void setPhone(Context context, String phone) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(Constants.PHONE, phone);
        editor.commit();
    }

    public static String getSocialId(Context context) {
        return getSharedPreferences(context).getString(Constants.SOCIAL_ID, null);
    }

    public static void setSocialID(Context context, String socialId) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(Constants.SOCIAL_ID, socialId);
        editor.commit();
    }

    public static String getID(Context context) {
        return getSharedPreferences(context).getString(Constants.ID, null);
    }

    public static void setID(Context context, String id) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(Constants.ID, id);
        editor.commit();
    }

    public static String getUserID(Context context) {
        return getSharedPreferences(context).getString(Constants.USER_ID, null);
    }

    public static void setUserID(Context context, String userId) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(Constants.USER_ID, userId);
        editor.commit();
    }

}
