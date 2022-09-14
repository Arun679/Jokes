package com.jokes.repositories

import androidx.lifecycle.LiveData
import com.jokes.common.BaseDataSource
import com.jokes.common.ResponseResult
import com.jokes.common.ResponseWrapper
import com.jokes.common.resultLiveData
import com.jokes.api.JokesApiService
import kotlinx.coroutines.CoroutineScope


class JokesDataSource(private val jokesApiService: JokesApiService) :
    BaseDataSource() {


    fun getLatestJokes(
        scope: CoroutineScope
    ): LiveData<ResponseResult<ResponseWrapper<String>>> =
        resultLiveData(scope) {
            getResult {
                jokesApiService.getLatestJokes()
            }
        }


}