package com.example.chris.leafchat.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.example.chris.leafchat.Logger
import com.example.chris.leafchat.model.AllUserResponse
import com.example.chris.leafchat.repository.BaseRepoCallback
import com.example.chris.leafchat.repository.ChatRoomRepository
import com.example.chris.leafchat.repository.SocketIoRepository
import org.webrtc.PeerConnectionFactory
import javax.inject.Inject

/**
 * Created by Chris on 3/6/18.
 */
class ChatRoomViewModel @Inject constructor(
        application: Application,
        private val chatRoomRepository: ChatRoomRepository,
        private val socketIoRepository: SocketIoRepository)
    : BaseViewModel(application), ChatRoomRepository.ChatRoomCallback, SocketIoRepository.SocketIoCallback {

    val progressObserver: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val toastMsgObserver: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val userListObserver: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>() }

    init {
        chatRoomRepository.call(this)
        socketIoRepository.call(this)
        //temp solution, need to replace later with push notification
        socketIoRepository.connectSocketIo()
    }

    fun getAllUsers() {
        setObserverValue(progressObserver, true)
        chatRoomRepository.getAllUsers()
    }

    fun requestConnection(userName: String) {
        PeerConnectionFactory.initializeAndroidGlobals(getApplication(), true, true, true)
        socketIoRepository.initConnection()

//        setObserverValue(progressObserver, true)
        Logger.log("connect", userName)
    }

    //region repo callback
    override fun onGetAllUser(users: List<String>) {
        setObserverValue(progressObserver, false)
        setObserverValue(userListObserver, users)
    }

    override fun onErrorWithId(error: Int) {
        setObserverValue(progressObserver, false)
        setObserverValue(toastMsgObserver, getContext().getString(error))
    }

    override fun onSocketConnected() {
    }
    //endregion
}