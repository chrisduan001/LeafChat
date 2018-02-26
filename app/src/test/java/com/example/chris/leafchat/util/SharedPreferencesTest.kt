package com.example.chris.leafchat.util

import com.example.chris.leafchat.BaseTest
import org.junit.Test
import org.junit.Assert.*
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.junit.Before
import javax.inject.Inject

/**
 * Created by Chris on 2/23/18.
 */
class SharedPreferencesTest : BaseTest() {

    @Inject lateinit var sharedPreference: LeafSharedPreference

    @Before
    fun before() {
        component.inject(this)
    }

    @Test
    fun testSetValue() {
        sharedPreference.setUserName("test user")
        val userName = sharedPreference.getUserName()
        assertThat(userName, `is`("test user"))

        sharedPreference.setUserName("changed")
        assertThat(sharedPreference.getUserName(), `is`("changed"))

        sharedPreference.setPasscode("123")
        val passcode = sharedPreference.getPasscode()
        assertThat(passcode, `is`("123"))

        sharedPreference.setPasscode("passcode changed")
        assertThat(sharedPreference.getPasscode(), `is`("passcode changed"))
    }

    @Test
    fun testCleanSp() {
        sharedPreference.setUserName("changed")
        assertThat(sharedPreference.getUserName(), `is`("changed"))

        sharedPreference.clearSharedPreference()
        assertThat(sharedPreference.getUserName(), `is`(""))
    }
}