package com.example.chris.leafchat.ui.fragment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chris.leafchat.R
import com.example.chris.leafchat.di.components.LandingComponent
import com.example.chris.leafchat.viewmodel.LandingViewModel
import kotlinx.android.synthetic.main.fragment_landing.*
import javax.inject.Inject

/**
 * Created by Chris on 2/15/18.
 */
class LandingFragment: BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var landingVm: LandingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_landing, container, false)

        initInjector()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
    }

    private fun initInjector() {
        getComponent(LandingComponent::class.java).inject(this)

        landingVm= ViewModelProviders.of(this, viewModelFactory)
                .get(LandingViewModel::class.java)
    }

    private fun setupViews() {
        view_continueBtn.setOnClickListener { onContinueClicked() }

    }

    private fun onContinueClicked() {
        landingVm.testValue()
        if (view_passcodeEt.text.length < 4) {
            view_passcodeLayout.error = getString(R.string.error_invalid)

            return
        }

        if (view_usernameEt.text.length < 5) {
            view_usernameLayout.error = getString(R.string.error_invalid_name)

            return
        }

        view_passcodeLayout.error = null

        toggleProgressView(setVisible = true)

    }

    private fun toggleProgressView(setVisible: Boolean) =
            if (setVisible) view_progressBar.visibility = View.VISIBLE
            else view_progressBar.visibility = View.GONE
}