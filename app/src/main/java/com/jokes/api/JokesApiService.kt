package com.jokes.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers



interface JokesApiService {
    @GET("api")
    @Headers("No-Authentication: null")
    suspend fun getLatestJokes(): Response<String>
}