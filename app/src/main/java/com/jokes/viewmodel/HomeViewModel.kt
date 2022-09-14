package com.jokes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokes.common.ResponseResult
import com.jokes.common.ResponseWrapper
import com.jokes.repositories.JokesDataSource


/**
 * This class is responsible to
 * 1. TODO
 */
class HomeViewModel(
    private val jokesDataSource: JokesDataSource
) :
    ViewModel() {


    /**
     * This method will return joke from server
     *
     * @return Single Joke
     */

    fun getLatestJokes(): LiveData<ResponseResult<ResponseWrapper<String>>> {

        return jokesDataSource.getLatestJokes(
            scope = viewModelScope
        )

    }



    companion object {
        private const val TAG = "HomeViewModel"
    }


}