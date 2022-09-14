package com.jokes.common


import com.jokes.BuildConfig
import com.jokes.api.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object NetworkModules : BaseModuleProvider {

    override val modules: List<Module>
        get() = listOf(
            retrofitModule,
            webServiceModule
        )

    private val webServiceModule = module {

        single { get<Retrofit>().create(JokesApiService::class.java) }


    }

    private val retrofitModule = module {
        single { provideAuthInterceptor() }
        single { provideOkHttpClient(get()) }
        single { provideRetrofit(get(), BuildConfig.BASE_URL) }
    }

    private fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor()
    private fun headerInterceptor(): HeaderInterceptor = HeaderInterceptor()


    private fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else
                HttpLoggingInterceptor.Level.NONE


//            level = HttpLoggingInterceptor.Level.BODY
        }


        return OkHttpClient.Builder()

            .addInterceptor(headerInterceptor())
            .addInterceptor(logging)
            .connectTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}