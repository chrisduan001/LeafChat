package com.example.chris.leafchat.repository

import com.example.chris.leafchat.BuildConfig
import com.example.chris.leafchat.Logger
import com.example.chris.leafchat.util.ErrorHandler
import com.example.chris.leafchat.util.LeafSharedPreference
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.webrtc.*
import javax.inject.Inject

/**
 * Created by Chris on 3/23/18.
 */
class SocketIoRepository @Inject constructor(
        errorHandler: ErrorHandler,
        private val sharedPreference: LeafSharedPreference)
    : BaseRepository<SocketIoRepository.SocketIoCallback>(errorHandler = errorHandler) {

    //Temp solution need to replace later with push notification
    private val socket: Socket by lazy {
        val opts = IO.Options()
        val name = sharedPreference.getUserName()
        opts.query = String.format("roomNumber=temproom&userName=%s", name)
        IO.socket("http://192.168.1.196:1337", opts)
    }

    private val iceServers = arrayListOf(PeerConnection.IceServer("stun:stun.l.google.com:19302"))

    private lateinit var peerConnection : PeerConnection
    private val peerConnectionFactory: PeerConnectionFactory by lazy {
        PeerConnectionFactory()
    }

    fun connectSocketIo() {
        if (socket.connected()) {
            return
        }

        socket.on(Socket.EVENT_CONNECT) {
            listener?.onSocketConnected()
        }

        socket.on("receive_sdp") {
            t ->
        }

        socket.connect()
    }

    fun initConnection() {
        peerConnection = peerConnectionFactory.createPeerConnection(
                iceServers,
                MediaConstraints(),
                peerConnectionOb)

        peerConnection.createOffer(sdbOb, MediaConstraints())
    }

    fun receiveSdp(description: String) {
        //if offer
        peerConnection.setRemoteDescription(sdbOb,
                SessionDescription(SessionDescription.Type.OFFER, description))

        peerConnection.createAnswer(sdbOb, MediaConstraints())
    }

    fun receiveIceCandidate(mLineIndex: Int, sdpMid: String, sdp: String) {
        peerConnection.addIceCandidate(IceCandidate(sdpMid, mLineIndex, sdp))
    }

    fun disconnectSocket() {
        socket.disconnect()
    }

    private val peerConnectionOb = object : PeerConnection.Observer {
        override fun onIceGatheringChange(p0: PeerConnection.IceGatheringState?) {
        }

        override fun onAddStream(p0: MediaStream?) {
        }

        override fun onIceCandidate(iceCandidate: IceCandidate) {
            iceCandidate.sdpMid
            iceCandidate.sdpMLineIndex
            iceCandidate.sdp
        }

        override fun onDataChannel(p0: DataChannel?) {
        }

        override fun onSignalingChange(p0: PeerConnection.SignalingState?) {
        }

        override fun onIceConnectionReceivingChange(p0: Boolean) {
        }

        override fun onRemoveStream(p0: MediaStream?) {
        }

        override fun onIceConnectionChange(p0: PeerConnection.IceConnectionState?) {
        }

        override fun onRenegotiationNeeded() {
        }
    }

    private val sdbOb = object : SdpObserver {
        override fun onSetFailure(p0: String?) {
        }

        override fun onSetSuccess() {
            //send localdescription
            socket.emit("send_android_sdp", peerConnection.localDescription.type,
                    peerConnection.localDescription.description)
            Logger.log("ds", peerConnection.localDescription.toString())
        }

        override fun onCreateSuccess(session: SessionDescription) {
            peerConnection.setLocalDescription(this, session)
        }

        override fun onCreateFailure(p0: String?) {
        }
    }

    interface SocketIoCallback : BaseRepoCallback {
        fun onSocketConnected()
    }
}