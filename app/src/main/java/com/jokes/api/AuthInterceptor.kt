package com.jokes.api

import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val interceptUrl = original.url.newBuilder()
          /*  .addQueryParameter("api_key", "")*/
            .build()
        val newReq = original.newBuilder().url(interceptUrl).build()
        return chain.proceed(newReq)
    }
}

