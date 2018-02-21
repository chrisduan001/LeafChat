package com.example.chris.leafchat.util

import android.content.Context
import android.support.annotation.StringRes
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import io.reactivex.annotations.Nullable
import kotlinx.android.synthetic.main.include_toolbar.view.*

/**
 * Created by Chris on 2/15/18.
 */
class LeafToolbar : Toolbar {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, @Nullable attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun showTitle(@StringRes stringRes: Int) {
        view_toolbarTitle.visibility = View.VISIBLE
        view_toolbarTitle.text = context.getString(stringRes)
    }
}