package com.example.chris.leafchat.ui.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.IntDef
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.chris.leafchat.LeafApplication
import com.example.chris.leafchat.R
import com.example.chris.leafchat.di.components.ApplicationComponent
import com.example.chris.leafchat.util.LeafSharedPreference
import com.example.chris.leafchat.util.Navigator
import com.example.chris.leafchat.viewmodel.BaseViewModel
import com.example.chris.leafchat.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_generic.view.*
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

@SuppressLint("Registered")
/**
 * Created by Chris on 2/15/18.
 */
abstract class BaseActivity : AppCompatActivity() {

    @Inject protected lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject protected lateinit var navigator: Navigator
    @Inject protected lateinit var sharedPreference: LeafSharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getApplicationComponent().inject(this)
    }

    protected fun performFragmentTransaction(
            fragment: Fragment,
            tag: String = fragment.javaClass.name,
            addToBackStack: Boolean = false) {

        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.view_container, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    @CallSuper
    protected open fun initToolbar() {
        setSupportActionBar(view_toolbar)
    }

    protected fun getApplicationComponent() : ApplicationComponent {
        return (application as LeafApplication).applicationComponent
    }
}