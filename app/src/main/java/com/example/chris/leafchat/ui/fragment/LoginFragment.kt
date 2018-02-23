package com.example.chris.leafchat.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chris.leafchat.R
import com.example.chris.leafchat.di.components.LoginComponent
import com.example.chris.leafchat.viewmodel.LandingViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

/**
 * Created by Chris on 2/15/18.
 */
class LoginFragment : BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var landingVm: LandingViewModel

    //region init
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        initInjector()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
    }

    private fun initInjector() {
        getComponent(LoginComponent::class.java).inject(this)

        landingVm = ViewModelProviders.of(this, viewModelFactory)
                .get(LandingViewModel::class.java)
    }

    private fun setupViews() {
        view_continueBtn.setOnClickListener {
            landingVm.loginUser(view_passcodeEt.text.toString(), view_usernameEt.text.toString())
        }

        landingVm.passCodeObserver.observe(this, passcodeOb)
        landingVm.userNameObserver.observe(this, userNameOb)
        landingVm.progressObserver.observe(this, progressBarOb)
        landingVm.toastMsgObserver.observe(this, toastOb)
    }
    //endregion

    //region observer
    private val passcodeOb = Observer<String> {
        view_passcodeLayout.error = if (it.isNullOrEmpty()) null else it
    }

    private val userNameOb = Observer<String> {
        view_usernameLayout.error = if (it.isNullOrEmpty()) null else it
    }

    private val progressBarOb = Observer<Boolean> { toggleProgressView(setVisible = it) }

    private val toastOb = Observer<String> {
        if (!it.isNullOrEmpty()) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
    //endregion

    private fun toggleProgressView(setVisible: Boolean?) =
            if (setVisible == true) view_progressBar.visibility = View.VISIBLE
            else view_progressBar.visibility = View.GONE
}