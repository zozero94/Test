package zero.ui.di

import android.content.Context
import android.os.Build
import androidx.core.app.NotificationChannelCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import zero.ui.notification.ChannelManager
import zero.ui.notification.Channels
import zero.ui.notification.V26ChannelManager

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideNotification(
        @ApplicationContext context: Context
    ) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        V26ChannelManager(context = context)
    } else object : ChannelManager {
        override suspend fun getChannelId(channel: Channels): String = NotificationChannelCompat.DEFAULT_CHANNEL_ID
    }
}