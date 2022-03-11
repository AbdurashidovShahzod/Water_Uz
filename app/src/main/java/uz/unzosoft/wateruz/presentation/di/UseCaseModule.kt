package uz.unzosoft.wateruz.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.unzosoft.wateruz.data.repository.LoginRepositoryImpl
import uz.unzosoft.wateruz.data.usecase.LoginUseCaseImpl
import uz.unzosoft.wateruz.data.usecase.OrdersUseCaseImpl
import uz.unzosoft.wateruz.domain.repository.LoginRepository
import uz.unzosoft.wateruz.domain.usecase.LoginUseCase
import uz.unzosoft.wateruz.domain.usecase.OrdersUseCase


/**
 * Created by Abdurashidov Shahzod on 09/03/22 18:03.
 * company QQBank
 * shahzod9933@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {
    @Binds
    fun bindsLoginUseCase(loginUseCaseImpl: LoginUseCaseImpl): LoginUseCase

    @Binds
    fun bindsOrdersUseCase(ordersUseCaseImpl: OrdersUseCaseImpl): OrdersUseCase
}