package com.example.chris.leafchat.util

import android.content.SharedPreferences

/**
 * Created by Chris on 2/23/18.
 */
class LeafSharedPreference constructor(private val sharedPreferences: SharedPreferences) {
    private val sp_user_name = "sp_user_name"
    private val sp_passcode = "sp_passcode"

    fun setUserName(userName: String) {
        val editor = sharedPreferences.edit()
        editor.putString(sp_user_name, userName)

        editor.apply()
    }

    fun getUserName() : String {
        return sharedPreferences.getString(sp_user_name, "")
    }

    fun setPasscode(passcode: String) {
        val editor = sharedPreferences.edit()
        editor.putString(sp_passcode, passcode)

        editor.apply()
    }

    fun getPasscode() : String {
        return sharedPreferences.getString(sp_passcode, "")
    }
}