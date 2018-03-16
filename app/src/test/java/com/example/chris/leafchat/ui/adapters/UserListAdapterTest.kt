package com.example.chris.leafchat.ui.adapters

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.chris.leafchat.BaseTest
import com.example.chris.leafchat.viewmodel.ChatRoomViewModel
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
    @Inject lateinit var chatRoomViewModel: ChatRoomViewModel
    private lateinit var adapter: UserListAdapter
    private lateinit var spyVm: ChatRoomViewModel

    @Before
    override fun before() {
        super.before()

        component.inject(this)

        spyVm = spy(chatRoomViewModel)
        adapter = UserListAdapter(emptyList(), spyVm)
    }

    @Test
    fun updateItems() {
        //check item number correct
        adapter.updateItems(listOf("u1", "u2"))
        assertThat(adapter.itemCount, `is`(2))
    }

    @Test
    fun onBindViewHolder() {
        adapter.updateItems(listOf("u1", "u2"))
        val vh = mock(UserVH::class.java)
        `when`(vh.userName).thenReturn(TextView(application))
        `when`(vh.connectBtn).thenReturn(Button(application))
        adapter.onBindViewHolder(vh, 0)

        //check user name updated
        assertThat(vh.userName.text.toString(), `is`("u1"))

        //check onclick click triggered vm update
        vh.connectBtn.callOnClick()
        verify(spyVm, times(1)).requestConnection(ArgumentMatchers.anyString())
    }

}