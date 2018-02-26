package com.example.chris.leafchat.ui.activity

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.chris.leafchat.R
import com.example.chris.leafchat.di.HasComponent
import com.example.chris.leafchat.di.components.DaggerLoginComponent
import com.example.chris.leafchat.di.components.LoginComponent
import com.example.chris.leafchat.ui.fragment.LoginFragment
import com.example.chris.leafchat.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.include_toolbar.*

class LoginActivity : BaseActivity(), HasComponent<LoginComponent> {

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

        setupViewModel()
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

    //region viewmodel
    private fun setupViewModel() {
        val loginVm = getViewModel(LoginViewModel::class.java)
        loginVm.loginObserver.observe(this, Observer<Boolean> {
            t -> if (t == true) {
            navigator.navigateToChatRoom(
                    loginVm.userNameObserver.value!!,
                    loginVm.passCodeObserver.value!!) }
            finish()
        })
    }
    //endregion

    companion object {
        fun newInstance(context: Context) : Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
