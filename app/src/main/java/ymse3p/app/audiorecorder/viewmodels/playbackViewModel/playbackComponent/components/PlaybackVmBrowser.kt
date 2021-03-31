package ymse3p.app.audiorecorder.viewmodels.playbackViewModel.playbackComponent.components

import android.content.ComponentName
import android.content.Context
import android.support.v4.media.MediaBrowserCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import ymse3p.app.audiorecorder.services.AudioService
import javax.inject.Inject

@ViewModelScoped
class PlaybackVmBrowser @Inject constructor(
    @ApplicationContext private val context: Context,
    playbackComponentState: PlaybackComponentState
) {

    private val subscriptionCallback = object : MediaBrowserCompat.SubscriptionCallback() {
        override fun onChildrenLoaded(
            parentId: String,
            children: MutableList<MediaBrowserCompat.MediaItem>
        ) {
            if (children.isNullOrEmpty()) {
                mediaBrowser.apply {
                    disconnect()
                    connect()
                }
            }
        }

    }

    private val connectionCallback =
        object : MediaBrowserCompat.ConnectionCallback() {
            override fun onConnected() {
                playbackComponentState.setControllerConnectedState(true)
                mediaBrowser.subscribe(mediaBrowser.root, subscriptionCallback)
            }
        }

    val mediaBrowser: MediaBrowserCompat =
        MediaBrowserCompat(
            context, ComponentName(context, AudioService::class.java),
            connectionCallback, null
        ).apply { connect() }

}