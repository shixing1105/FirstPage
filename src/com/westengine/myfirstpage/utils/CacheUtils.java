package com.westengine.myfirstpage.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtils {
	public static final String CACHE_FILE_NAME = "firstpage-westengine";
	private static SharedPreferences mSharedPreferences;

	public static void putBoolean(Context context, String key, boolean value) {
		if(mSharedPreferences == null) {
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		mSharedPreferences.edit().putBoolean(key, value).commit();
	}
	
	/**
	 * ȡ��һ��boolean���͵�ֵ
	 * @param context
	 * @param key ��
	 * @param defValue ȱʡֵ
	 * @return
	 */
	public static boolean getBoolean(Context context, String key, boolean defValue) {
		if(mSharedPreferences == null) {
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		return mSharedPreferences.getBoolean(key, defValue);
	}

}
