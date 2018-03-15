package com.example.chris.leafchat.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.chris.leafchat.BaseTest
import org.hamcrest.Matchers.`is`
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import javax.inject.Inject

/**
 * Created by Chris on 3/15/18.
 */
class UserListAdapterTest : BaseTest() {
    @Inject lateinit var adapter: UserListAdapter
    private lateinit var spyAdapter: UserListAdapter

    @Before
    override fun before() {
        super.before()

        component.inject(this)

        spyAdapter = spy(adapter)
    }

    @Test
    fun updateItems() {
        adapter.updateItems(listOf("u1", "u2"))
        assertThat(adapter.itemCount, `is`(2))
    }

    @Test
    fun onBindViewHolder() {
        adapter.updateItems(listOf("u1", "u2"))
        val vh = mock(UserVH::class.java)
        `when`(vh.userName).thenReturn(TextView(application))
        adapter.onBindViewHolder(vh, 0)

        assertThat(vh.userName.text.toString(), `is`("u1"))
    }

}