package com.byansanur.awesomeapp.di

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.core.content.ContextCompat
import com.byansanur.awesomeapp.ApplicationAwesome
import com.byansanur.awesomeapp.BuildConfig
import com.byansanur.awesomeapp.BuildConfig.AuthToken
//import com.byansanur.awesomeapp.BuildConfig.AuthToken
import com.byansanur.awesomeapp.api.ApiService
import com.byansanur.awesomeapp.common.AUTH_TOKEN
import com.byansanur.awesomeapp.common.BASE_URL
import com.byansanur.awesomeapp.common.InternetConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext


/**
 * Created by byansanur on 9/14/2021.
 * ratbyansanur81@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Singleton
    @Provides
    @WorkerThread
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            //handle request time out
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor { chain ->
                val original = chain.request()
                val buildToken = AuthToken
                val constToken = AUTH_TOKEN
                val request = original.newBuilder()
                    .addHeader("Authorization", buildToken)
                    .build()
                val response = chain.proceed(request)
                response
            }
            .addInterceptor { chain ->
                // cache
                chain.proceed(
                    chain.request().newBuilder().cacheControl(
                        CacheControl.Builder().maxStale(1, TimeUnit.DAYS).build()
                    ).build()
                )
            }

            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService =
        retrofit.create(ApiService::class.java)

}