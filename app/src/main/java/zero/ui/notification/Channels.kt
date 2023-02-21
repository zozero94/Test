package zero.ui.notification

import androidx.annotation.StringRes
import zero.app.R

enum class Channels(
    val notificationChannelId: String,
    @StringRes val channelName: Int?,
    val groupId: String?,
    val showBadge: Boolean
) {
    NORMAL(
        notificationChannelId = "일반 노티",
        channelName = R.string.normal,
        groupId = null,
        showBadge = true
    ),
    GROUP1(
        notificationChannelId = "그룹1",
        channelName = R.string.group1,
        groupId = MESSAGE_GROUP1,
        showBadge = true
    ),
    GROUP2(
        notificationChannelId = "그룹2",
        channelName = R.string.group2,
        groupId = MESSAGE_GROUP2,
        showBadge = true
    ),
}

const val MESSAGE_GROUP1 = "group"
const val MESSAGE_GROUP2 = "group2"