package com.example.chris.leafchat.ui.activity

import android.os.Bundle
import com.example.chris.leafchat.R
import com.example.chris.leafchat.di.HasComponent
import com.example.chris.leafchat.di.components.DaggerLandingComponent
import com.example.chris.leafchat.di.components.LandingComponent
import com.example.chris.leafchat.ui.fragment.BaseFragment
import com.example.chris.leafchat.ui.fragment.LandingFragment
import kotlinx.android.synthetic.main.include_toolbar.*

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
