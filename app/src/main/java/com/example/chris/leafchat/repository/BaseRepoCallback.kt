package com.example.chris.leafchat.repository

import android.support.annotation.StringRes

/**
 * Created by Chris on 2/21/18.
 */
interface BaseRepoCallback {
    fun onErrorWithId(@StringRes error: Int)
}