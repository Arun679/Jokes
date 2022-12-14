package com.jokes.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.jokes.utils.AppLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response



abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResponseResult<ResponseWrapper<T>> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (null != body) return ResponseResult.Success(ResponseWrapper(body, null))
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(msg: String): ResponseResult<ResponseWrapper<T>> {
        AppLog.e(msg)
        return ResponseResult.Error(ResponseWrapper(null, msg))
    }

}

fun <T> resultLiveData(scope: CoroutineScope, call: suspend () -> ResponseResult<T>): LiveData<ResponseResult<T>> {
    return liveData(scope.coroutineContext) {
        emit(ResponseResult.Loading)

        withContext(Dispatchers.IO) {
            emit(call.invoke())
        }
    }
}