package com.example.chris.leafchat.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.chris.leafchat.R
import com.example.chris.leafchat.di.HasComponent
import com.example.chris.leafchat.di.components.DaggerLoginComponent
import com.example.chris.leafchat.di.components.LoginComponent
import com.example.chris.leafchat.ui.fragment.LoginFragment
import kotlinx.android.synthetic.main.include_toolbar.*

class LoginActivity : BaseActivity(), HasComponent<LoginComponent>, LoginFragment.LoginActivityListener {

    //region init
    private val loginComponent: LoginComponent by lazy {
        DaggerLoginComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic)

        initToolbar()

        performFragmentTransaction(LoginFragment())
    }

    override fun initToolbar() {
        super.initToolbar()
        
        supportActionBar?.setDisplayShowTitleEnabled(false)

        view_toolbar.showTitle(R.string.landing_title)
    }

    override fun getComponent(): LoginComponent {
        return loginComponent
    }
    //endregion

    //regionfragment call
    override fun onLoginSuccessful(userName: String, passcode: String) {
        navigator.navigateToChatRoom(userName, passcode)
        finish()
    }
    //endregion

    companion object {
        fun newInstance(context: Context) : Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
