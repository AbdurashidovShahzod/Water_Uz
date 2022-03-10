package uz.unzosoft.wateruz.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.unzosoft.wateruz.data.remote.api.ApiService
import javax.inject.Singleton


/**
 * Created by Abdurashidov Shahzod on 09/03/22 01:53.
 * company QQBank
 * shahzod9933@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @[Provides]
    fun provideApiService(@WaterServiceQualifier retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}