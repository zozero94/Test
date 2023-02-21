package zero.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber
import zero.data.source.remote.ZeroCallAdapterFactory
import zero.data.source.remote.ZeroConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor {
                val request = it.request()
                Timber.tag("zero:okhttp").d("normalIntercept$request")
                it.proceed(request)
            }
            .addNetworkInterceptor {
                val request = it.request()
                Timber.tag("zero:okhttp").d("networkIntercept$request")
                it.proceed(request)
            }
            .build()
    }

    @Provides
    fun provideJson() = Json { ignoreUnknownKeys = true }

    @Provides
    fun provideSerializationConverterFactory(json: Json) = json.asConverterFactory("application/json".toMediaType())

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        jsonConverterFactory: Converter.Factory
    ) = Retrofit
        .Builder()
        .client(okHttpClient)
        .addCallAdapterFactory(ZeroCallAdapterFactory)
        .addConverterFactory(ZeroConverterFactory(jsonConverterFactory))
        .baseUrl("https://raw.githubusercontent.com/")
        .build()


}