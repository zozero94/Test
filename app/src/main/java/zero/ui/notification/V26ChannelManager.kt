package zero.ui.notification

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat

@RequiresApi(Build.VERSION_CODES.O)
class V26ChannelManager constructor(private val context: Context) : ChannelManager {
    private val notificationManager = NotificationManagerCompat.from(context)

    init {
        Channels.values()
            .filter { it.groupId != null }
            .forEach {
                notificationManager.createNotificationChannelGroup(
                    NotificationChannelGroup(
                        it.groupId,
                        it.notificationChannelId
                    )
                )
            }
    }

    override suspend fun getChannelId(channel: Channels): String {
        val channelId = Uri.Builder()
            .scheme(channel.notificationChannelId)
            .path(NOTIFICATION_PATH)
            .toString()
        val foundChannel = notificationManager.notificationChannels.findChannel(channel.notificationChannelId)

        return if (foundChannel == null) {
            channelVersioning(
                foundChannel,
                channel.groupId,
                channelId,
                if (channel.channelName != null) context.getString(channel.channelName) + "1" else "",
                true,
                channel.showBadge
            )
        } else {
            foundChannel.id
        }
    }

    private fun channelVersioning(
        foundChannel: NotificationChannel?,
        groupId: String?,
        notificationChannelId: String,
        channelName: String,
        vibrationEnabled: Boolean,
        showBadge: Boolean
    ): String {
        val versionId = if (foundChannel != null) foundChannel.id else "${notificationChannelId}#0"
        notificationManager.deleteNotificationChannel(versionId)
        val (prefix, rev) = versionId.split(VERSION_SEPARATOR)
        val revisionId = "${prefix}$VERSION_SEPARATOR${(rev.toInt()) + 1}"

        notificationManager.createNotificationChannel(
            NotificationChannelCompat
                .Builder(revisionId, NotificationManagerCompat.IMPORTANCE_HIGH)
                .setGroup(groupId)
                .setName(channelName)
                .setVibrationEnabled(vibrationEnabled)
                .setShowBadge(showBadge)
                .build()
        )
        return revisionId
    }

    private fun List<NotificationChannel>.findChannel(channelId: String): NotificationChannel? =
        firstOrNull {
            runCatching {
                val (uri, _) = it.id.split(VERSION_SEPARATOR)
                uri.contains(channelId)
            }.getOrDefault(false)
        }


    companion object {
        private const val VERSION_SEPARATOR = "#"
        private const val NOTIFICATION_PATH = "notification"
    }
}