package zero.app.ui.retrofit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import zero.app.ui.theme.TestTheme

@AndroidEntryPoint
class RetrofitActivity : ComponentActivity() {
    private val viewModel by viewModels<RetrofitViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                // A surface container using the 'background' color from the theme
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(modifier = Modifier.align(Alignment.Center), text = viewModel.title)
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, RetrofitActivity::class.java)
    }
}