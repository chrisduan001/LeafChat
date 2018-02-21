package com.example.chris.leafchat.ui.activity

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import com.example.chris.leafchat.R
import com.example.chris.leafchat.di.HasComponent
import com.example.chris.leafchat.di.components.DaggerLandingComponent
import com.example.chris.leafchat.di.components.LandingComponent
import com.example.chris.leafchat.di.modules.LandingActivityModule
import com.example.chris.leafchat.ui.fragment.LandingFragment
import com.example.chris.leafchat.viewmodel.LandingViewModel
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

class LandingActivity : BaseActivity(), HasComponent<LandingComponent> {




    private val landingComponent: LandingComponent by lazy {
        DaggerLandingComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic)

        initToolbar()

        performFragmentTransaction(LandingFragment())
    }

    private fun initToolbar() {
        setSupportActionBar(view_toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        view_toolbar.showTitle(R.string.landing_title)
    }

    override fun getComponent(): LandingComponent {
        return landingComponent
    }
}
