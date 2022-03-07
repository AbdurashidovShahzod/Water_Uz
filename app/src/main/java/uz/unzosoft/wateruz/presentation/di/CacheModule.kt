package uz.unzosoft.wateruz.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.unzosoft.wateruz.data.local.LocalStorage
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideLocalStorage(@ApplicationContext context: Context): LocalStorage {
        return LocalStorage(context)
    }


}