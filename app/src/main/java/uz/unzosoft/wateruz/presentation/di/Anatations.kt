package uz.unzosoft.wateruz.presentation.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppCacheQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EncryptedCacheQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WaterServiceQualifier
