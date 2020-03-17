package com.coderZsq.base.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

// 强大的工具类, 专门用来保存数据
public class SpUtils {
    public static SharedPreferences getSp(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constantin.CONFIG, context.MODE_PRIVATE);
        return sp;
    }

    public static String getString(Context context, String key) {
        SharedPreferences sp = SpUtils.getSp(context);
        return sp.getString(key, "");
    }

    public static int getInt(Context context, String key) {
        SharedPreferences sp = SpUtils.getSp(context);
        return sp.getInt(key, 0);
    }

    public static Boolean getBoolean(Context context, String key) {
        SharedPreferences sp = SpUtils.getSp(context);
        return sp.getBoolean(key, false);
    }

    public static void SafeString(Context context, String key, String value) {
        SharedPreferences sp = SpUtils.getSp(context);
        // 必须先获得编辑器
        Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static void SafeInt(Context context, String key, int value) {
        SharedPreferences sp = SpUtils.getSp(context);
        // 必须先获得编辑器
        Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static void SafeBoolean(Context context, String key, Boolean value) {
        SharedPreferences sp = SpUtils.getSp(context);
        // 必须先获得编辑器
        Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }
}
