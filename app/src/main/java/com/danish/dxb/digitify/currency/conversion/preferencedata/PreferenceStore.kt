package com.danish.dxb.digitify.currency.conversion.preferencedata

import android.content.Context
import android.content.SharedPreferences
import com.danish.dxb.digitify.currency.conversion.app.DigitifyApp

class PreferenceStore(context: Context?) {
    private var editor: SharedPreferences.Editor? = null
    private var preferences: SharedPreferences? = null

    fun saveStringData(key: String?, value: String?) {
        editor!!.putString(key, value)
        editor!!.apply()
    }

    fun saveBooleanData(key: String?, value: Boolean?) {
        editor!!.putBoolean(key, value!!)
        editor!!.apply()
    }

    fun saveIntegerData(key: String?, value: Int) {
        editor!!.putInt(key, value)
        editor!!.apply()
    }

    fun getStringData(key: String?): String? {
        return if (preferences != null) {
            preferences?.getString(key, "")
        } else {
            null
        }
    }

    fun getBooleanData(key: String?): Boolean? {
        return if (preferences != null) {
            preferences?.getBoolean(key, false)
        } else {
            false
        }
    }

    fun getIntegerData(key: String?): Int? {
        return if (preferences != null) {
            preferences?.getInt(key, 0)
        } else {
            0
        }
    }

    fun clearPreferencesData() {
        preferences!!.edit().clear().apply()
    }

    companion object {
        @get:Synchronized
        var instance: PreferenceStore? = null
            get() {
                field =
                    if (field == null) PreferenceStore(DigitifyApp.context) else field
                return field
            }
            private set

        @Synchronized
        fun getInstance(context: Context?): PreferenceStore {
            return PreferenceStore(context)
        }
    }

    init {
        if (context != null) {
            preferences = context.getSharedPreferences(
                "DIGITIFY_EXCHANGE_RATE", Context.MODE_PRIVATE
            )
            editor = preferences?.edit()
        }
    }
}