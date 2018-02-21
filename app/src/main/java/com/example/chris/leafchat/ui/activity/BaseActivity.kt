package com.example.chris.leafchat.ui.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.os.Bundle
import android.support.annotation.IntDef
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.chris.leafchat.LeafApplication
import com.example.chris.leafchat.R
import com.example.chris.leafchat.di.components.ApplicationComponent
import kotlinx.android.synthetic.main.activity_generic.view.*
import javax.inject.Inject

@SuppressLint("Registered")
/**
 * Created by Chris on 2/15/18.
 */
abstract class BaseActivity : AppCompatActivity() {

    @Inject protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getApplicationComponent().inject(this)

        performFragmentTransaction(Fragment())
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

    protected fun getApplicationComponent() : ApplicationComponent {
        return (application as LeafApplication).applicationComponent
    }
}