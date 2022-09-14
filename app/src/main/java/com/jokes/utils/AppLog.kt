package com.jokes.utils

/**
 * All the logs could be done using AppLog methods,
 * 1. In Debug keep the LOG_ENABLED true
 * 2. In Production you have to set LOG_ENABLED false
 * 3. You could pass your own TAG or you could use the default TAG everywhere
 */
object AppLog {

    private const val TAG = "jokes"

    private const val LOG_ENABLED = true

    fun i(message: String) {
        if (LOG_ENABLED)
            android.util.Log.i(TAG, message)
    }

    fun i(tag: String, message: String) {
        if (LOG_ENABLED)
            android.util.Log.i(tag, message)
    }

    fun v(message: String) {
        if (LOG_ENABLED)
            android.util.Log.v(TAG, message)
    }

    fun v(tag: String, message: String) {
        if (LOG_ENABLED)
            android.util.Log.v(tag, message)
    }

    fun d(message: String) {
        if (LOG_ENABLED)
            android.util.Log.d(TAG, message)
    }

    fun d(tag: String, message: String) {
        if (LOG_ENABLED)
            android.util.Log.d(tag, message)
    }

    fun e(message: String) {
        if (LOG_ENABLED)
            android.util.Log.e(TAG, message)
    }

    fun e(tag: String, message: String) {
        if (LOG_ENABLED)
            android.util.Log.e(tag, message)
    }

    fun w(message: String) {
        if (LOG_ENABLED)
            android.util.Log.w(TAG, message)
    }

    fun w(tag: String, message: String) {
        if (LOG_ENABLED)
            android.util.Log.w(tag, message)
    }
}