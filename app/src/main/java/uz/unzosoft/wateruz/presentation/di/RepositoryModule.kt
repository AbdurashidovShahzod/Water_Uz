package uz.unzosoft.wateruz.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.unzosoft.wateruz.data.repository.HomeRepositoryImpl
import uz.unzosoft.wateruz.data.repository.LoginRepositoryImpl
import uz.unzosoft.wateruz.data.repository.LoginStateRepositoryImpl
import uz.unzosoft.wateruz.data.repository.OrdersRepositoryImpl
import uz.unzosoft.wateruz.domain.repository.HomeRepository
import uz.unzosoft.wateruz.domain.repository.LoginRepository
import uz.unzosoft.wateruz.domain.repository.LoginRepositoryState
import uz.unzosoft.wateruz.domain.repository.OrdersRepository


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

    @Binds
    fun bindsStateLoginRepository(loginStateRepositoryImpl: LoginStateRepositoryImpl):LoginRepositoryState

    @Binds
    fun bindsOrdersRepository(ordersRepositoryImpl:OrdersRepositoryImpl):OrdersRepository

    @Binds
    fun bindsHomeRepository(homeRepositoryImpl: HomeRepositoryImpl):HomeRepository
}