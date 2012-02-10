package com.chute.android.photopickerplus.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.chute.sdk.api.authentication.GCAuthenticationFactory.AccountType;

public class PreferenceUtil {
    private static final String TAG = PreferenceUtil.class.getSimpleName();
    private final Context context;

    private PreferenceUtil(Context context) {
        this.context = context;
    }

    static PreferenceUtil instance;

    public static PreferenceUtil get() {
        return instance;
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new PreferenceUtil(context.getApplicationContext());
        }
    }

    public SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    private final <T> void setPreference(final String key, final T value) {
        SharedPreferences.Editor edit = getPreferences().edit();
        if (value.getClass().equals(String.class)) {
            edit.putString(key, (String) value);
        } else if (value.getClass().equals(Boolean.class)) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value.getClass().equals(Integer.class)) {
            edit.putInt(key, (Integer) value);
        } else if (value.getClass().equals(Long.class)) {
            edit.putLong(key, (Long) value);
        } else if (value.getClass().equals(Float.class)) {
            edit.putFloat(key, (Float) value);
        } else {
            throw new UnsupportedOperationException("Need to add a primitive type to shared prefs");
        }
        edit.commit();
    }

    //Account ID
    public void setIdForAccount(AccountType accountType, String accountId) {
        setPreference(accountType.getName(), accountId);
    }

    public boolean hasAccountId(AccountType accountType) {
        return getPreferences().contains(accountType.getName());
    }

    public String getAccountId(AccountType accountType) {
        return getPreferences().getString(accountType.getName(), null);
    }
    
    //Account Name
    public void setNameForAccount(AccountType accountType, String accountName) {
        setPreference(accountType.getName()+"_name", accountName);
    }
    
    public boolean hasAccountName(AccountType accountType) {
    	return getPreferences().contains(accountType.getName()+"_name");
    }
    
    public String getAccountName(AccountType accountType) {
    	return getPreferences().getString(accountType.getName()+"_name", null);
    }
    
}
