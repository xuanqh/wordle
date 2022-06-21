package com.restaff.wordle.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefUtils private constructor() {
    companion object {
        private const val PREF_APP = "WordleApp"

        fun getBooleanData(context: Context, key: String?, defValue: Boolean = false): Boolean {
            return context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE)
                .getBoolean(key, defValue)
        }

        fun getIntData(context: Context, key: String?): Int {
            return context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE).getInt(key, 0)
        }

        fun getLongData(context: Context, key: String?): Long {
            return context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE).getLong(key, 0)
        }


        fun getStringData(context: Context, key: String?): String? {
            return context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE).getString(key, null)
        }


        fun saveData(context: Context, key: String?, value: String?) {
            context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE).edit()
                .putString(key, value).apply()
        }

        fun saveData(context: Context, key: String?, value: Int) {
            context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE).edit().putInt(key, value)
                .apply()
        }

        fun saveData(context: Context, key: String?, value: Long) {
            context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE).edit().putLong(key, value)
                .apply()
        }

        fun saveData(context: Context, key: String?, value: Boolean) {
            context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(key, value)
                .apply()
        }

        fun getSharedPrefEditor(context: Context, pref: String?): SharedPreferences.Editor {
            return context.getSharedPreferences(pref, Context.MODE_PRIVATE).edit()
        }

        fun saveData(editor: SharedPreferences.Editor) {
            editor.apply()
        }
    }
}