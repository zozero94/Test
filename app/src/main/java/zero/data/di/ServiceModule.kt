package zero.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import zero.data.source.remote.TestApi

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Provides
    fun provideTestApi(retrofit: Retrofit): TestApi = retrofit.create()
}