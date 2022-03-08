package uz.unzosoft.wateruz.presentation.di

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.unzosoft.wateruz.data.local.LocalStorage
import uz.unzosoft.wateruz.presentation.app.App
import uz.unzosoft.wateruz.presentation.ui.utils.BaseConfig
import uz.unzosoft.wateruz.presentation.ui.utils.isConnected
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by Abdurashidov Shahzod on 07/03/22 16:07.
 * company QQBank
 * shahzod9933@gmail.com
 */

private const val MAX_STALE = 60 * 60 * 24 * 30 // 30day

private const val cacheSize: Long = 30 * 1024 * 1024 // 30MB
private val cache = Cache(App.instance.cacheDir, cacheSize)

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @[Provides Singleton]
    fun getApi(): String = BaseConfig.BASE_URL

    @[Provides Singleton]
    fun getLogging(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @[Provides Singleton]
    fun okHttpClient(
        logging: HttpLoggingInterceptor,
        @ApplicationContext context: Context,
        storage: LocalStorage
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .cache(cache)
        .addInterceptor(provideOfflineCacheInterceptor())
        .addInterceptor(ChuckInterceptor(context))//for seeing responses and requests from emulator
        .addInterceptor {
            val requestOld = it.request()
            val request = requestOld.newBuilder()
                .removeHeader("Authorization")//additional
                .addHeader("Authorization", "Bearer " + storage.token)
                .method(requestOld.method, requestOld.body)
                .build()
            val response = it.proceed(request)
            response
        }
        .build()

    @[Provides Singleton]
    fun getRetrofit(
        api: String,
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(api)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //retrieves max-stale cache if connection is off
    private fun provideOfflineCacheInterceptor() = Interceptor { chain ->
        var request = chain.request()

        if (!isConnected()) {
            val cacheControl = CacheControl.Builder()
                .maxStale(MAX_STALE, TimeUnit.SECONDS)
                .build()

            request = request.newBuilder()
                .removeHeader("Cache-Control")
                .cacheControl(cacheControl)
                .build()
        }
        chain.proceed(request)
    }
}
