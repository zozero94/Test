package zero.app.app.notification

interface ChannelManager {
    suspend fun getChannelId(channel: Channels): String
}