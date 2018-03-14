package com.example.chris.leafchat.viewmodel

import com.example.chris.leafchat.BaseTest
import com.example.chris.leafchat.R
import com.example.chris.leafchat.mocks.MockServiceHelper
import com.example.chris.leafchat.network.ServiceHelper
import com.example.chris.leafchat.repository.ChatRoomRepository
import org.hamcrest.Matchers.`is`
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import javax.inject.Inject

/**
 * Created by Chris on 3/14/18.
 */
class ChatRoomViewModelTest : BaseTest() {

    @Inject lateinit var chatRoomViewModel: ChatRoomViewModel
    @Inject lateinit var chatRoomRepository: ChatRoomRepository
    @Inject lateinit var serviceHelper: ServiceHelper

    private val spyRepoCallback = mock(ChatRoomRepository.ChatRoomCallback::class.java)

    @Before
    override fun before() {
        super.before()

        component.inject(this)

        chatRoomRepository.call(spyRepoCallback)
    }

    @Test
    fun getAllUsers() {
        //test error
        (serviceHelper as MockServiceHelper).serviceStatus = MockServiceHelper.GENERIC_ERROR
        chatRoomRepository.getAllUsers()
        verify(spyRepoCallback, times(1)).onErrorWithId(R.string.error_generic)


        //test success
        //test progress dismissed
        (serviceHelper as MockServiceHelper).serviceStatus = MockServiceHelper.STATUS_SUCCESS
        chatRoomRepository.getAllUsers()
        verify(spyRepoCallback, times(1)).onGetAllUser(ArgumentMatchers.anyList())
    }

    @Test
    fun onGetAllUser() {
        //test progress dismissed
        //test value
        chatRoomViewModel.progressObserver.value = true
        chatRoomViewModel.onGetAllUser(listOf("user1", "user2"))
        assertThat(chatRoomViewModel.progressObserver.value, `is`(false))
        assertThat(chatRoomViewModel.userListObserver.value?.size, `is`(2))
    }

    @Test
    fun onErrorWithId() {
        //test progress dismissed
        //test value
        chatRoomViewModel.progressObserver.value = true
        chatRoomViewModel.onErrorWithId(R.string.error_generic)
        assertThat(chatRoomViewModel.progressObserver.value, `is`(false))
        assertThat(chatRoomViewModel.toastMsgObserver.value, `is`(application.getString(R.string.error_generic)))
    }

}