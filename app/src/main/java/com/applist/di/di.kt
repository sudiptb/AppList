package com.applist.di

import com.applist.data.Api
import com.applist.data.Api.Companion.BASE_URL
import com.applist.data.ErrorListInterceptor
import com.applist.repository.AppRepository
import com.applist.viewmodel.MyViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val nm = module {
    single {
        Retrofit.Builder()
            /*.baseUrl("https://attendance-tracker.codelogicx.com/api/")*/
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also { client ->
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                    client.addInterceptor(ErrorListInterceptor())
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                    client.addInterceptor { chain ->
                        val original = chain.request()
                        val requestBuilder = original.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Accept", "application/json")
                        val request = requestBuilder.build()
                        chain.proceed(request)
                    }
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
    factory { AppRepository( get() ) }
    viewModel { MyViewModel( get() ) }
}