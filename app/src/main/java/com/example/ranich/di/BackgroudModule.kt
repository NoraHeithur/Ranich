package com.example.ranich.di

import android.app.Service
import android.content.Context
import com.example.ranich.common.NotificationMaster
import com.example.ranich.common.NotificationMasterImpl
import com.example.ranich.common.SensorObserverManager
import com.example.ranich.common.SessionManager
import com.example.ranich.common.SessionManagerImpl
import com.example.ranich.data.MyFirebaseMessagingService
import com.example.ranich.data.network.RanichApi
import com.example.ranich.data.network.RanichServicesClient
import com.example.ranich.data.network.UnsafeOkHttpClient
import com.example.ranich.domain.common.SensorObserver
import com.markodevcic.peko.PermissionRequester
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

typealias DispatcherIO = CoroutineDispatcher

val networkModule = module {
    singleOf(::provideMoshi)
    singleOf(::provideIOCoroutineDispatcher)
    singleOf(::provideHttpLoggingInterceptor)
    singleOf(::provideOkHttpClient)
    singleOf(::provideRetrofit)
    singleOf(::providePermissionRequestInstance)
    singleOf(::NotificationMasterImpl) { bind<NotificationMaster>() }
    singleOf(::RanichServicesClient) { bind<RanichApi>() }
    singleOf(::SensorObserverManager) { bind<SensorObserver>() }
    singleOf(::SessionManagerImpl) { bind<SessionManager>() }
    single<Service> { MyFirebaseMessagingService() }
}

private fun provideRetrofit(
    moshi: Moshi,
    client: OkHttpClient,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://188.166.249.112/api/v1/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient {
    val unsafeOkHttpClient = UnsafeOkHttpClient
    return unsafeOkHttpClient.getUnsafeOkHttpClient()
        .addInterceptor(loggingInterceptor)
        .build()
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        .setLevel(HttpLoggingInterceptor.Level.BODY)
}

private fun provideMoshi(): Moshi {
    return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}

private fun provideIOCoroutineDispatcher(): DispatcherIO {
    return Dispatchers.IO
}

private fun providePermissionRequestInstance(context: Context): PermissionRequester {
    PermissionRequester.initialize(context)
    return PermissionRequester.instance()
}