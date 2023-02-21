package zero.ui.notification

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint
import zero.ui.theme.TestTheme

@AndroidEntryPoint
@OptIn(ExperimentalPermissionsApi::class)
class NotificationActivity : ComponentActivity() {

    private val viewModel by viewModels<NotificationViewModel>()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                val notificationPermissionState = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS) { result ->

                }

                LaunchedEffect(notificationPermissionState.status) {
                    when (notificationPermissionState.status) {
                        is PermissionStatus.Denied -> {
                            notificationPermissionState.launchPermissionRequest()
                        }
                        PermissionStatus.Granted -> {
                        }
                    }
                }

                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    items(Channels.values()) { item ->
                        TextButton(onClick = { viewModel.notify(item) }) {
                            Text(text = item.name)
                        }
                    }
                }
            }
        }

    }

    companion object {
        fun getIntent(context: Context) = Intent(context, NotificationActivity::class.java)
    }
}