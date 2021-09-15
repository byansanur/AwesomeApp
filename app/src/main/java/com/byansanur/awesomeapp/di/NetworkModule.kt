package com.byansanur.awesomeapp.di

import androidx.annotation.WorkerThread
import com.byansanur.awesomeapp.ApplicationAwesome
import com.byansanur.awesomeapp.api.ApiService
import com.byansanur.awesomeapp.common.AUTH_TOKEN
import com.byansanur.awesomeapp.common.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


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
            level = HttpLoggingInterceptor.Level.BODY
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
                val request = original.newBuilder()
                    .addHeader("Authorization", AUTH_TOKEN)
                    .build()
                val response = chain.proceed(request)
                response
            }
                /*
            .addInterceptor { chain ->
                // offline cache
                var request = chain.request()
                if (ApplicationAwesome.hasNetwork()) {
                    request = request.newBuilder().cacheControl(
                        CacheControl.Builder().maxStale(1, TimeUnit.DAYS).build()
                    ).build()
                }
                chain.proceed(request)
            }
            .addInterceptor { chain ->
                // cache
                chain.proceed(
                    chain.request().newBuilder().cacheControl(
                        CacheControl.Builder().maxStale(1, TimeUnit.DAYS).build()
                    ).build()
                )
            }
                 */
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