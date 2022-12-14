package zero.app.app.retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import zero.app.data.source.remote.TestApi
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(
    private val api: TestApi
) : ViewModel() {
    val title = "retrofit"

    init {
        viewModelScope.launch {
            runCatching { api.getRule() }
                .onFailure { Timber.tag("zero:fail").e(it) }
                .onSuccess { Timber.tag("zero:success").d("$it") }
        }
    }
}