package zero.ui.notification

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import zero.app.R
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val channelManager: ChannelManager
) : ViewModel() {
    private val notificationManager: NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(context)
    }

    private var normalCount = 0
    private var group1count = MutableStateFlow(0)
    private var group2count = MutableStateFlow(0)
    private var tempGroupId = arrayOf("kakao", "daum")

    fun notify(channels: Channels) {
        viewModelScope.launch {
            val channelId = channelManager.getChannelId(channels)

            when (channels) {
                Channels.NORMAL -> {
                    notificationManager.notify(
                        channels.notificationChannelId,
                        normalCount,
                        createNotification(channelId, channels.groupId, normalCount.toString())
                    )
                    normalCount += 1
                }
                Channels.GROUP1 -> {
                    val newMessageId = group1count.value + 1
                    val groupId = tempGroupId.random()
                    notificationManager.notify(
                        channels.notificationChannelId,
                        newMessageId,
                        createNotification(channelId, groupId, newMessageId.toString())
                    )
                    group1count.emit(newMessageId)
                }
                Channels.GROUP2 -> {
                    val newMessageId = group2count.value + 1
                    val groupId = tempGroupId.random()
                    notificationManager.notify(
                        channels.notificationChannelId,
                        newMessageId,
                        createNotification(channelId, groupId, newMessageId.toString())
                    )
                    notificationManager.notify(
                        channelId,
                        newMessageId,
                        createSummary(channelId, groupId)
                    )
                    group2count.emit(newMessageId)
                }

            }
        }

    }

    private fun createSummary(channelId: String, groupId: String?): Notification {
        return NotificationCompat.Builder(context, channelId)
            .setAutoCancel(true)
            .setGroupSummary(true)
            .setGroup(groupId)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentTitle(channelId)
            .setContentText("요약 메시지")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }

    private fun createNotification(channelId: String, groupId: String?, name: String): Notification {
        return NotificationCompat.Builder(context, channelId)
            .setAutoCancel(true)
            .setGroup(groupId)
            .setSortKey("${channelId}_$groupId")
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentTitle(channelId)
            .setContentText("$name / groupId : $groupId")
            .setSubText("ㅇㅇㅇㅇ???")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }

}