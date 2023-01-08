package zero.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import dagger.hilt.android.AndroidEntryPoint
import zero.app.app.notification.NotificationActivity
import zero.app.app.retrofit.RetrofitActivity
import zero.app.app.theme.TestTheme

enum class Actions(val action: (context: Context) -> Intent) {
    Retrofit({ RetrofitActivity.getIntent(it) }),
    Notification({ NotificationActivity.getIntent(it) })
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                // A surface container using the 'background' color from the theme
                LazyColumn {
                    items(Actions.values()) { item ->
                        TextButton(onClick = { startActivity(item.action(this@MainActivity)) }) {
                            Text(text = item.name)
                        }
                    }

                }
            }
        }
    }
}