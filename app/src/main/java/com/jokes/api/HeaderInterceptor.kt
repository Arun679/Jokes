package com.jokes.api


import com.criclivline.utils.AppPreference
import com.jokes.App
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class HeaderInterceptor : Interceptor {

    private val preference: AppPreference by lazy {
        AppPreference(App.instance)
    }


    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        if (original.header("No-Authentication") == null) {
            return chain.proceed(original)
        }

        val request: Request = original.newBuilder()
            .header("Accept", "application/json")
//            .header("Authorization", "Bearer " +preference.accessToken)
//            .header("Content-Type", "application/json")
            .method(original.method, original.body)
            .build()
        return chain.proceed(request)

    }
}

