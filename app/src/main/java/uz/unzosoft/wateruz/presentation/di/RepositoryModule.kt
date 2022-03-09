package uz.unzosoft.wateruz.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.unzosoft.wateruz.data.repository.LoginRepositoryImpl
import uz.unzosoft.wateruz.domain.repository.LoginRepository


/**
 * Created by Abdurashidov Shahzod on 09/03/22 18:03.
 * company QQBank
 * shahzod9933@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsLoginRepository(loginRepositoryImpl: LoginRepositoryImpl):LoginRepository
}