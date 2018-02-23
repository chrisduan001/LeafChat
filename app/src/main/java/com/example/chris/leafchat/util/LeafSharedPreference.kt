package com.example.chris.leafchat.util

import android.content.SharedPreferences

/**
 * Created by Chris on 2/23/18.
 */
class LeafSharedPreference constructor(private val sharedPreferences: SharedPreferences) {
    private val sp_user_name = "sp_user_name"
    private val sp_passcode = "sp_passcode"

    fun setUserName(userName: String) {
        setSpValue { editor -> editor.putString(sp_user_name, userName) }
    }

    fun getUserName() : String {
        return sharedPreferences.getString(sp_user_name, "")
    }

    fun setPasscode(passcode: String) {
        setSpValue { editor ->  editor.putString(sp_passcode, passcode) }
    }

    fun getPasscode() : String {
        return sharedPreferences.getString(sp_passcode, "")
    }

    private inline fun setSpValue(setValue: (SharedPreferences.Editor) -> Unit) {
        val editor = sharedPreferences.edit()
        setValue(editor)

        editor.apply()
    }

    fun clearSharedPreference() {
        val editor = sharedPreferences.edit()
        editor.clear()

        editor.apply()
    }
}