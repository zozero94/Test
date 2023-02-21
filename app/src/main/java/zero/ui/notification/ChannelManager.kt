package zero.ui.notification

interface ChannelManager {
    suspend fun getChannelId(channel: Channels): String
}